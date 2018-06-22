package com.coviam.reimbursement.claims.config.feign;

import com.coviam.reimbursement.claims.TestUtils;
import com.coviam.reimbursement.claims.config.feign.LtsBackendFeignRequestInterceptor;
import com.coviam.reimbursement.claims.config.properties.LtsBackendClientParamProperties;

import feign.RequestTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class LtsBackendFeignRequestInterceptorTest {

    @InjectMocks
    private LtsBackendFeignRequestInterceptor feignRequestInterceptor;

    private LtsBackendClientParamProperties clientParamProperties;
    private RequestTemplate requestTemplate;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        this.clientParamProperties = new LtsBackendClientParamProperties();
        this.clientParamProperties.setClientId(TestUtils.CLIENT_ID);
        this.clientParamProperties.setStoreId(TestUtils.STORE_ID);

        this.requestTemplate = new RequestTemplate();

        ReflectionTestUtils.setField(this.feignRequestInterceptor, "clientParamProperties",
            this.clientParamProperties);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void setterFactoryTest() {
        this.feignRequestInterceptor.apply(this.requestTemplate);
    }
}
