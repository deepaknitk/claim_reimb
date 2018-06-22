/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.apis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.model.constants.LtsUiApiPath;
import com.coviam.reimbursement.claims.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping(LtsUiApiPath.BASE + LtsUiApiPath.LTS_UI + LtsUiApiPath.WILD_CARD)
    public String index(Model model, HttpServletRequest servletRequest) {
        model.addAttribute(Constants.USERNAME, (CommonUtils.getUserName()));
        model.addAttribute(Constants.CONTEXT_PATH, servletRequest.getContextPath());
        return Constants.INDEX;
    }
}
