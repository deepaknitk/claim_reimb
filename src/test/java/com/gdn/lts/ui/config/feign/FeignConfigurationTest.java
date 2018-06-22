package com.gdn.lts.ui.config.feign;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestExecutionListeners;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class FeignConfigurationTest {

    @InjectMocks
    private FeignConfiguration feignConfiguration;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void setterFactoryTest() {
        this.feignConfiguration.setterFactory();
    }
}
