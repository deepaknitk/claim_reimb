/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */

package com.gdn.lts.ui.apis;

import com.gdn.lts.ui.model.constants.Constants;
import com.gdn.lts.ui.model.constants.LtsUiApiPath;
import com.gdn.lts.ui.utils.CommonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
