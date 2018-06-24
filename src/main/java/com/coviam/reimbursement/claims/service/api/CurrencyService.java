package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Currency;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
public interface CurrencyService {

    List<Currency> findAllCurrencies();
}
