package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.SystemParameter;

public interface SystemParameterService {

    String findByParameterName(String parameterName);
}
