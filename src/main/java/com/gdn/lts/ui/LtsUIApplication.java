/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.gdn.lts.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class LtsUIApplication {

    public static void main(String[] args) {
        SpringApplication.run(LtsUIApplication.class, args);
    }

}
