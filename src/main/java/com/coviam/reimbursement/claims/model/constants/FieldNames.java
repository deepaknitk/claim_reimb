package com.coviam.reimbursement.claims.model.constants;

public interface FieldNames {

    // claim base entity fields
    String MARK_FOR_DELETE = "mark_for_delete";
    String UPDATED_DATE = "updated_date";
    String CREATED_DATE = "created_date";
    String CREATED_BY = "created_by";
    String UPDATED_BY = "updated_by";

    // expense type entity fields
    String EXPENSE_TYPE_ID ="expense_type_id";
    String EXPENSE_TYPE_CODE = "expense_type_code";
    String EXPENSE_TYPE_DESCRIPTION = "expense_type_description";

    //status entity fields
    String STATUS_ID = "status_id";
    String STATUS_CODE = "status_code";
    String STATUS_DESCRIPTION = "status_description";
    String STATUS_GROUP_NAME ="status_group_name";

    // user master fields
    String USER_ID = "user_id";
    String USER_FULL_NAME = "user_full_name";
    String USER_PHONE = "user_phone";
    String USER_EMAIL = "user_email";
    String USER_TYPE = "user_type_id";
    String USER_SLACK_ID = "user_slack_id";
    String EMPLOYEE_ID = "employee_id";

    // user type master fields
    String USER_TYPE_ID = "user_type_id";
    String USER_TYPE_CODE = "user_type_code";
    String USER_TYPE_DESCRIPTION = "user_type_description";

    // reimbursement fields
    String REIMBURSEMENT_ID = "reimbursement_id";
    String REIMBURSEMENT_DATE = "reimbursement_date";

    //rmb_item entity fields
    String RMB_ITEM_ID = "reimbursement_item_id";
    String RMB_ID = "reimbursement_id";
    String RMB_BILL_NO = "reimbursement_item_bill_number";
    String RMB_DESC  = "reimbursement_item_description";
    String RMB_EXPENSE_TYPE_ID = "reimbursement_item_expense_type_id";
    String RMB_ITEM_AMOUNT = "reimbursement_item_amount";
    String RMB_ITEM_REMARKS = "reimbursement_item_remarks";
    String RMB_ITEM_FILENAME = "reimbursement_item_filename";

    //currency entity fields
    String CURRENCY_CODE = "currency_code";
    String CURRENCY_ID = "currency_id";
    String CURRENCY_DESC = "currency_description";

    //rmb item status log entity fields
    String RMB_ITEM_STATUS_LOG_ID = "reimbursement_itemstatus_log_id";
    String RMB_ITEM_OLD_STATUS_ID = "reimbursement_item_old_status_id";
    String RMB_ITEM_NEW_STATUS_ID = "reimbursement_item_new_status_id";

    // rmb item work flow status entity fields
    String RMB_ITEM_WORK_FLOW_STATUS_ID = "reimbursement_item_workflow_status_id";

    // system parameter entity fields
    String PARAMETER_ID = "parameter_id";
    String PARAMETER_NAME = "parameter_name";
    String PARAMETER_VALUE = "parameter_value";
    String PARAMETER_DESCRIPTION = "parameter_description";
}
