/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE: All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.gdn.lts.ui.outbound.feign;

import java.util.List;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;
import com.gdn.lts.backend.api.web.model.request.RFQItemWebRequest;
import com.gdn.lts.backend.api.web.model.request.RFQWebRequest;
import com.gdn.lts.backend.api.web.model.response.MasterDataResponse;
import com.gdn.lts.backend.api.web.model.response.RFQItemResponse;
import com.gdn.lts.backend.api.web.model.response.RFQResponse;
import com.gdn.lts.backend.api.web.model.response.RFQTypeResponse;
import com.gdn.lts.backend.api.web.model.response.SystemParameterWebResponse;
import com.gdn.lts.backend.master.model.constants.LtsApiPath;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import rx.Single;

/**
 * @author nsrikantaiah
 */
@FeignClient(name = "ltsBackendFeign", url = "${service.endpoint.lts.backend}",
    fallbackFactory = LtsBackendFeignFallbackFactory.class,
    configuration = LtsBackendFeign.MultipartSupportConfig.class)
public interface LtsBackendFeign {

  /**
   * Feign method to invoke the /rfq/master-data-list API
   * 
   * @param userName
   * @return MasterDataResponse associated within BaseRestResponse object
   */
  @RequestMapping(value = LtsApiPath.RFQ + LtsApiPath.GET_MASTER_DATA, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  Single<BaseRestResponse<MasterDataResponse>> getMasterDataResponse(
      @RequestParam("userName") String userName);

  /**
   * Feign method to invoke the /rfq/create API
   * 
   * @param userName
   * @param rfqWebRequest
   * @return RFQResponse associated within BaseRestResponse object
   */
  @RequestMapping(value = LtsApiPath.RFQ + LtsApiPath.CREATE, method = RequestMethod.POST,
      consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  Single<BaseRestResponse<RFQResponse>> saveRFQWithDealer(@RequestParam("userName") String userName,
      @RequestBody RFQWebRequest rfqWebRequest);


  @RequestMapping(value = "/api/user/getUserTypeByEmail", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  Single<BaseRestResponse<List<String>>> getUserTypeByEmail(@RequestParam("email") String email,
      @RequestParam("userName") String userName);

  @RequestMapping(value = LtsApiPath.SYSTEM_PARAMETER + LtsApiPath.FIND_BY_PARAMETER_NAME,
      method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  Single<BaseRestResponse<SystemParameterWebResponse>> findByParameterName(
      @RequestParam(name = "userName", required = false,
          defaultValue = "lts-ui-user") String userName,
      @RequestParam("parameterName") String parameterName);

  @RequestMapping(value = LtsApiPath.RFQ_TYPE + LtsApiPath.FIND_BY_ID, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  Single<BaseRestResponse<RFQTypeResponse>> findByRFQTypeId(
      @RequestParam(name = "userName", required = false,
          defaultValue = "lts-ui-user") String userName,
      @RequestParam("rfqTypeId") String rfqTypeId);

  @RequestMapping(value = LtsApiPath.RFQ + LtsApiPath.ACTIVATE_RFQ, method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  Single<BaseRestResponse<RFQResponse>> activateRFQ(@RequestParam(name = "userName",
      required = false, defaultValue = "lts-ui-user") String userName,
      @RequestParam("rfqId") String rfqId);

  @RequestMapping(value = LtsApiPath.RFQ_ITEM + LtsApiPath.UPDATE, method = RequestMethod.POST,
      consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  Single<BaseRestResponse<RFQItemResponse>> updateRFQItem(
      @RequestParam(name = "userName", required = false,
          defaultValue = "lts-ui-user") String userName,
      @RequestBody RFQItemWebRequest rfqItemWebRequest);

  class MultipartSupportConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Encoder feignFormEncoder() {
      return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
  }

}
