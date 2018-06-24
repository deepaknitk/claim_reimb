package com.coviam.reimbursement.claims.response;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.entity.ExpenseType;
import lombok.Data;

@Data public class ReimbursementItemResponse {

    private String itemStatus;

    private String rmbItemBillNumber;

    private String rfqItemDescription;

    private String expenseTypeCode;

    private String currencyCode;

    private Double rmbItemAmount;

    private String rmbItemFilename;

    private String remarks;

    private String rmbItemDate;
}
