package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.CurrencyResponseDto;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
public interface RestWebModelConverterService {

  BaseRestResponse<List<CurrencyResponseDto>> convertCurrencyResponse(List<Currency> currencies);
}
