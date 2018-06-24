package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.dto.RmbItemWorkFlowStatusDto;
import com.coviam.reimbursement.claims.entity.ReimbursementItemWorkFlowStatus;

public interface RmbItemWorkFlowStatusService {

    ReimbursementItemWorkFlowStatus save(RmbItemWorkFlowStatusDto rmbItemWorkFlowStatusDto);
}
