package com.coviam.reimbursement.claims.model.base;

import com.coviam.reimbursement.claims.model.constants.FieldNames;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @author Foram Shah on 23/06/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyResponseDto implements Serializable {

  private static final long serialVersionUID = -2651399533796624539L;

  private String currencyCode;
  private String currencyDesc;
}
