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
@Table(name = Constants.EXPENSE_TYPE_MASTER)
//todo indexes
public class ExpenseType extends ClaimBaseEntity {

    private static final long serialVersionUID = 38L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQ_GEN_NAME_EXPENSE_TYPE_MASTER)
    @SequenceGenerator(name = Constants.SEQ_GEN_NAME_EXPENSE_TYPE_MASTER,
        sequenceName = Constants.DB_SEQ_NAME_EXPENSE_TYPE_MASTER, allocationSize = 1)
    @Column(name = FieldNames.EXPENSE_TYPE_ID)
    private Long expenseTypeId;

    @Column(name = FieldNames.EXPENSE_TYPE_CODE, nullable = false)
    private String expenseTypeCode;

    @Column(name = FieldNames.EXPENSE_TYPE_DESCRIPTION, nullable = false)
    private String expenseTypeDescription;

}
