package com.coviam.reimbursement.claims.controllers;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.Paging;
import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.request.RmbWebRequest;
import com.coviam.reimbursement.claims.response.ReimbursementResponse;
import com.coviam.reimbursement.claims.service.api.ReimbursementService;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j @RestController @RequestMapping(value = ClaimReimbursementApiPath.REIMBURSEMENT)
public class ReimbursementController {

    @Autowired private RestWebModelConverterService restWebModelConverterService;

    @Autowired private ReimbursementService reimbursementService;

    @RequestMapping(value = {
        ClaimReimbursementApiPath.CREATE}, method = RequestMethod.POST, consumes = {
        MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BaseRestResponse save(@Valid @RequestBody RmbWebRequest rmbWebRequest) {
        ReimbursementResponse rmbResponse = null;
        try {
            Reimbursement rmbRequest =
                this.restWebModelConverterService.convert(rmbWebRequest, Reimbursement.class);
            List<ReimbursementItem> rmbItemRequestList = this.restWebModelConverterService
                .convert(rmbWebRequest.getRmbItemList(), ReimbursementItem.class);
            rmbRequest.setRmbItemList(rmbItemRequestList);
            Reimbursement saveRMB = this.reimbursementService.saveRmb(rmbRequest);
            rmbResponse = this.restWebModelConverterService.convertRMBToRMBResponse(saveRMB);
        } catch (Exception e) {
            log.error("Error in saving rmb  with user id: {}  due to: {} ",
                rmbWebRequest.getUserId(), e.getMessage(), e);
        }
        return new BaseRestResponse<>(true, rmbResponse);


    }

    @RequestMapping(value = {
        ClaimReimbursementApiPath.FIND_ALL}, method = RequestMethod.GET, produces = {
        MediaType.APPLICATION_JSON_VALUE})
    public BaseRestResponse<List<ReimbursementResponse>> findAll(@RequestParam Long userId,
        @RequestParam(defaultValue = "0") int pageNo,
        @RequestParam(defaultValue = "25") int pageSize) throws Exception {

        log.info("Accessed: findAll() with storeId: {}, requestId: {}, clientId: {},"
            + " channelId: {} and userName: {} ", userId);
        List<ReimbursementResponse> ReimbursementResponseList;
        Page<Reimbursement> reimbursementPage;

        try {
            reimbursementPage = this.reimbursementService.findAll(userId, pageNo, pageSize);
            ReimbursementResponseList = this.restWebModelConverterService
                .convert(reimbursementPage.getContent(), ReimbursementResponse.class);
        } catch (Exception e) {
            log.error("Error in getting all claims due to : {} ", e.getMessage(), e);
            return new BaseRestResponse(Error.SYSTEM_ERROR.getCode(),
                Error.SYSTEM_ERROR.getMessage(), false);
        }

        return new BaseRestResponse<>(true, ReimbursementResponseList,
            Paging.builder().page(reimbursementPage.getNumber())
                .totalPage(reimbursementPage.getTotalPages())
                .itemPerPage(reimbursementPage.getSize())
                .totalItem(reimbursementPage.getTotalElements()).build());
    }


}
