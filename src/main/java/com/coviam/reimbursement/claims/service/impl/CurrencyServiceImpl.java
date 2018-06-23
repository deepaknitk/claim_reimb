package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.repository.CurrencyRepository;
import com.coviam.reimbursement.claims.service.api.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

  @Autowired
  private CurrencyRepository currencyRepository;

  @Override public List<Currency> findAllCurrencies() {
    return currencyRepository.findAll();
  }
}
