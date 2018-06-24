package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.UserMaster;
import com.coviam.reimbursement.claims.entity.UserMaster;
import com.coviam.reimbursement.claims.model.base.UserWebRequest;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.repository.UserRepository;
import com.coviam.reimbursement.claims.service.api.UserService;
import com.coviam.reimbursement.claims.service.api.UserTypeService;
import com.coviam.reimbursement.claims.utils.ClaimUtilities;
import com.coviam.reimbursement.claims.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Foram Shah on 23/06/18
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserTypeService userTypeService;

  @Override
  public UserMaster findByEmail(String email){

      CommonUtils.checkError(StringUtils.isNotBlank(email),Error.EMAIL_ID_NULL);
      CommonUtils.checkError(ClaimUtilities.validateEmail(email), Error.EMAIL_ID_INVALID);

      log.info("find user by email: {}", email);

      UserMaster user = userRepository.findByUserEmailAndMarkForDeleteFalse(email.toLowerCase());

      CommonUtils.checkError(user!=null,Error.USER_NOT_FOUND);

      return user;
  }

  @Override
  public UserMaster save(UserMaster userMaster){
      CommonUtils.checkError( userMaster!=null, Error.USER_NULL);
      CommonUtils.checkError(userMaster.getEmployeeId() != null, Error.EMPLOYEE_ID_NULL);
      CommonUtils.checkError(userMaster.getUserEmail() != null , Error.EMAIL_ID_NULL);
      CommonUtils.checkError(userRepository.findByEmployeeIdAndMarkForDeleteFalse(userMaster.getEmployeeId()) == null,Error.EMPLOYEE_ID_INVALID);

      userMaster.setUserType(userTypeService.findByUserTypeCode("EMPLOYEE"));
      userMaster.setCreatedAt(new Date());
      userMaster.setCreatedBy("system");
      userMaster.setMarkForDelete(false);

      UserMaster result = userRepository.save(userMaster);
      CommonUtils.checkError(result != null, Error.USER_NOT_SAVED);

      return userMaster;
  }
}
