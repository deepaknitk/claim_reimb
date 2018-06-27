package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.entity.ReimbursementItemStatusLog;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemStatusLogDto;
import com.coviam.reimbursement.claims.repository.ReimbursementItemRepository;
import com.coviam.reimbursement.claims.repository.ReimbursementItemStatusLogRepository;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemStatusLogService;
import com.coviam.reimbursement.claims.service.api.StatusService;
import com.coviam.reimbursement.claims.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
@Service
public class ReimbursementItemStatusLogServiceImpl implements ReimbursementItemStatusLogService {

  @Autowired
  private ReimbursementItemStatusLogRepository reimbursementItemStatusLogRepository;

  @Autowired
  private StatusService statusService;

  @Autowired
  private ReimbursementItemService reimbursementItemService;

  @Override
  public ReimbursementItemStatusLog save(
      ReimbursementItemStatusLogDto reimbursementItemStatusLogDto) {
    ReimbursementItemStatusLog reimbursementItemStatusLog = new ReimbursementItemStatusLog();
    reimbursementItemStatusLog.setNewStatus(reimbursementItemStatusLogDto.getNewStatusCode());
    reimbursementItemStatusLog.setOldStatus(reimbursementItemStatusLogDto.getOldStatusCode());
    reimbursementItemStatusLog.setReimbursementItem(
        reimbursementItemService.findByReimbursementItemByReimburesementItemId(
            reimbursementItemStatusLogDto.getReimbursementItemId()));
      reimbursementItemStatusLog.setCreatedAt(new Date());
      reimbursementItemStatusLog.setCreatedBy("System");
      reimbursementItemStatusLog.setMarkForDelete(false);
    return reimbursementItemStatusLogRepository.save(reimbursementItemStatusLog);

  }
}
