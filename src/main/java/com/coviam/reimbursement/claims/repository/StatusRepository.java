package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByStatusCodeAndMarkForDeleteFalse(String statusCode);
}
