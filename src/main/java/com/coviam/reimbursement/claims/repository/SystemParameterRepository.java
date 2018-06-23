package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.SystemParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemParameterRepository extends JpaRepository<SystemParameter, Long> {

    boolean existsByParameterName(String parameterName);

    String findByParameterName(String parameterName);
}
