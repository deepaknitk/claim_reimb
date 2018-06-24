package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemDto;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
public interface ReimbursementItemService {

  List<ReimbursementItem> saveOrUpdate(List<ReimbursementItem> reimbursementItemList);

  List<ReimbursementItemDto> findByReimbursementItemListByReimburesementItemId(Long rmbItemId);

  ReimbursementItem findByReimbursementItemByReimburesementItemId(Long rmbItemId);
}
