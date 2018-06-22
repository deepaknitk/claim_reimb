package com.gdn.lts.ui.controllers;

import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;
import com.gdn.lts.backend.master.model.enums.Error;
import com.gdn.lts.backend.master.model.exceptions.BusinessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;

/**
 * Created by jugalkishorsahu on Jan, 2018
 */
public class LTSExceptionControllerTest {

  @InjectMocks
  private LTSExceptionController ltsExceptionController;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void throwable_test() {
    Exception e = new Exception();
    BaseRestResponse<Object> result = ltsExceptionController.throwable(e);
    Assert.assertNotNull(result);
    Assert.assertEquals(e.getClass().getName(), result.getErrorCode());
    Assert.assertEquals(e.getMessage(), result.getErrorMessage());
  }

  @Test
  public void illegalArgumentException_test() {
    BusinessException e = new BusinessException(Error.COMPANY_NAME_NULL);
    BaseRestResponse<Object> result = ltsExceptionController.illegalArgumentException(e);
    Assert.assertNotNull(result);
    Assert.assertEquals(Error.COMPANY_NAME_NULL.getCode(), result.getErrorCode());
    Assert.assertEquals(Error.COMPANY_NAME_NULL.getMessage(), result.getErrorMessage());
  }

  @Test
  public void methodArgumentNotValidExceptionTest() {
    MapBindingResult mapBindingResult = new MapBindingResult(Collections.emptyMap(), "");
    mapBindingResult.addError(new ObjectError("objectName", "message"));
    MethodArgumentNotValidException e = new MethodArgumentNotValidException(null, mapBindingResult);
    BaseRestResponse<Object> result = ltsExceptionController.methodArgumentNotValidException(e);
    Assert.assertNotNull(result);
    Assert.assertEquals(e.getClass().getName(), result.getErrorCode());
    Assert.assertEquals("[message, ]", result.getErrorMessage());
  }
}
