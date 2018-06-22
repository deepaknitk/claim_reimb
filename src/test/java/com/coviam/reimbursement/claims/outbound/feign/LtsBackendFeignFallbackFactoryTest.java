package com.coviam.reimbursement.claims.outbound.feign;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.coviam.reimbursement.claims.outbound.feign.LtsBackendFeignFallbackFactory;

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
