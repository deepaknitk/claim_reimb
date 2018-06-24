package com.coviam.reimbursement.claims.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RmbItemWorkFlowStatusDto {

    private Long reimbursementItemId;

    private Long statusId;

    private Long userId;

    private String remarks;

}
