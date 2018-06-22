/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.gdn.lts.ui.outbound.feign;

import java.util.Arrays;
import java.util.List;

import com.gdn.lts.backend.api.web.model.request.RFQItemWebRequest;
import com.gdn.lts.backend.api.web.model.request.RFQWebRequest;
import com.gdn.lts.backend.api.web.model.response.RFQItemResponse;
import com.gdn.lts.backend.api.web.model.response.RFQResponse;
import com.gdn.lts.backend.api.web.model.response.RFQTypeResponse;
import com.gdn.lts.backend.api.web.model.response.SystemParameterWebResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;
import com.gdn.lts.backend.api.web.model.response.MasterDataResponse;

import rx.Single;

/**
 * @author nsrikantaiah
 */
@Component
public class LtsBackendFeignFallback implements LtsBackendFeign {

  @Override
  public Single<BaseRestResponse<MasterDataResponse>> getMasterDataResponse(
      @RequestParam("userName") String userName) {
    BaseRestResponse<MasterDataResponse> fallbackResponse =
        new BaseRestResponse<MasterDataResponse>("FALLBACK", "Fallback", false);
    return Single.just(fallbackResponse);
  }

  @Override
  public Single<BaseRestResponse<RFQResponse>> saveRFQWithDealer(@RequestParam String userName,
      @RequestBody RFQWebRequest rfqWebRequest) {
    BaseRestResponse<RFQResponse> fallbackResponse =
        new BaseRestResponse<RFQResponse>("FALLBACK", "Fallback", false);
    return Single.just(fallbackResponse);
  }

  @Override
  public Single<BaseRestResponse<List<String>>> getUserTypeByEmail(@RequestParam String email,
      @RequestParam("userName") String userName) {
    BaseRestResponse<List<String>> fallbackResponse =
        new BaseRestResponse("Fallback", "FALLBACK", false, Arrays.asList("ANONYMOUS"), null);
    return Single.just(fallbackResponse);
  }

  @Override
  public Single<BaseRestResponse<SystemParameterWebResponse>> findByParameterName(
      @RequestParam(name = "userName", required = false,
          defaultValue = "lts-ui-user") String userName,
      @RequestParam("parameterName") String parameterName) {
    BaseRestResponse<SystemParameterWebResponse> fallbackResponse =
        new BaseRestResponse<>("FALLBACK", "Fallback", false);
    return Single.just(fallbackResponse);
  }

  @Override
  public Single<BaseRestResponse<RFQTypeResponse>> findByRFQTypeId(
      @RequestParam(name = "userName", required = false,
          defaultValue = "lts-ui-user") String userName,
      @RequestParam("rfqTypeId") String rfqTypeId) {
    BaseRestResponse<RFQTypeResponse> fallbackResponse =
        new BaseRestResponse("Fallback", "FALLBACK", false, null, null);
    return Single.just(fallbackResponse);
  }

  @Override
  public Single<BaseRestResponse<RFQResponse>> activateRFQ(@RequestParam(name = "userName",
      required = false, defaultValue = "lts-ui-user") String userName,
      @RequestParam("rfqId") String rfqId) {
    BaseRestResponse<RFQResponse> fallbackResponse =
        new BaseRestResponse("Fallback", "FALLBACK", false, null, null);
    return Single.just(fallbackResponse);
  }

  @Override
  public Single<BaseRestResponse<RFQItemResponse>> updateRFQItem(
      @RequestParam(name = "userName", required = false,
          defaultValue = "lts-ui-user") String userName,
      @RequestBody RFQItemWebRequest rfqItemWebRequest) {
    BaseRestResponse<RFQItemResponse> fallbackResponse =
        new BaseRestResponse("Fallback", "FALLBACK", false, null, null);
    return Single.just(fallbackResponse);
  }


}
