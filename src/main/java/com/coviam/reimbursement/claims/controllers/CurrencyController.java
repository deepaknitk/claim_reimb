package com.coviam.reimbursement.claims.controllers;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.CurrencyResponseDto;
import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.service.api.CurrencyService;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
@Slf4j
@RestController
@RequestMapping(value = ClaimReimbursementApiPath.CURRENCY_BASE_PATH)
public class CurrencyController {

  @Autowired
  private CurrencyService currencyService;

  @Autowired
  private RestWebModelConverterService restWebModelConverterService;

  @RequestMapping(value = {ClaimReimbursementApiPath.FIND_ALL}, method = {RequestMethod.GET},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseBody
  public BaseRestResponse<List<CurrencyResponseDto>> getAllCurrencyType() throws Exception {
    try {
      List<Currency> currency =
          this.currencyService.findAllCurrencies();

      return this.restWebModelConverterService.convertCurrencyResponse(currency);
    } catch (Exception e) {
      return new BaseRestResponse<>(Error.CONVERTING_EXCEPTION.getMessage(),
          Error.CONVERTING_EXCEPTION.getCode(), false, null);
    }
  }
}
