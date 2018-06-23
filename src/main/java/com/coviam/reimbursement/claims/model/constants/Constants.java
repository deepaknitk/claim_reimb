/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.model.constants;

public interface Constants {

    String CONTEXT_PATH = "__contextPath";
    String USERNAME = "__username";
    String INDEX = "index";
    String FILE_SIZE_MB = "fileSizeInMB";
    String DEFAULT_MAX_ALLOWED_FILE_SIZE_IN_MB = "2";
    String SERVER_DATE = "serverDate";
    String IMAGE_REGEX_PATTERN = "imageRegexPattern";

    /**
     * Unique Key Constraints
     */
    String EXPENSE_TYPE_MASTER_UK_01 = "expense_type_master_uk_01";
    String STATUS_MASTER_UK_01 = "status_master_uk_01";

    /**
     * Table name constants
     */
    String EXPENSE_TYPE_MASTER = "expense_type_master";
    String STATUS_MASTER = "status_master";
    String REIMBURSEMNET_ITEM = "reimbursement_item";
    String CURRENCY = "currency_master";

    /**
     * Sequence name constants
     */
    String DB_SEQ_NAME_EXPENSE_TYPE_MASTER = "expense_type_master_seq";
    String DB_SEQ_NAME_STATUS_MASTER = "status_master_seq";
    String DB_SEQ_NAME_CURRENCY_MASTER = "currency_master_seq";
    String DB_SEQ_NAME_REIMBURSEMENT_ITEM = "reimbursement_item_seq";

    /**
     * Sequence generator name constants
     */
    String SEQ_GEN_NAME_EXPENSE_TYPE_MASTER = "expense_type_master_seq";
    String SEQ_GEN_NAME_STATUS_MASTER = "status_master_seq";
    String SEQ_GEN_NAME_REIMBURSEMENT_ITEM = "reimbursement_item_seq";
    String SEQ_GEN_NAME_CURRENCY = "currency_master_seq";

    /**
     * Foreign key Constraints
     */
    String RMB_ITEM_CURRENCY_FK = "reimbursement_item_currencyid_fk_01";
    String RMB_ITEM_REIMBURSEMENT_FK ="reimbursement_item_reimbursement_id_fk_01";
    String RMB_ITEM_STATUS_FK ="reimbursement_item_statusid_fk_01";
    String RMB_ITEM_EXPENSE_FK ="reimbursement_item_expensetype_id_fk_01";
}
