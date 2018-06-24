package com.coviam.reimbursement.claims.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Foram Shah on 24/06/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RmbItemWorkFlowStatusDto implements Serializable {

  private static final long serialVersionUID = -2651399533796624539L;

  private Long reimbursementItemId;

  private Long statusId;

  private Long userId;

  private String remarks;
}
