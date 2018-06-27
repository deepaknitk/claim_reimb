/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.utils;

import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.model.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;

import com.coviam.reimbursement.claims.model.constants.Constants;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
@Slf4j
public class CommonUtils {

    public static String getMaxAllowedFileSize(String fileSizeInMb) {
        try {
            return fileSizeInMb.substring(0, fileSizeInMb.length() - 2);
        } catch (StringIndexOutOfBoundsException e) {
            log.error("fileSizeInMb is in invalid format, fileSizeInMb :{}", fileSizeInMb, e);
            return Constants.DEFAULT_MAX_ALLOWED_FILE_SIZE_IN_MB;
        }
    }

    public static void checkError(boolean cond, Error error) {
        if (!cond) {
            throw new BusinessException(error);
        }
    }
}
