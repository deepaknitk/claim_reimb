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
}
