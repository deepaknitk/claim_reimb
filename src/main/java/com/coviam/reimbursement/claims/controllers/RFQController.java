/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE: All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */
package com.coviam.reimbursement.claims.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coviam.reimbursement.claims.outbound.feign.LtsBackendFeign;
import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;
import com.gdn.lts.backend.api.web.model.request.RFQWebRequest;
import com.gdn.lts.backend.api.web.model.response.MasterDataResponse;
import com.gdn.lts.backend.api.web.model.response.RFQResponse;
import com.gdn.lts.backend.master.model.constants.LtsApiPath;

import rx.Single;

/**
 * @author nsrikantaiah
 *
 */
@Controller
@RequestMapping(value = LtsApiPath.RFQ)
public class RFQController {

  @Autowired
  private LtsBackendFeign ltsBackendFeign;

  /**
   * UI end-point to get the masterDataResponse
   * @param userName
   * @return
   */
  @RequestMapping(value = LtsApiPath.GET_MASTER_DATA, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<BaseRestResponse<MasterDataResponse>> getMasterDataResponse(
      @RequestParam(required = false, defaultValue = "lts-ui-user") String userName) {
    return ltsBackendFeign.getMasterDataResponse(userName);
  }
  
  /**
   * UI end-point to make a request to save the RFQ
   * @param userName
   * @param rfqWebRequest
   * @return
   */
  @RequestMapping(value = LtsApiPath.CREATE, method = RequestMethod.POST,
      consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public Single<BaseRestResponse<RFQResponse>> saveRFQWithDealer(
      @RequestParam(required = false, defaultValue = "lts-ui-user") String userName,
      @RequestBody RFQWebRequest rfqWebRequest) {
    return ltsBackendFeign.saveRFQWithDealer(userName, rfqWebRequest);
  }
  

}
