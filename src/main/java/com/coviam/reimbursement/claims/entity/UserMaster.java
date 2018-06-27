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
@Table(name = Constants.USER_MASTER)

public class UserMaster extends ClaimBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQ_GEN_NAME_USER_MASTER)
    @SequenceGenerator(name = Constants.SEQ_GEN_NAME_USER_MASTER,
        sequenceName = Constants.DB_SEQ_NAME_USER_MASTER, allocationSize = 1)
    @Column(name = FieldNames.USER_ID)
    private Long userId;

    @Column(name = FieldNames.USER_FULL_NAME, nullable = false)
    private String userName;

//    @Column(name = FieldNames.USER_PHONE, nullable = false)
//    private String userPhone;

    @Column(name = FieldNames.USER_EMAIL, nullable = false)
    private String userEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.USER_TYPE,
        referencedColumnName = FieldNames.USER_TYPE_ID,
        foreignKey = @ForeignKey(name = Constants.USER_MASTER_FK_01), nullable = false)
    private UserTypeMaster userType;

//    @Column(name = FieldNames.USER_SLACK_ID, nullable = false)
//    private String userSlackId;

    @Column(name = FieldNames.EMPLOYEE_ID, nullable = false)
    private String employeeId;


}
