package com.coviam.reimbursement.claims.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.coviam.reimbursement.claims.model.constants.FieldNames;
import com.coviam.reimbursement.claims.model.constants.Constants;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.USER_TYPE_MASTER)

public class UserTypeMaster extends ClaimBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQ_GEN_NAME_USER_TYPE_MASTER)
    @SequenceGenerator(name = Constants.SEQ_GEN_NAME_USER_TYPE_MASTER, sequenceName = Constants.DB_SEQ_NAME_USER_TYPE_MASTER, allocationSize = 1)
    @Column(name = FieldNames.USER_TYPE_ID, nullable = false)
    private Long userTypeId;

    @Column(name = FieldNames.USER_TYPE_CODE, nullable = false)
    private String userTypeCode;

    @Column(name = FieldNames.USER_TYPE_DESCRIPTION, nullable = false)
    private String userTypeDescription;


}
