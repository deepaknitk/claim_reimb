/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.coviam.reimbursement.claims.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import com.coviam.reimbursement.claims.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
@Controller
public class AuthController {

    @RequestMapping(ClaimReimbursementApiPath.LOGIN)
    public String login() {
        return "redirect:/secured";
    }

    @RequestMapping(ClaimReimbursementApiPath.LOGOUT)
    public String logout() {
        return "redirect:/logout/cas";
    }

    @RequestMapping(ClaimReimbursementApiPath.SECURED)
    public String index(Model model, HttpServletRequest servletRequest) {
        model.addAttribute("__username", CommonUtils.getUserName());
        model.addAttribute("__contextPath", servletRequest.getContextPath());
        return "secure";
    }

}
