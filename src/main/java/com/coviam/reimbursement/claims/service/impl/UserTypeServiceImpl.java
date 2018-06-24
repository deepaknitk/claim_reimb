package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.repository.UserTypeRepository;
import com.coviam.reimbursement.claims.service.api.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Foram Shah on 23/06/18
 */
@Service
public class UserTypeServiceImpl implements UserTypeService {

  @Autowired
  private UserTypeRepository userTypeRepository;
}
