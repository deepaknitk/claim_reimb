package com.coviam.reimbursement.claims.controllers;

import com.coviam.reimbursement.claims.model.base.AuthenticationKeysDto;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.service.api.SystemParameterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Foram Shah on 23/06/18
 */
@Slf4j
@RestController
@RequestMapping(ClaimReimbursementApiPath.USER)
public class AuthenticationController {

  @Autowired
  private SystemParameterService systemParameterService;

  @RequestMapping(value = {ClaimReimbursementApiPath.GET_AUTHENTICATION_KEYS},
      method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseBody
  public BaseRestResponse<AuthenticationKeysDto> getAuthenticationKeys() throws Exception {
    try {
      AuthenticationKeysDto authenticationKeysDto = systemParameterService.getAuthKeys();
      return new BaseRestResponse<>(true, authenticationKeysDto);
    } catch (Exception e) {
      return new BaseRestResponse<>(Error.SYSTEM_ERROR.getMessage(), Error.SYSTEM_ERROR.getCode(),
          false, null);
    }
  }
}
