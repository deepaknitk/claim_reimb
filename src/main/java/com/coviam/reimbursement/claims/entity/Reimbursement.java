package com.coviam.reimbursement.claims.entity;

import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.model.constants.FieldNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.REIMBURSEMENT)

public class Reimbursement extends ClaimBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQ_GEN_NAME_REIMBURSEMENT)
    @SequenceGenerator(name = Constants.SEQ_GEN_NAME_REIMBURSEMENT,
        sequenceName = Constants.DB_NAME_REIMBURSEMENT, allocationSize = 1)

    @Column(name = FieldNames.REIMBURSEMENT_ID)
    private Long reimbursementId;

    @Column(name = FieldNames.REIMBURSEMENT_DATE, nullable = false)
    private Date reimbursement_date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.USER_ID,
        referencedColumnName = FieldNames.USER_ID,
        foreignKey = @ForeignKey(name = Constants.USER_ID_FK_01), nullable = false)
    private UserMaster userMaster;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = FieldNames.STATUS_ID, referencedColumnName = FieldNames.STATUS_ID,
        foreignKey = @ForeignKey(name = Constants.REIMBURSEMENT_STATUS_FK_02), nullable = false)
    private Status status;

}
