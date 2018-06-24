package com.coviam.reimbursement.claims.controllers;

import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import com.coviam.reimbursement.claims.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ClaimReimbursementApiPath.USER)
public class UserController {

    @Autowired
    private UserService userService;
//
//    public BaseRestResponse<> findByEmail(@RequestParam String userEmail){
//
//        userService.findByEmail(userEmail);
//
//        return new BaseRestResponse()
//    }


}
