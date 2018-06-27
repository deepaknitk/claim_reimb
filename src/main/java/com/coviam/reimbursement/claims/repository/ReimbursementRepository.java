package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.Status;
import com.coviam.reimbursement.claims.entity.UserMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementRepository extends JpaRepository<Reimbursement, Long> {

   List<Reimbursement> findByUserIdAndMarkForDeleteFalse(UserMaster user);

//    Page<Reimbursement> findByStatusCodeAndMarkForDeleteFalse(String statusCode);

//    @Query(value = "select * from reimbursement where status_id = 1 or status_id = 3", nativeQuery = true)
//    Page<Reimbursement> findAllForFinance();
//
//    @Query(value = "select * from reimbursement where status_id = 2", nativeQuery = true)
//    Page<Reimbursement> findAllForAdmin(Pageable pageable);

    List<Reimbursement> findByStatusId(Status status);
}
