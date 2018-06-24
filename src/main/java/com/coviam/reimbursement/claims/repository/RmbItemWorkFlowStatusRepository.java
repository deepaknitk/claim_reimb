package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.ReimbursementItemWorkFlowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Foram Shah on 24/06/18
 */

@Repository
public interface RmbItemWorkFlowStatusRepository
    extends JpaRepository<ReimbursementItemWorkFlowStatus, Long> {
}
