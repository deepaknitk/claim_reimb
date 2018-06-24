package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.CurrencyResponseDto;
import com.coviam.reimbursement.claims.model.base.ExpenseTypeDto;
import com.coviam.reimbursement.claims.model.base.ReimbursementDto;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemDto;
import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.request.RmbWebRequest;
import com.coviam.reimbursement.claims.response.ReimbursementItemResponse;
import com.coviam.reimbursement.claims.response.ReimbursementResponse;
import com.coviam.reimbursement.claims.service.api.CurrencyService;
import com.coviam.reimbursement.claims.service.api.ExpenseTypeService;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import com.coviam.reimbursement.claims.service.api.StatusService;
import com.coviam.reimbursement.claims.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by payal on June, 2018
 */
@Slf4j @Service public class RestWebModelConverterServiceImpl
    implements RestWebModelConverterService {

    @Autowired private org.dozer.Mapper mapper;

    @Autowired
    private StatusService statusService;

    @Autowired
    private UserService userService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExpenseTypeService expenseTypeService;

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
        rfqResponse.setStatusCode(rmb.getStatusId().getStatusCode());
        rfqResponse.setReimbursement_date(rmb.getReimbursement_date());
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

    @Override
    public BaseRestResponse<List<CurrencyResponseDto>> convertCurrencyResponse(
        List<Currency> currencies) {

        List<CurrencyResponseDto> currencyResponseDtos = currencies.stream().filter(Objects::nonNull)
            .map(response -> convertCurrency(response)).collect(Collectors.toList());
        return new BaseRestResponse<>(true, currencyResponseDtos);
    }

    public CurrencyResponseDto convertCurrency(Currency currency) {
        return new CurrencyResponseDto(currency.getCurrencyCode(), currency.getCurrencyDesc());
    }

    @Override
    public BaseRestResponse<List<ExpenseTypeDto>> convertExpenseResponse(
        List<ExpenseType> expenseTypes) {

        List<ExpenseTypeDto> expenseTypeDtos = expenseTypes.stream().filter(Objects::nonNull)
            .map(response -> convertExpenseType(response)).collect(Collectors.toList());
        return new BaseRestResponse<>(true, expenseTypeDtos);
    }

    public ExpenseTypeDto convertExpenseType(ExpenseType expenseType) {
        return new ExpenseTypeDto(expenseType.getExpenseTypeCode(),
            expenseType.getExpenseTypeDescription());
    }

    @Override
    public BaseRestResponse<ReimbursementItemDto> convertRmbItemToRmbItemDto(
        ReimbursementItem rmbItem) {
        ReimbursementItemDto reimbursementItemDto = new ReimbursementItemDto();
        reimbursementItemDto.setCurrencyCode(rmbItem.getCurrency().getCurrencyCode());
        reimbursementItemDto.setExpenseTypeDescription(rmbItem.getExpenseType().getExpenseTypeDescription());
        reimbursementItemDto.setItemStatusCode(rmbItem.getItemStatus().getStatusCode());
        reimbursementItemDto.setRfqItemDescription(rmbItem.getRfqItemDescription());
        reimbursementItemDto.setRmbItemFilename(rmbItem.getRmbItemFilename());
        reimbursementItemDto.setRmbItemAmount(rmbItem.getRmbItemAmount());
        reimbursementItemDto.setRmbItemBillNumber(rmbItem.getRmbItemBillNumber());
        reimbursementItemDto.setRemarks(rmbItem.getRmbItemRemarks());
        return new BaseRestResponse<>(true, reimbursementItemDto);
    }

    @Override public Reimbursement convertRmbWebRequestToRmb(RmbWebRequest rmbWebRequest) {
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setUserId(userService.findByEmail(rmbWebRequest.getUserId()));
        reimbursement.setStatusId(statusService.findByStatusCode(Constants.STATUS_CODE_OPEN));
        reimbursement.setCreatedAt(new Date());
        reimbursement.setCreatedBy("System");
        reimbursement.setMarkForDelete(false);
        reimbursement.setReimbursement_date(new Date());
        return reimbursement;
    }

    @Override public List<ReimbursementItem> convertRmbItemList(List<ReimbursementDto> reimbursementDtos, Reimbursement reimbursement){
        List<ReimbursementItem> reimbursementItems = new ArrayList<>();
        List<MultipartFile> fileList = new ArrayList<>();
        for (ReimbursementDto reimbursementDto: reimbursementDtos){
            reimbursementItems.add(convertRmbItem(reimbursementDto, reimbursement));
            fileList.add(reimbursementDto.getFile());
        }

        return reimbursementItems;
    }
    @Override public List<MultipartFile> convertRmbItemFileList(List<ReimbursementDto> reimbursementDtos, Reimbursement reimbursement){

        List<MultipartFile> fileList = new ArrayList<>();
        for (ReimbursementDto reimbursementDto: reimbursementDtos){
            fileList.add(reimbursementDto.getFile());
        }

        return fileList;
    }

    public ReimbursementItem convertRmbItem(ReimbursementDto reimbursementDto, Reimbursement reimbursement){
        ReimbursementItem reimbursementItem = new ReimbursementItem();
        reimbursementItem.setCurrency(currencyService.findByCurrencyCode(reimbursementDto.getCurrency()));
        reimbursementItem.setExpenseType(expenseTypeService.findByExpenseTypeCode(reimbursementDto.getExpenseType()));
        reimbursementItem.setItemStatus(statusService.findByStatusCode(Constants.STATUS_CODE_OPEN));
        reimbursementItem.setRmbItemBillNumber(reimbursementDto.getRmbItemBillNumber());
        reimbursementItem.setRmbItemRemarks(reimbursementDto.getRemarks());
        reimbursementItem.setRfqItemDescription(reimbursementDto.getRfqItemDescription());
        reimbursementItem.setRmbItemFilename(reimbursementDto.getRmbItemFilename());
        reimbursementItem.setRmbItemAmount(reimbursementDto.getRmbItemAmount());
        reimbursementItem.setRmbItemDate(reimbursementDto.getRmbItemDate());
        reimbursementItem.setReimbursement(reimbursement);
        reimbursementItem.setCreatedAt(new Date());
        reimbursementItem.setCreatedBy("System");
        reimbursementItem.setMarkForDelete(false);
        return reimbursementItem;
    }
}
