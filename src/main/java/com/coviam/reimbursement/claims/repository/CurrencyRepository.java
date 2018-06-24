package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Foram Shah on 23/06/18
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
