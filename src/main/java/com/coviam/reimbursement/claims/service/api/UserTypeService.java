package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.UserTypeMaster;

/**
 * @author Foram Shah on 23/06/18
 */
public interface UserTypeService {

    UserTypeMaster findByUserTypeCode(String userTypeCode);

    UserTypeMaster findByUserTypeId(Long userTypeId);
}
