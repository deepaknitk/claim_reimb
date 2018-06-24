package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.repository.ReimbursementItemRepository;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
@Service
public class ReimbursementItemServiceImpl implements ReimbursementItemService {

  @Autowired
  private ReimbursementItemRepository reimbursementItemRepository;

  @Override
  public ReimbursementItem saveOrUpdate(ReimbursementItem reimbursementItem) {
    return reimbursementItemRepository.save(reimbursementItem);
  }

  @Override
  public List<ReimbursementItem> findByReimbursementItemByReimburesementId(Long rmbId) {
   // return reimbursementItemRepository.findByReimbursementId(rmbId);
    return null;
  }

  @Override
  public ReimbursementItem findByReimbursementItemByReimburesementItemId(Long rmbItemId) {
    return reimbursementItemRepository.findByReimbursementItemId(rmbItemId);
  }
}
