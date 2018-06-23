package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Status;

import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.repository.StatusRepository;
import com.coviam.reimbursement.claims.service.api.StatusService;
import com.coviam.reimbursement.claims.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Status findByStatusCode(String statusCode){
        CommonUtils.checkError(statusCode != null, Error.STATUS_CODE_NULL);
        Status status = statusRepository.findByStatusCodeAndMarkForDeleteFalse(statusCode);
        CommonUtils.checkError(status != null, Error.STATUS_NULL);

        return status;
    }
}
