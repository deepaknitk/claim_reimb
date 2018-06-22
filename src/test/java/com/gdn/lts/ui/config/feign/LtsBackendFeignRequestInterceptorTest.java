package com.gdn.lts.ui.config.feign;

import com.gdn.lts.ui.TestUtils;
import com.gdn.lts.ui.config.properties.LtsBackendClientParamProperties;
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
