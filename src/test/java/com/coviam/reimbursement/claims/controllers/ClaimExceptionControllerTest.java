package com.coviam.reimbursement.claims.controllers;

import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.exceptions.BusinessException;
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
public class ClaimExceptionControllerTest {

  @InjectMocks
  private ClaimExceptionController claimExceptionController;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void throwable_test() {
    Exception e = new Exception();
    BaseRestResponse<Object> result = claimExceptionController.throwable(e);
    Assert.assertNotNull(result);
    Assert.assertEquals(e.getClass().getName(), result.getErrorCode());
    Assert.assertEquals(e.getMessage(), result.getErrorMessage());
  }

  @Test
  public void methodArgumentNotValidExceptionTest() {
    MapBindingResult mapBindingResult = new MapBindingResult(Collections.emptyMap(), "");
    mapBindingResult.addError(new ObjectError("objectName", "message"));
    MethodArgumentNotValidException e = new MethodArgumentNotValidException(null, mapBindingResult);
    BaseRestResponse<Object> result = claimExceptionController.methodArgumentNotValidException(e);
    Assert.assertNotNull(result);
    Assert.assertEquals(e.getClass().getName(), result.getErrorCode());
    Assert.assertEquals("[message, ]", result.getErrorMessage());
  }
}
