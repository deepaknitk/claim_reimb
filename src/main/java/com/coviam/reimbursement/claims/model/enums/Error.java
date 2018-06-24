package com.coviam.reimbursement.claims.model.enums;

/**
 * @author Foram Shah on 22/06/18
 */
public enum Error {
  DEALER_NULL("dealer should not be null"),
    SYSTEM_ERROR("Internal system error"),
    STATUS_CODE_NULL("status code should not be null"),
    STATUS_NULL("status should not be not null"),

    EXPENSE_TYPE_CODE_NULL("expense type code should not be null"),
    EXPENSE_TYPE_NULL("expense type should not be null"),
    EXPENSE_TYPE_NOT_FOUND("expense type not found"),
    EXPENSE_TYPE_CODE_INVALID("expense type code already exists"),
    EXPENSE_TYPE_NOT_SAVED("expense type is not saved"),

    PARAMETER_NAME_NULL("parameter name should not be null"),
    PARAMETER_NAME_INVALID("parameter name does not exists"),
    PARAMETER_NAME_NOT_FOUND("parameter name is not found"),

    RMB_ITEM_WORK_FLOW_STATUS_NULL("rmb item work flow status should not be null"),
    RMB_ITEM_WORK_FLOW_STATUS_NOT_SAVED("rmb item work flow status is not saved");

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
