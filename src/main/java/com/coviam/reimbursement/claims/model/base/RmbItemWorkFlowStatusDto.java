package com.coviam.reimbursement.claims.model.base;

import com.coviam.reimbursement.claims.entity.Status;
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

  private Status status;

  private String userEmail;

  private String remarks;
}
