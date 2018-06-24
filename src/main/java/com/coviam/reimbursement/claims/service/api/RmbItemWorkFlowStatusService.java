package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.ReimbursementItemWorkFlowStatus;
import com.coviam.reimbursement.claims.model.base.RmbItemWorkFlowStatusDto;

/**
 * @author Foram Shah on 24/06/18
 */
public interface RmbItemWorkFlowStatusService {

  ReimbursementItemWorkFlowStatus save(RmbItemWorkFlowStatusDto rmbItemWorkFlowStatusDto);
}
