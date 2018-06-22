/*
 *
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 *
 */

package com.coviam.reimbursement.claims.model.exceptions;


import com.coviam.reimbursement.claims.model.enums.Error;

public class BusinessException extends RuntimeException {
  private final Error error;

  public BusinessException(Error errorCode) {
    this.error = errorCode;
  }

  public String getCode() {
    return this.error.getCode();
  }

  @Override
  public String getMessage() {
    return this.error.getMessage();
  }
}
