package com.gdn.lts.ui.outbound.feign;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class LtsBackendFeignFallbackFactoryTest {

    @InjectMocks
    private LtsBackendFeignFallbackFactory ltsBackendFeignFallbackFactory;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void setterFactoryTest() {
        this.ltsBackendFeignFallbackFactory.create(new Exception());
    }
}
