package com.coviam.reimbursement.claims.model.base;

import com.coviam.reimbursement.claims.entity.Currency;
import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.Status;
import com.coviam.reimbursement.claims.entity.UserMaster;
import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.model.constants.FieldNames;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Foram Shah on 23/06/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReimbursementDto implements Serializable {
  private static final long serialVersionUID = -2651399533796624539L;

  private Reimbursement reimbursementId;

  private Status itemStatus;

  private String rmbItemBillNumber;

  private String rfqItemDescription;

  private ExpenseType expenseType;

  private Currency currency;

  private Double rmbItemAmount;

  private String rmbItemFilename;
}
