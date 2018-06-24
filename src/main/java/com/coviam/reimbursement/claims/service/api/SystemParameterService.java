package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.SystemParameter;
import com.coviam.reimbursement.claims.model.base.AuthenticationKeysDto;

public interface SystemParameterService {

  String findByParameterName(String parameterName);

  AuthenticationKeysDto getAuthKeys();
}
