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
@Table(name = Constants.REIMBURSEMENT_ITEM_WORK_FLOW_STATUS)
public class ReimbursementItemWorkFlowStatus extends ClaimBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = Constants.SEQ_GEN_NAME_REIMBURSEMENT_ITEM_WORK_FLOW_STATUS)
    @SequenceGenerator(name = Constants.SEQ_GEN_NAME_REIMBURSEMENT_ITEM_WORK_FLOW_STATUS,
        sequenceName = Constants.DB_SEQ_NAME_REIMBURSEMENT_ITEM_WORK_FLOW_STATUS, allocationSize = 1)
    @Column(name = FieldNames.RMB_ITEM_WORK_FLOW_STATUS_ID)
    private Long reimbursementItemWorkFlowStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.RMB_ITEM_ID, referencedColumnName = FieldNames.RMB_ITEM_ID,
        foreignKey = @ForeignKey(name = Constants.RMB_ITEM_WORK_FLOW_STATUS_RMB_ITEM_ID_FK_01),nullable = false)
    private ReimbursementItem reimbursementItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.STATUS_ID, referencedColumnName = FieldNames.STATUS_ID,
        foreignKey = @ForeignKey(name = Constants.RMB_ITEM_WORK_FLOW_STATUS_STATUS_ID_FK_01), nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.USER_ID, referencedColumnName = FieldNames.USER_ID,
        foreignKey = @ForeignKey(name = Constants.RMB_ITEM_WORK_FLOW_STATUS_USER_ID_FK_01), nullable = false)
    private UserMaster userMaster;

    @Column(name = FieldNames.RMB_ITEM_WORK_FLOW_REMARKS)
    private String remarks;

}
