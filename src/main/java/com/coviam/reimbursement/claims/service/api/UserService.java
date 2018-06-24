package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.UserMaster;

/**
 * @author Foram Shah on 23/06/18
 */
public interface UserService {

    UserMaster findByUserMailId(String userMailId);
}
