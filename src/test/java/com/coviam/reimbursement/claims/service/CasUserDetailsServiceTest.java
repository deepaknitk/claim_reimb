package com.coviam.reimbursement.claims.service;

import java.util.Arrays;
import java.util.List;

import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.jasig.cas.client.validation.AssertionImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.coviam.reimbursement.claims.TestUtils;
import com.coviam.reimbursement.claims.outbound.feign.LtsBackendFeign;
import com.coviam.reimbursement.claims.service.CasUserDetailsService;
import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;

import rx.Single;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class CasUserDetailsServiceTest {

    @InjectMocks
    private CasUserDetailsService casUserDetailsService;

    @Mock
    private LtsBackendFeign ltsBackendFeign;

    private BaseRestResponse<List<String>> baseRestResponse1;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        this.baseRestResponse1 = new BaseRestResponse<>(true, Arrays.asList("ANONYMOUS"));

        Mockito.when(
            this.ltsBackendFeign.getUserTypeByEmail(TestUtils.USERNAME, TestUtils.USERNAME))
            .thenReturn(Single.just(this.baseRestResponse1));
    }

    @After
    public void tearDown() {

    }

    @Test
    public void loadUserDetailsTest() {
        this.casUserDetailsService
            .loadUserDetails(new AssertionImpl(new AttributePrincipalImpl(TestUtils.USERNAME)));

        Mockito.verify(this.ltsBackendFeign)
            .getUserTypeByEmail(TestUtils.USERNAME, TestUtils.USERNAME);
    }
}
