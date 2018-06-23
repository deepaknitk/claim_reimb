package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.repository.ReimbursementItemRepository;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
public class ReimbursementItemServiceImpl implements ReimbursementItemService {

  @Autowired
  private ReimbursementItemRepository reimbursementItemRepository;

  @Override
  public List<ReimbursementItem> saveOrUpdate(List<ReimbursementItem> reimbursementItemList) {
    return reimbursementItemRepository.save(reimbursementItemList);
  }
}
