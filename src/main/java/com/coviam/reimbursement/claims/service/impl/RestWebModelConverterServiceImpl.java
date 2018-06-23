package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.CurrencyResponseDto;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Foram Shah on 23/06/18
 */
@Service
public class RestWebModelConverterServiceImpl implements RestWebModelConverterService {

  @Override
  public BaseRestResponse<List<CurrencyResponseDto>> convertCurrencyResponse(
      List<Currency> currencies) {

    List<CurrencyResponseDto> currencyResponseDtos = currencies.stream().filter(Objects::nonNull)
        .map(response -> convertCurrency(response)).collect(Collectors.toList());
      return new BaseRestResponse<>(true, currencyResponseDtos);
  }

  public CurrencyResponseDto convertCurrency(Currency currency) {
    return new CurrencyResponseDto(currency.getCurrencyCode(), currency.getCurrencyDesc());
  }
}
