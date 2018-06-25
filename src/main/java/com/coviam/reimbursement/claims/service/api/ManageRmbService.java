package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.model.base.ManageReimbursementRequest;

/**
 * @author Foram Shah on 24/06/18
 */
public interface ManageRmbService {

  void manageClaims(ManageReimbursementRequest manageReimbursementRequest);
}
