/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.outbound.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nsrikantaiah
 */
@Component
@Slf4j
public class LtsBackendFeignFallbackFactory implements FallbackFactory<LtsBackendFeign> {

  @Autowired
  private LtsBackendFeignFallback fallback;

  @Override
  public LtsBackendFeign create(Throwable throwable) {
    log.error("Fallback Error:{} ", throwable.toString());
    return fallback;
  }
}
