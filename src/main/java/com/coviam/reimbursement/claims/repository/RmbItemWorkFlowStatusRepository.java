package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.ReimbursementItemWorkFlowStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RmbItemWorkFlowStatusRepository extends JpaRepository<ReimbursementItemWorkFlowStatus, Long> {
}
