package com.coviam.reimbursement.claims.model.enums;

/**
 * @author Foram Shah on 22/06/18
 */
public enum Error {
  DEALER_NULL("dealer should not be null");

  private String message;

  Error(String message) {
    this.message = message;
  }

  public String getCode() {
    return this.name();
  }

  public String getMessage() {
    return this.message;
  }
}
