package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
public interface ReimbursementItemRepository extends JpaRepository<ReimbursementItem, Long> {

  /**
   * @param rmbId rmb id for which we need to find the object
   * @returnlist of rmb items
   */
  List<ReimbursementItem> findByReimbursementId(Long rmbId);
}
