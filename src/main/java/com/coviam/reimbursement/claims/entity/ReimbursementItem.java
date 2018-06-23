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
    @Column(name = FieldNames.RMB_ITEM_ID)
    private Long reimbursementItemId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.RFQ_ID, referencedColumnName = FieldNames.RFQ_ID,
        foreignKey = @ForeignKey(name = Constants.RFQ_ITEM_RFQ_FK), nullable = false)
    private RFQ rfq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.RFQ_STATUS_ID, referencedColumnName = FieldNames.STATUS_ID,
        foreignKey = @ForeignKey(name = Constants.RFQ_ITEM_STATUS_FK), nullable = false)
    private Status itemStatus;

    @Column(name = FieldNames.RFQ_ITEM_NAME, nullable = false)
    private String rfqItemName;

    @Column(name = FieldNames.RFQ_ITEM_DESCRIPTION, nullable = false)
    private String rfqItemDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.CURRENCY_ID, referencedColumnName = FieldNames.CURRENCY_ID,
        foreignKey = @ForeignKey(name = Constants.RFQ_ITEM_RFQ_FK), nullable = false)
    private Currency currency;

    @Column(name = FieldNames.RMB_ITEM_REMARKS, nullable = false)
    private String rmbItemRemarks;

    @Column(name = FieldNames.RMB_ITEM_FILENAME)
    private String rmbItemFilename;
}
    String RMB_ITEM_ID = "reimbursement_item_id";
    String RMB_ID = "reimbursement_id";
    String RMB_STATUS_ID = "reimbursement_status_id";
    String RMB_BILL_NO = "reimbursement_item_bill_number";
    String RMB_DESC  = "reimbursement_item_description";
    String RMB_EXPENSE_TYPE_ID = "reimbursement_item_expense_type_id";
    String RMB_ITEM_AMOUNT = "reimbursement_item_amount";
    String RMB_CURRENCY_ID = "currency_id";
