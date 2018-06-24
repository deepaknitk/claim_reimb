package com.coviam.reimbursement.claims.model.base;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.entity.Reimbursement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Foram Shah on 23/06/18
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor public class ReimbursementDto
    implements Serializable {
    private static final long serialVersionUID = -2651399533796624539L;

    private String rmbItemBillNumber;

    private String rfqItemDescription;

    private String expenseType;

    private String currency;

    private Double rmbItemAmount;

    private String rmbItemFilename;

    private String remarks;

    private Date rmbItemDate;
}
