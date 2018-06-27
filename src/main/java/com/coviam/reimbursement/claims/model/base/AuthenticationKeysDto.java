package com.coviam.reimbursement.claims.model.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Foram Shah on 24/06/18
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationKeysDto implements Serializable {

  private static final long serialVersionUID = -2651399533796624539L;

  String clientId;
  String clientSecret;
}
