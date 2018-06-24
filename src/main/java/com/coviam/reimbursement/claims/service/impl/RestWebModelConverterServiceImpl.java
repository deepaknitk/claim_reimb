package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.CurrencyResponseDto;
import com.coviam.reimbursement.claims.model.base.ExpenseTypeDto;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemDto;
import com.coviam.reimbursement.claims.response.ReimbursementItemResponse;
import com.coviam.reimbursement.claims.response.ReimbursementResponse;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        reimbursementItemDto.setCurrency(rmbItem.getCurrency());
        reimbursementItemDto.setExpenseType(rmbItem.getExpenseType());
        reimbursementItemDto.setItemStatus(rmbItem.getItemStatus());
        reimbursementItemDto.setRfqItemDescription(rmbItem.getRfqItemDescription());
        reimbursementItemDto.setRmbItemFilename(rmbItem.getRmbItemFilename());
        reimbursementItemDto.setRmbItemAmount(rmbItem.getRmbItemAmount());
        reimbursementItemDto.setRmbItemBillNumber(rmbItem.getRmbItemBillNumber());
        reimbursementItemDto.setRemarks(rmbItem.getRmbItemRemarks());
        return new BaseRestResponse<>(true, reimbursementItemDto);
    }

}
