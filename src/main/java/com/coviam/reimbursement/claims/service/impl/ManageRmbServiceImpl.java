package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.entity.Status;
import com.coviam.reimbursement.claims.entity.UserMaster;
import com.coviam.reimbursement.claims.model.base.ManageReimbursementRequest;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemStatusDto;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemStatusLogDto;
import com.coviam.reimbursement.claims.model.base.RmbItemWorkFlowStatusDto;
import com.coviam.reimbursement.claims.service.api.ManageRmbService;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemStatusLogService;
import com.coviam.reimbursement.claims.service.api.ReimbursementService;
import com.coviam.reimbursement.claims.service.api.RmbItemWorkFlowStatusService;
import com.coviam.reimbursement.claims.service.api.StatusService;
import com.coviam.reimbursement.claims.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Foram Shah on 24/06/18
 */
@Service
public class ManageRmbServiceImpl implements ManageRmbService {

  @Autowired
  private RmbItemWorkFlowStatusService rmbItemWorkFlowStatusService;

  @Autowired
  private ReimbursementItemStatusLogService reimbursementItemStatusLogService;

  @Autowired
  private ReimbursementItemService reimbursementItemService;

  @Autowired
  private ReimbursementService reimbursementService;

  @Autowired
  private UserService userService;

  @Autowired
  private StatusService statusService;

  @Override
  public void manageClaims(ManageReimbursementRequest manageReimbursementRequest) {
    Status rmbStatus = new Status();
    boolean isRejected = false;
    UserMaster userMaster = userService.findByEmail(manageReimbursementRequest.getUserEmail());
    for (ReimbursementItemStatusDto reimbursementItemStatusDto : manageReimbursementRequest
        .getReimbursementItemStatusDtos()) {
      if ("REJECTED".equalsIgnoreCase(reimbursementItemStatusDto.getAction()))
        isRejected = true;
      ReimbursementItem reimbursementItem =
          this.reimbursementItemService.findByReimbursementItemByReimburesementItemId(
              reimbursementItemStatusDto.getReimbursementItemId());

      ReimbursementItemStatusLogDto reimbursementItemStatusLogDto =
          new ReimbursementItemStatusLogDto();
      reimbursementItemStatusLogDto.setNewStatusCode(
          this.statusService.findByStatusCode(reimbursementItemStatusDto.getAction()));
      reimbursementItemStatusLogDto.setOldStatusCode(reimbursementItem.getItemStatus());
      reimbursementItemStatusLogDto
          .setReimbursementItemId(reimbursementItem.getReimbursementItemId());
      this.reimbursementItemStatusLogService.save(reimbursementItemStatusLogDto);


      RmbItemWorkFlowStatusDto rmbItemWorkFlowStatusDto = new RmbItemWorkFlowStatusDto();
      rmbItemWorkFlowStatusDto
          .setReimbursementItemId(reimbursementItemStatusDto.getReimbursementItemId());
      rmbItemWorkFlowStatusDto.setRemarks(reimbursementItemStatusDto.getRemarks());
      rmbItemWorkFlowStatusDto
          .setStatus(this.statusService.findByStatusCode(reimbursementItemStatusDto.getAction()));
      rmbItemWorkFlowStatusDto.setUserEmail(manageReimbursementRequest.getUserEmail());
      this.rmbItemWorkFlowStatusService.save(rmbItemWorkFlowStatusDto);

      reimbursementItem.setItemStatus(
          this.statusService.findByStatusCode(reimbursementItemStatusDto.getAction()));
      reimbursementItem.setUpdatedAt(new Date());
      reimbursementItem.setUpdatedBy("System");
      this.reimbursementItemService.update(reimbursementItem);
    }
    if ("ADMIN".equalsIgnoreCase(userMaster.getUserType().getUserTypeCode()) && isRejected) {
      rmbStatus = statusService.findByStatusCode("OPEN");
    } else if ("ADMIN".equalsIgnoreCase(userMaster.getUserType().getUserTypeCode())
        && !isRejected) {
      rmbStatus = statusService.findByStatusCode("APPROVED");
    } else if ("FINANCE".equalsIgnoreCase(userMaster.getUserType().getUserTypeCode())
        && isRejected) {
      rmbStatus = statusService.findByStatusCode("OPEN");
    } else if ("FINANCE".equalsIgnoreCase(userMaster.getUserType().getUserTypeCode())
        && !isRejected) {
      rmbStatus = statusService.findByStatusCode("VERIFIED");
    }
    Reimbursement reimbursement = this.reimbursementService
        .findReimburesementByRmbId(manageReimbursementRequest.getReimbursementId());
    reimbursement.setStatusId(rmbStatus);
    reimbursement.setUpdatedAt(new Date());
    reimbursement.setUpdatedBy("System");
    this.reimbursementService.saveRmb(reimbursement);
  }
}
