package com.coviam.reimbursement.claims.controllers;

import com.coviam.reimbursement.claims.entity.UserMaster;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.UserWebRequest;
import com.coviam.reimbursement.claims.model.base.UserWebResponse;
import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.model.exceptions.BusinessException;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import com.coviam.reimbursement.claims.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(ClaimReimbursementApiPath.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestWebModelConverterService restWebModelConverterService;

    @RequestMapping(value = {ClaimReimbursementApiPath.FIND_BY_ID},
        method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseRestResponse<UserWebResponse> findByEmail(@RequestParam String userEmail){
        UserWebResponse userWebResponse = new UserWebResponse();
        try{
            UserMaster userMaster =  userService.findByEmail(userEmail);
            userWebResponse.setEmployeeId(userMaster.getEmployeeId());
        } catch (BusinessException be){
            log.error("Error in find by email : {}",be.getMessage(),be);
            return new BaseRestResponse(be.getCode(), be.getMessage(),false);
        }
        return new BaseRestResponse(true, userWebResponse);
    }

    @RequestMapping(value = {ClaimReimbursementApiPath.SAVE},
        method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseRestResponse<UserWebResponse> save(@RequestBody UserWebRequest userWebRequest) throws Exception {
        UserWebResponse userWebResponse;

        try{

            UserMaster userMaster= restWebModelConverterService.convert(userWebRequest, UserMaster.class);
            userMaster.setEmployeeId(userWebRequest.getEmployeeId());
            userMaster.setUserEmail(userWebRequest.getUserEmail());
            userMaster.setUserName(userWebRequest.getUserName());

           UserMaster result =  userService.save(userMaster);

        } catch (BusinessException be){
            log.error("Error in find by email : {}",be.getMessage(),be);
            return new BaseRestResponse(be.getCode(), be.getMessage(),false);
        } catch (Exception e){
            log.error("Error in find by email : {}",e.getMessage(),e);
            return new BaseRestResponse(Error.SYSTEM_ERROR.getCode(), Error.SYSTEM_ERROR.getMessage(),
                false);
        }

        return new BaseRestResponse(true, true);
    }
}
