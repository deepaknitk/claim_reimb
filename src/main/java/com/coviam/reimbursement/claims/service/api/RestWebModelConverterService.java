package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.CurrencyResponseDto;
import com.coviam.reimbursement.claims.model.base.ExpenseTypeDto;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemDto;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
public interface RestWebModelConverterService {

  BaseRestResponse<List<CurrencyResponseDto>> convertCurrencyResponse(List<Currency> currencies);

  BaseRestResponse<List<ExpenseTypeDto>> convertExpenseResponse(List<ExpenseType> expenseTypes);

  BaseRestResponse<ReimbursementItemDto> convertRmbItemToRmbItemDto(ReimbursementItem rmbItem);
}
