package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.UserTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Foram Shah on 23/06/18
 */
@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeMaster, Long> {

    UserTypeMaster findByUserTypeCode(String userTypeCode);
}
