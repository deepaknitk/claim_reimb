package com.coviam.reimbursement.claims.entity;

import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.model.constants.FieldNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Foram Shah on 23/06/18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.CURRENCY)
public class Currency extends ClaimBaseEntity {
  private static final long serialVersionUID = 38712338140L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQ_GEN_NAME_CURRENCY)
  @Column(name = FieldNames.CURRENCY_ID)
  private Long currencyId;

  @Column(name = FieldNames.CURRENCY_CODE, nullable = false)
  private String currencyCode;

  @Column(name = FieldNames.CURRENCY_DESC, nullable = false)
  private String currencyDesc;
}
