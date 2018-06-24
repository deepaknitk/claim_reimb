package com.coviam.reimbursement.claims.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Foram Shah on 23/06/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRfqItemStatusWebRequest implements Serializable {

  private static final long serialVersionUID = -2651399533796624539L;

  private Map<Long, RmbItemWorkFlowDto> rmbItemWorkFlowDtoMap;

  private String userEmailId;
}
