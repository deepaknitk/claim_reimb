/*
 *
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 *
 */

package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.response.ReimbursementItemResponse;
import com.coviam.reimbursement.claims.response.ReimbursementResponse;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by payal on June, 2018
 */
@Slf4j @Service public class RestWebModelConverterServiceImpl
    implements RestWebModelConverterService {

    @Autowired private org.dozer.Mapper mapper;

    @Override public <M, T> T convert(M entity, Class<T> clasz) throws Exception {
        T response = this.mapper.map(entity, clasz);
        return response;
    }

    @Override public <M, T> List<T> convert(List<M> entities, Class<T> clasz) throws Exception {
        List<T> responseList = new ArrayList<T>();
        for (M entity : entities) {
            T response = this.mapper.map(entity, clasz);
            responseList.add(response);
        }
        return responseList;
    }

    @Override public ReimbursementResponse convertRMBToRMBResponse(Reimbursement rmb)
        throws Exception {
        ReimbursementResponse rfqResponse = this.convert(rmb, ReimbursementResponse.class);
        rfqResponse.setStatus_Id(rmb.getStatusId().getStatusCode());
        rfqResponse.setReimbursement_date(rmb.getReimbursement_date());
        rfqResponse
            .setRmbItemList(this.convertRmbItemListToRMBItemResponseList(rmb.getRmbItemList()));

        return rfqResponse;
    }

    @Override public List<ReimbursementItemResponse> convertRmbItemListToRMBItemResponseList(
        List<ReimbursementItem> rmbItemList) throws Exception {
        List<ReimbursementItemResponse> responseList = new ArrayList<>();
        rmbItemList.forEach(rmbItem -> {
            ReimbursementItemResponse response = null;
            try {
                response = this.convert(rmbItem, ReimbursementItemResponse.class);
            } catch (Exception e) {
                log.error("error in giving response ", e.getMessage(), e);
            }
            response.setItemStatus(rmbItem.getItemStatus().getStatusCode());
            responseList.add(response);
        });
        return responseList;
    }

}
