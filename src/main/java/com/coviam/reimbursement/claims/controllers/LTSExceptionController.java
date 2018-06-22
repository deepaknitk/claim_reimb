/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.controllers;

import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;
import com.gdn.lts.backend.master.model.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class LTSExceptionController {

  @ExceptionHandler(Throwable.class)
  BaseRestResponse<Object> throwable(Throwable e) {
    log.error(e.getClass().getName(), e);
    return BaseRestResponse.builder().errorCode(e.getClass().getName()).errorMessage(e.getMessage())
        .success(false).build();
  }

  @ExceptionHandler(BusinessException.class)
  BaseRestResponse<Object> illegalArgumentException(BusinessException be) {
    log.error(be.getClass().getName(), be);
    return BaseRestResponse.builder().errorCode(be.getCode()).errorMessage(be.getMessage())
        .success(false).build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  BaseRestResponse<Object> methodArgumentNotValidException(MethodArgumentNotValidException manve) {
    log.error(manve.getClass().getName(), manve.getBindingResult());
    StringBuilder msg = new StringBuilder("[");

    manve.getBindingResult().getAllErrors().forEach(x -> {
      msg.append(x.getDefaultMessage()).append(", ");
    });

    return BaseRestResponse.builder().errorCode(manve.getClass().getName())
        .errorMessage(msg.append("]").toString()).success(false).build();
  }
}
