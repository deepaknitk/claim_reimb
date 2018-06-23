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
@Table(name = Constants.SYSTEM_PARAMETER)
public class SystemParameter extends ClaimBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQ_GEN_NAME_SYSTEM_PARAMETER)
    @SequenceGenerator(name = Constants.SEQ_GEN_NAME_SYSTEM_PARAMETER,
        sequenceName = Constants.DB_SEQ_NAME_SYSTEM_PARAMETER, allocationSize = 1)
    @Column(name = FieldNames.PARAMETER_ID)
    private Long parameterId;

    @Column(name = FieldNames.PARAMETER_NAME, nullable = false)
    private String parameterName;

    @Column(name = FieldNames.PARAMETER_VALUE)
    private String parameterValue;

    @Column(name = FieldNames.PARAMETER_DESCRIPTION)
    private String parameterDescription;


}
