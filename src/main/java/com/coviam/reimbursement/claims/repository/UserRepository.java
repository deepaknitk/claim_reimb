package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Foram Shah on 23/06/18
 */
@Repository
public interface UserRepository extends JpaRepository<UserMaster, Long> {
    UserMaster findByUserEmailAndMarkForDeleteFalse(String userEmail);

    UserMaster findByEmployeeIdAndMarkForDeleteFalse(String employeeId);
}
