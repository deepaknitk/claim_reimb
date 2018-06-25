package com.coviam.reimbursement.claims.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Foram Shah on 24/06/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManageReimbursementRequest implements Serializable {
  private static final long serialVersionUID = -2651399533796624539L;

  private Long reimbursementId;

  private List<ReimbursementItemStatusDto> reimbursementItemStatusDtos;

  private String userEmail;
}
