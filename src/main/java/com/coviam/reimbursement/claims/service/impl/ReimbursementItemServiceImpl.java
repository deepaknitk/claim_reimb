package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemDto;
import com.coviam.reimbursement.claims.repository.ReimbursementItemRepository;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import com.coviam.reimbursement.claims.service.api.ReimbursementService;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
@Service
public class ReimbursementItemServiceImpl implements ReimbursementItemService {

  @Autowired
  private ReimbursementItemRepository reimbursementItemRepository;

  @Autowired
  private ReimbursementService reimbursementService;


  @Autowired
  private RestWebModelConverterService restWebModelConverterService;

  @Override
  public List<ReimbursementItem> saveOrUpdate(List<ReimbursementItem> reimbursementItemList) {
    return reimbursementItemRepository.save(reimbursementItemList);
  }

  @Override
  public ReimbursementItem findByReimbursementItemByReimburesementItemId(Long rmbItemId) {
    return reimbursementItemRepository.findByReimbursementItemId(rmbItemId);
  }

  @Override
  public List<ReimbursementItemDto> findByReimbursementItemListByReimburesementItemId(
      Long rmbItemId) {

    List<ReimbursementItem> reimbursementItems = reimbursementItemRepository
        .findByReimbursement(reimbursementService.findReimburesementByRmbItemId(rmbItemId));
    List<ReimbursementItemDto> reimbursementItemDtos = new ArrayList<>();
    for (ReimbursementItem reimbursementItem : reimbursementItems) {
      reimbursementItemDtos
          .add(restWebModelConverterService.convertRmbItemToRmbItemDto(reimbursementItem));
    }
    return reimbursementItemDtos;
  }

  @Override
  public ReimbursementItem update(ReimbursementItem reimbursementItem) {
    return reimbursementItemRepository.save(reimbursementItem);
  }
}
