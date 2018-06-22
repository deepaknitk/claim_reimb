package com.gdn.lts.ui.outbound.feign;

import java.util.Arrays;
import java.util.List;

import com.gdn.lts.ui.TestUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;
import com.gdn.lts.ui.config.SecurityConfig;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LtsBackendFeignTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8082);

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private LtsBackendFeign ltsBackendFeign;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getUserTypeByEmailTest() throws JsonProcessingException {

        BaseRestResponse<List<String>> restResponse =
            new BaseRestResponse<>(true, Arrays.asList("ANONYMOUS"));

        WireMock.stubFor(WireMock.get(WireMock
            .urlPathEqualTo("/lts-backend/api/user/getUserTypeByEmail"))
            .withHeader("Accept", WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE)).willReturn(
                WireMock.aResponse().withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withBody(new ObjectMapper().writeValueAsString(restResponse))));

        BaseRestResponse<List<String>> result =
            this.ltsBackendFeign.getUserTypeByEmail(TestUtils.EMAIL, TestUtils.EMAIL)
                .toBlocking().value();

        Assert.assertTrue(result.isSuccess());
        Assert.assertNotNull(result.getData());
    }

}
