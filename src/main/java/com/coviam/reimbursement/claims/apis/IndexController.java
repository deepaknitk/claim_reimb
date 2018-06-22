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
import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import com.coviam.reimbursement.claims.utils.CommonUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping(
        ClaimReimbursementApiPath.BASE + ClaimReimbursementApiPath.LTS_UI + ClaimReimbursementApiPath.WILD_CARD)
    public String index(Model model, HttpServletRequest servletRequest) {
        model.addAttribute(Constants.CONTEXT_PATH, servletRequest.getContextPath());
        return Constants.INDEX;
    }


    @RequestMapping(
        ClaimReimbursementApiPath.BASE)
    public String index1(Model model, HttpServletRequest servletRequest) {
        model.addAttribute(Constants.CONTEXT_PATH, servletRequest.getContextPath());
        return Constants.INDEX;
    }
}
