package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Long> {
   Page<Reimbursement> findByUserIdAndMarkForDeleteFalse(Long userId, Pageable pageable);

}
