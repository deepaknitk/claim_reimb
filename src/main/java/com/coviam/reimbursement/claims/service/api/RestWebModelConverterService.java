/*
 *
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 *
 */

package com.coviam.reimbursement.claims.service.api;


import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.response.ReimbursementItemResponse;
import com.coviam.reimbursement.claims.response.ReimbursementResponse;

import java.util.List;

/**
 * Created by payal on June, 2018
 */
public interface RestWebModelConverterService {

    <M, T> T convert(M entity, Class<T> clasz) throws Exception;

    <M, T> List<T> convert(List<M> entities, Class<T> clasz) throws Exception;

    public ReimbursementResponse convertRMBToRMBResponse(Reimbursement rmb) throws Exception;

    public List<ReimbursementItemResponse> convertRmbItemListToRMBItemResponseList(
        List<ReimbursementItem> rmbItemList) throws Exception;
}
