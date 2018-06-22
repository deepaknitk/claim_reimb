package com.gdn.lts.ui.outbound.feign;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.gdn.lts.backend.api.web.model.request.RFQWebRequest;
import com.gdn.lts.backend.master.model.constants.SystemParameterNames;
import com.gdn.lts.ui.TestUtils;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class LtsBackendFeignFallbackTest {

  @InjectMocks
  private LtsBackendFeignFallback ltsBackendFeignFallback;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() {}

  @Test
  public void getMasterDataTest() {
    Assert.assertEquals(false, this.ltsBackendFeignFallback
        .getMasterDataResponse(TestUtils.USERNAME).toBlocking().value().isSuccess());
  }

  @Test
  public void saveRFQWithDealerTest() {
    Assert.assertEquals(false,
        this.ltsBackendFeignFallback.saveRFQWithDealer(TestUtils.USERNAME, new RFQWebRequest())
            .toBlocking().value().isSuccess());
  }

  @Test
  public void getUserTypeByEmailTest() {
    Assert.assertEquals(false, this.ltsBackendFeignFallback
        .getUserTypeByEmail(TestUtils.EMAIL, TestUtils.USERNAME).toBlocking().value().isSuccess());
  }

  @Test
  public void findByParameterNameTest() {
    Assert.assertEquals(false,
        this.ltsBackendFeignFallback
            .findByParameterName(TestUtils.USERNAME, SystemParameterNames.FILE_UPLOAD_FOLDER_PATH)
            .toBlocking().value().isSuccess());
  }

  @Test
  public void findByRFQTypeIdTest() {
    Assert.assertEquals(false, this.ltsBackendFeignFallback
        .findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID).toBlocking().value().isSuccess());
  }

  @Test
  public void activateRFQTest() {
    Assert.assertEquals(false, this.ltsBackendFeignFallback
        .activateRFQ(TestUtils.USERNAME, TestUtils.ID).toBlocking().value().isSuccess());
  }

  @Test
  public void updateRFQItemTest() {
    Assert.assertEquals(false,
        this.ltsBackendFeignFallback
            .updateRFQItem(TestUtils.USERNAME, TestUtils.getRFQItemWebRequest()).toBlocking()
            .value().isSuccess());
  }

}
