package com.coviam.reimbursement.claims.model.enums;

/**
 * @author Foram Shah on 22/06/18
 */
public enum Error {
  DEALER_NULL("dealer should not be null"),

    STATUS_CODE_NULL("status code should not be null"),
    STATUS_NULL("status should not be not null"),

    EXPENSE_TYPE_CODE_NULL("expense type code should not be null"),
    EXPENSE_TYPE_NULL("expense type should not be null"),
    EXPENSE_TYPE_NOT_FOUND("expense type not found"),
    EXPENSE_TYPE_CODE_INVALID("expense type code already exists"),
    EXPENSE_TYPE_NOT_SAVED("expense type is not saved");

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
