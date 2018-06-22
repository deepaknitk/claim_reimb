/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.apis;

import com.coviam.reimbursement.claims.config.properties.FileServerProperties;
import com.coviam.reimbursement.claims.config.properties.LtsBackendClientParamProperties;
import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.model.constants.LtsUiApiPath;
import com.coviam.reimbursement.claims.utils.CommonUtils;
import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
@Controller
public class ConfigController {

    @Autowired
    private FileServerProperties fileServerProperties;

    @Value("${spring.http.multipart.max-file-size}")
    private String fileSizeInMB;

    @RequestMapping(value = {LtsUiApiPath.GET_CONFIGS}, method = RequestMethod.GET)
    @ResponseBody
    public BaseRestResponse<Map<String, Object>> getConfig() {

        Map<String, Object> map = new HashMap<>();
        map.put(Constants.FILE_SIZE_MB, CommonUtils.getMaxAllowedFileSize(this.fileSizeInMB));
        map.put(Constants.SERVER_DATE, new Date());
        map.put(Constants.IMAGE_REGEX_PATTERN, this.fileServerProperties.getImageRegexPattern());

        return new BaseRestResponse<>(true, map);
    }
}
