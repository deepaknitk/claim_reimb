/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE: All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by payal on June, 2018
 */
@Configuration
@ComponentScans({
    @ComponentScan(basePackages = "com.coviam.reimbursement.claims.service")

})
@EnableJpaAuditing(auditorAwareRef = "stringAuditorAware")
public class ServiceConfiguration {
    @Bean
  public Mapper createDozerMapper() {
    return new DozerBeanMapper();
  }



}
