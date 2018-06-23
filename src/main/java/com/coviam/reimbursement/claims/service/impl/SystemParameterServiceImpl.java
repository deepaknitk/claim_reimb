package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.SystemParameter;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.repository.SystemParameterRepository;
import com.coviam.reimbursement.claims.service.api.SystemParameterService;
import com.coviam.reimbursement.claims.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemParameterServiceImpl implements SystemParameterService {

    @Autowired
    private SystemParameterRepository systemParameterRepository;

    @Override
    public String findByParameterName(String parameterName){
        CommonUtils.checkError(parameterName != null , Error.PARAMETER_NAME_NULL);
        boolean parameterNameExists = systemParameterRepository.existsByParameterName(parameterName);
        CommonUtils.checkError(parameterNameExists != false, Error.PARAMETER_NAME_INVALID);
        String parameterValue = systemParameterRepository.findByParameterName(parameterName);
        CommonUtils.checkError(parameterValue != null, Error.PARAMETER_NAME_NOT_FOUND);

        return parameterValue;
    }
}
