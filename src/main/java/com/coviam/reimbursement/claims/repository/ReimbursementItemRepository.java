package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
@Repository
public interface ReimbursementItemRepository extends JpaRepository<ReimbursementItem, Long> {

  ReimbursementItem findByReimbursementItemId(Long rmbItemId);

  List<ReimbursementItem> findByReimbursement(Reimbursement reimbursement);
}
