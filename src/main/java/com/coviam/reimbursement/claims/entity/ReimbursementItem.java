package com.coviam.reimbursement.claims.entity;

import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.model.constants.FieldNames;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Foram Shah on 23/06/18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.REIMBURSEMNET_ITEM)
public class ReimbursementItem extends ClaimBaseEntity {

    private static final long serialVersionUID = 38712338140L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQ_GEN_NAME_REIMBURSEMENT_ITEM)
    @SequenceGenerator(name = Constants.SEQ_GEN_NAME_REIMBURSEMENT_ITEM,
        sequenceName = Constants.DB_SEQ_NAME_REIMBURSEMENT_ITEM, allocationSize = 1)
    @Column(name = FieldNames.RMB_ITEM_ID)
    private Long reimbursementItemId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.RMB_ID, referencedColumnName = FieldNames.REIMBURSEMENT_ID,
        foreignKey = @ForeignKey(name = Constants.RMB_ITEM_REIMBURSEMENT_FK), nullable = false)
    private Reimbursement reimbursementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.STATUS_ID, referencedColumnName = FieldNames.STATUS_ID,
        foreignKey = @ForeignKey(name = Constants.RMB_ITEM_STATUS_FK), nullable = false)
    private Status itemStatus;

    @Column(name = FieldNames.RMB_BILL_NO, nullable = false)
    private String rmbItemBillNumber;

    @Column(name = FieldNames.RMB_DESC, nullable = false)
    private String rfqItemDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.RMB_EXPENSE_TYPE_ID, referencedColumnName = FieldNames.EXPENSE_TYPE_ID,
        foreignKey = @ForeignKey(name = Constants.RMB_ITEM_EXPENSE_FK), nullable = false)
    private ExpenseType expenseType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.CURRENCY_ID, referencedColumnName = FieldNames.CURRENCY_ID,
        foreignKey = @ForeignKey(name = Constants.RMB_ITEM_CURRENCY_FK), nullable = false)
    private Currency currency;

    @Column(name = FieldNames.RMB_ITEM_AMOUNT, nullable = false)
    private Double rmbItemAmount;

    @Column(name = FieldNames.RMB_ITEM_REMARKS, nullable = false)
    private String rmbItemRemarks;

    @Column(name = FieldNames.RMB_ITEM_FILENAME)
    private String rmbItemFilename;
}
