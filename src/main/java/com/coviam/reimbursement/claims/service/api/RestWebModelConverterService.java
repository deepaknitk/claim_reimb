package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.CurrencyResponseDto;
import com.coviam.reimbursement.claims.model.base.ExpenseTypeDto;
import com.coviam.reimbursement.claims.model.base.ReimbursementDto;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemDto;
import com.coviam.reimbursement.claims.request.RmbWebRequest;
import com.coviam.reimbursement.claims.response.ReimbursementItemResponse;
import com.coviam.reimbursement.claims.response.ReimbursementResponse;
import org.springframework.web.multipart.MultipartFile;

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

    BaseRestResponse<List<CurrencyResponseDto>> convertCurrencyResponse(List<Currency> currencies);

    BaseRestResponse<List<ExpenseTypeDto>> convertExpenseResponse(List<ExpenseType> expenseTypes);

    BaseRestResponse<ReimbursementItemDto> convertRmbItemToRmbItemDto(ReimbursementItem rmbItem);

    Reimbursement convertRmbWebRequestToRmb(RmbWebRequest rmbWebRequest);

    List<ReimbursementItem> convertRmbItemList(List<ReimbursementDto> reimbursementDtos, Reimbursement reimbursement);

    List<MultipartFile> convertRmbItemFileList(List<ReimbursementDto> reimbursementDtos, Reimbursement reimbursement);
}
