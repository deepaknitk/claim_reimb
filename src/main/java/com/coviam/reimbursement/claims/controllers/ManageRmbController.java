package com.coviam.reimbursement.claims.controllers;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.ManageReimbursementRequest;
import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import com.coviam.reimbursement.claims.request.RmbWebRequest;
import com.coviam.reimbursement.claims.service.api.ManageRmbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Foram Shah on 24/06/18
 */
@Slf4j
@RestController
@RequestMapping(value = ClaimReimbursementApiPath.MANAGE_CLAIMS)
public class ManageRmbController {

  @Autowired
  private ManageRmbService manageRmbService;

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public BaseRestResponse manageRmb(
      @Valid @RequestBody ManageReimbursementRequest manageReimbursementRequest) {
    boolean success = false;
    try {
      manageRmbService.manageClaims(manageReimbursementRequest);

      success = true;
    } catch (Exception e) {
      log.error("Error in updating the success", e.getMessage(), e);
    }
    return new BaseRestResponse<>(success);
  }
}
