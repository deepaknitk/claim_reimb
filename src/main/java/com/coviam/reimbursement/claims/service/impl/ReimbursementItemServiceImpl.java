package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Reimbursement;
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
  public List<ReimbursementItem> saveOrUpdate(List<ReimbursementItem> reimbursementItemList){
    return reimbursementItemRepository.save(reimbursementItemList);
  }

  @Override
  public ReimbursementItem findByReimbursementItemByReimburesementItemId(Long rmbItemId) {

      ReimbursementItem reimbursementItem = reimbursementItemRepository.findByReimbursementItemId(rmbItemId);
      Long reimbursementId = reimbursementItem.getReimbursement().getReimbursementId();
      String  description = reimbursementItem.getReimbursement().getStatusId().getStatusDescription();
    return reimbursementItemRepository.findByReimbursementItemId(rmbItemId);
  }
}
