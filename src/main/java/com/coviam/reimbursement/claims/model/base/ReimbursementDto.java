package com.coviam.reimbursement.claims.model.base;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.entity.Reimbursement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Foram Shah on 23/06/18
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor public class ReimbursementDto
    implements Serializable {
    private static final long serialVersionUID = -2651399533796624539L;

    private Reimbursement reimbursementId;

    private String rmbItemBillNumber;

    private String rfqItemDescription;

    private ExpenseType expenseType;

    private Currency currency;

    private Double rmbItemAmount;

    private String rmbItemFilename;
}
