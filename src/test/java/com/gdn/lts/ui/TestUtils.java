package com.gdn.lts.ui;

import java.util.Date;

import com.gdn.lts.backend.api.web.model.request.RFQItemWebRequest;
import com.gdn.lts.backend.api.web.model.request.RFQWebRequest;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public interface TestUtils {

    Date DATE = new Date();
    String EMAIL = "test@email.com";
    String PHONE_NUMBER = "1234567890";
    String USERNAME = "userName";
    String REQUEST_ID = "requestId";
    String CLIENT_ID = "clientId";
    String CHANNEL_ID = "channelId";
    String STORE_ID = "10001";
    String ID = "1";
    String FILE_SIZE_MB = "25MB";
    String FILE_NAME = "file_name.png";

  static RFQWebRequest getRFQWebRequest() {
    RFQWebRequest request = new RFQWebRequest();
    request.setRfqTypeId(11L);
    request.setPaymentMethodId(51L);
    request.setRfqDeliveryAddress("Delivery Address");
    request.setRfqDeliveryDate(new Date());
    return request;
  }
  
  static RFQItemWebRequest getRFQItemWebRequest() {
    RFQItemWebRequest rfqItemWebRequest = new RFQItemWebRequest();
    rfqItemWebRequest.setRfqItemId(Long.valueOf(ID));
    rfqItemWebRequest.setRfqItemFileName(FILE_NAME);
    return rfqItemWebRequest;
  }

}
