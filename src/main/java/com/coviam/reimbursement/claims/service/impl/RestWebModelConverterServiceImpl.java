package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.CurrencyResponseDto;
import com.coviam.reimbursement.claims.model.base.ExpenseTypeDto;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemDto;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import com.sun.org.apache.regexp.internal.RE;
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

  @Override
  public BaseRestResponse<List<ExpenseTypeDto>> convertExpenseResponse(
      List<ExpenseType> expenseTypes) {

    List<ExpenseTypeDto> expenseTypeDtos = expenseTypes.stream().filter(Objects::nonNull)
        .map(response -> convertExpenseType(response)).collect(Collectors.toList());
    return new BaseRestResponse<>(true, expenseTypeDtos);
  }

  public ExpenseTypeDto convertExpenseType(ExpenseType expenseType) {
    return new ExpenseTypeDto(expenseType.getExpenseTypeCode(),
        expenseType.getExpenseTypeDescription());
  }

  @Override
  public BaseRestResponse<ReimbursementItemDto> convertRmbItemToRmbItemDto(
      ReimbursementItem rmbItem) {
    ReimbursementItemDto reimbursementItemDto = new ReimbursementItemDto();
    reimbursementItemDto.setCurrency(rmbItem.getCurrency());
    reimbursementItemDto.setExpenseType(rmbItem.getExpenseType());
    reimbursementItemDto.setItemStatus(rmbItem.getItemStatus());
    reimbursementItemDto.setRfqItemDescription(rmbItem.getRfqItemDescription());
    reimbursementItemDto.setRmbItemFilename(rmbItem.getRmbItemFilename());
    reimbursementItemDto.setRmbItemAmount(rmbItem.getRmbItemAmount());
    reimbursementItemDto.setRmbItemBillNumber(rmbItem.getRmbItemBillNumber());
    reimbursementItemDto.setRemarks(rmbItem.getRmbItemRemarks());
    return new BaseRestResponse<>(true, reimbursementItemDto);
  }
}
