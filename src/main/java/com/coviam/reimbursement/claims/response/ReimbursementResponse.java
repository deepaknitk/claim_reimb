package com.coviam.reimbursement.claims.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data public class ReimbursementResponse implements Serializable {

    private Long reimbursementId;

    private String statusCode;

    private Date reimbursement_date;
}
