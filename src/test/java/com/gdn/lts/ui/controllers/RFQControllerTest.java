/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved NOTICE: All information
 * contained herein is, and remains the property of PT Global Digital Niaga. Dissemination of this
 * information or reproduction of this material is strictly forbidden.
 */
package com.gdn.lts.ui.controllers;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;
import com.gdn.lts.backend.api.web.model.request.RFQWebRequest;
import com.gdn.lts.backend.api.web.model.response.MasterDataResponse;
import com.gdn.lts.backend.master.model.constants.LtsApiPath;
import com.gdn.lts.ui.TestUtils;
import com.gdn.lts.ui.outbound.feign.LtsBackendFeign;

import rx.Single;

/**
 * @author nsrikantaiah
 */
public class RFQControllerTest {

  @InjectMocks
  private RFQController rfqController;

  @Mock
  private LtsBackendFeign ltsBackendFeign;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper = new ObjectMapper();
  private BaseRestResponse<MasterDataResponse> restResponse;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(this.rfqController).build();

    MasterDataResponse masterDataResponse = new MasterDataResponse();
    masterDataResponse.setMaximumLineItemsAllowed(15);
    restResponse = new BaseRestResponse<>(true, masterDataResponse);

    Mockito.when(this.ltsBackendFeign.getMasterDataResponse(TestUtils.USERNAME))
        .thenReturn(Single.just(restResponse));

    ReflectionTestUtils.setField(this.rfqController, "ltsBackendFeign", this.ltsBackendFeign);
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(this.ltsBackendFeign);
  }

  @Test
  public void test_getMasterDataResponse_Success() throws Exception {
    this.mockMvc.perform(
        get(LtsApiPath.RFQ + LtsApiPath.GET_MASTER_DATA).param("userName", TestUtils.USERNAME)
            .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());

    Mockito.verify(this.ltsBackendFeign).getMasterDataResponse(TestUtils.USERNAME);
  }

  @Test
  public void test_saveRFQWithDealer_Success() throws Exception {
    RFQWebRequest rfqRequest = TestUtils.getRFQWebRequest();

    this.mockMvc
        .perform(post(LtsApiPath.RFQ + LtsApiPath.CREATE).param("userName", TestUtils.USERNAME)
            .content(this.objectMapper.writeValueAsString(rfqRequest))
            .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());

    Mockito.verify(this.ltsBackendFeign).saveRFQWithDealer(TestUtils.USERNAME, rfqRequest);
  }
}
