package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
public interface ReimbursementItemService {

  ReimbursementItem saveOrUpdate(ReimbursementItem reimbursementItemList);

  List<ReimbursementItem> findByReimbursementItemByReimburesementId(Long rmbId);

  ReimbursementItem findByReimbursementItemByReimburesementItemId(Long rmbItemId);
}
