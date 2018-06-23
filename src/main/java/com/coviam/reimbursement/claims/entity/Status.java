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
@Table(name = Constants.STATUS_MASTER)
//todo indexes
public class Status extends ClaimBaseEntity {

    private static final long serialVersionUID = 40L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQ_GEN_NAME_STATUS_MASTER)
    @SequenceGenerator(name = Constants.SEQ_GEN_NAME_STATUS_MASTER,
        sequenceName = Constants.DB_SEQ_NAME_STATUS_MASTER, allocationSize = 1)
    @Column(name = FieldNames.STATUS_ID)
    private Long statusId;

    @Column(name = FieldNames.STATUS_CODE, nullable = false)
    private String statusCode;

    @Column(name = FieldNames.STATUS_DESCRIPTION, nullable = false)
    private String statusDescription;

    @Column(name = FieldNames.STATUS_GROUP_NAME, nullable = false)
    private String statusGroupName;


}
