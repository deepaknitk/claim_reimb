package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.dto.RmbItemWorkFlowStatusDto;
import com.coviam.reimbursement.claims.entity.ReimbursementItemWorkFlowStatus;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.repository.RmbItemWorkFlowStatusRepository;
import com.coviam.reimbursement.claims.service.api.RmbItemWorkFlowStatusService;
import com.coviam.reimbursement.claims.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class RmbItemWorkFlowStatusServiceImpl implements RmbItemWorkFlowStatusService {

    @Autowired
    private RmbItemWorkFlowStatusRepository rmbItemWorkFlowStatusRepository;

    @Override
    public  ReimbursementItemWorkFlowStatus save(RmbItemWorkFlowStatusDto rmbItemWorkFlowStatusDto){
        CommonUtils.checkError(rmbItemWorkFlowStatusDto != null, Error.RMB_ITEM_WORK_FLOW_STATUS_NULL);
        ReimbursementItemWorkFlowStatus reimbursementItemWorkFlowStatus = new ReimbursementItemWorkFlowStatus();
        reimbursementItemWorkFlowStatus.
            getReimbursementItem().setReimbursementItemId(rmbItemWorkFlowStatusDto.getReimbursementItemId());
        reimbursementItemWorkFlowStatus.getStatus().setStatusId(rmbItemWorkFlowStatusDto.getStatusId());
        reimbursementItemWorkFlowStatus.getUserMaster().setUserId(rmbItemWorkFlowStatusDto.getUserId());
        reimbursementItemWorkFlowStatus.setRemarks(rmbItemWorkFlowStatusDto.getRemarks());
        ReimbursementItemWorkFlowStatus result = rmbItemWorkFlowStatusRepository.save(reimbursementItemWorkFlowStatus);
        CommonUtils.checkError(result != null, Error.RMB_ITEM_WORK_FLOW_STATUS_NOT_SAVED);
        return result;
    }
}
