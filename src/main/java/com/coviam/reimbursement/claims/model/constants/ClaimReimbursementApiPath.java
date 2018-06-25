/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.model.constants;


public interface ClaimReimbursementApiPath {

    /**
     * Base Api Paths
     */
    String API = "/api";
    String BASE = "/";
    String LTS_UI = "views/";
    String REIMBURSEMENT = API + "/reimbursement";

    String WILD_CARD = "**";
    String GET_CONFIGS = "/getConfigs";

    String CURRENCY_BASE_PATH = "/currency";
    String EXPENSE_BASE_PATH = "/expense";
    String RMB_ITEM_BASE_PATH = "/rmbItem";
    String USER = "/user";
    String FIND_RMB_ITEM_BY_ID = "/findRmbItemById";
    String GET_AUTHENTICATION_KEYS = "/getAuthenticationKeys";

    String SAVE_IMAGE = "/saveImage";
    String MANAGE_CLAIMS = "/manageClaims";

    /**
     * Common Api Paths
     */
    String SAVE = "/save";
    String DELETE = "/delete";
    String UPDATE = "/update";
    String FIND_ALL = "/findAll";
    String FIND_BY_PARAMETER_NAME = "/findByName";
    String CREATE = "/create";
    String FIND_BY_ID = "/findById";

    /**
     * File API Paths
     */
    String FILE_DOWNLOAD = "/download";


}
