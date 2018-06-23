package com.coviam.reimbursement.claims.entity;

import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.model.constants.FieldNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Constants.REIMBURSEMENT_ITEM_STATUS_LOG)
public class ReimbursementItemStatusLog extends ClaimBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQ_GEN_NAME_REIMBURSEMENT_ITEM_STATUS_LOG)
    @SequenceGenerator(name = Constants.SEQ_GEN_NAME_REIMBURSEMENT_ITEM_STATUS_LOG,
        sequenceName = Constants.DB_SEQ_NAME_REIMBURSEMENT_ITEM_STATUS_LOG, allocationSize = 1)
    @Column(name = FieldNames.RMB_ITEM_STATUS_LOG_ID)
    private Long reimbursementItemStatusLogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = FieldNames.RMB_ITEM_OLD_STATUS_ID, referencedColumnName = FieldNames.STATUS_ID,
        foreignKey = @ForeignKey(name = Constants.RMB_ITEM_STATUS_LOG_NEW_STATUS_FK_02), nullable = false),
        @JoinColumn(name = FieldNames.RMB_ITEM_NEW_STATUS_ID, referencedColumnName = FieldNames.STATUS_ID,
        foreignKey = @ForeignKey(name = Constants.RMB_ITEM_STATUS_LOG_OLD_STATUS_FK_01), nullable = false)
    })
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.RMB_ITEM_ID, referencedColumnName = FieldNames.RMB_ITEM_ID,
    foreignKey = @ForeignKey(name = Constants.RMB_ITEM_STATUS_LOG_RMB_ITEM_ID_FK_03),nullable = false)
    private ReimbursementItem reimbursementItem;

}
