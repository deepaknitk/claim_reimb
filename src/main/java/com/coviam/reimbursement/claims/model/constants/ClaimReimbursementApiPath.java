/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.model.constants;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public interface ClaimReimbursementApiPath {

    /**
     * Base Api Paths
     */
    String API = "/api";
    String BASE = "/";
    String LTS_UI = "views/";
    String REIMBURSEMENT = API + "/reimbursement";

    String WILD_CARD = "**";
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String SECURED = "/secured";

    String GET_CONFIGS = "/getConfigs";
    String SAVE_IMAGE = "/saveImage";

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

}
