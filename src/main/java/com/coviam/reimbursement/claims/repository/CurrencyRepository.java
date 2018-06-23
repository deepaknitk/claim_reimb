package com.coviam.reimbursement.claims.repository;

import com.coviam.reimbursement.claims.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Foram Shah on 23/06/18
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
