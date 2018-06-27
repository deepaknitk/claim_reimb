package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.ReimbursementItemWorkFlowStatus;
import com.coviam.reimbursement.claims.model.base.RmbItemWorkFlowStatusDto;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.repository.RmbItemWorkFlowStatusRepository;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import com.coviam.reimbursement.claims.service.api.RmbItemWorkFlowStatusService;
import com.coviam.reimbursement.claims.service.api.StatusService;
import com.coviam.reimbursement.claims.service.api.UserService;
import com.coviam.reimbursement.claims.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Foram Shah on 24/06/18
 */
@Service
public class RmbItemWorkFlowStatusServiceImpl implements RmbItemWorkFlowStatusService {

  @Autowired
  private RmbItemWorkFlowStatusRepository rmbItemWorkFlowStatusRepository;

  @Autowired
  private StatusService statusService;

  @Autowired
  private UserService userService;

  @Autowired
  private ReimbursementItemService reimbursementItemService;

  @Override
  public ReimbursementItemWorkFlowStatus save(RmbItemWorkFlowStatusDto rmbItemWorkFlowStatusDto) {
    CommonUtils.checkError(rmbItemWorkFlowStatusDto != null, Error.RMB_ITEM_WORK_FLOW_STATUS_NULL);
    ReimbursementItemWorkFlowStatus reimbursementItemWorkFlowStatus =
        new ReimbursementItemWorkFlowStatus();
    reimbursementItemWorkFlowStatus
        .setReimbursementItem(
            reimbursementItemService.findByReimbursementItemByReimburesementItemId(
                rmbItemWorkFlowStatusDto.getReimbursementItemId()));
    reimbursementItemWorkFlowStatus.setStatus(rmbItemWorkFlowStatusDto.getStatus());
    reimbursementItemWorkFlowStatus
        .setUserMaster(userService.findByEmail(rmbItemWorkFlowStatusDto.getUserEmail()));
    reimbursementItemWorkFlowStatus.setRemarks(rmbItemWorkFlowStatusDto.getRemarks());
      reimbursementItemWorkFlowStatus.setCreatedAt(new Date());
      reimbursementItemWorkFlowStatus.setCreatedBy("System");
      reimbursementItemWorkFlowStatus.setMarkForDelete(false);
    ReimbursementItemWorkFlowStatus result =
        rmbItemWorkFlowStatusRepository.save(reimbursementItemWorkFlowStatus);
    CommonUtils.checkError(result != null, Error.RMB_ITEM_WORK_FLOW_STATUS_NOT_SAVED);
    return result;
  }
}
