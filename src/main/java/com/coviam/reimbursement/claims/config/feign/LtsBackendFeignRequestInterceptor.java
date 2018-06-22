/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.config.feign;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coviam.reimbursement.claims.config.properties.LtsBackendClientParamProperties;
import com.coviam.reimbursement.claims.model.constants.Constants;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author nsrikantaiah
 */
@Component
public class LtsBackendFeignRequestInterceptor implements RequestInterceptor {

  @Autowired
  private LtsBackendClientParamProperties clientParamProperties;

  public LtsBackendFeignRequestInterceptor(LtsBackendClientParamProperties clientParamProperties) {
    this.clientParamProperties = clientParamProperties;
  }

  @Override
  public void apply(RequestTemplate template) {
    template.query(true, Constants.STORE_ID, clientParamProperties.getStoreId())
        .query(true, Constants.CHANNEL_ID, clientParamProperties.getChannelId())
        .query(true, Constants.CLIENT_ID, clientParamProperties.getClientId())
        .query(true, Constants.REQUEST_ID, UUID.randomUUID().toString());
  }
}
