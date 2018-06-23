package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.ReimbursementItemStatusLog;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemStatusLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
@Service
public class ReimbursementItemStatusLogServiceImpl implements ReimbursementItemStatusLogService {

  @Autowired
  private ReimbursementItemStatusLogService reimbursementItemStatusLogService;

  @Override
  public List<ReimbursementItemStatusLog> findAll() {
    return reimbursementItemStatusLogService.findAll();
  }
}
