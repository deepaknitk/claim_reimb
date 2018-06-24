package com.coviam.reimbursement.claims.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Foram Shah on 23/06/18
 */
@Configuration
@ConfigurationProperties(prefix = "spring.dataSource")
@Data
public class SpringDataSourceConfig {

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String dialect;
    private String showSql;
    private String ddlAuto;
    private String minIdle;
    private String maxIdle;
    private String initialSize;
    private String maxActive;
    private String maxWait;
}
