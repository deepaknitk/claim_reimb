/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.gdn.lts.ui.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
@Data
@Configuration
@ConfigurationProperties(value = "ltsbackend.client")
public class LtsBackendClientParamProperties {

    private String storeId;
    private String clientId;
    private String channelId;
    
}
