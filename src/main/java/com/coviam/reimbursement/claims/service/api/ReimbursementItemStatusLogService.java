package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.ExpenseType;
import com.coviam.reimbursement.claims.entity.ReimbursementItemStatusLog;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemStatusLogDto;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
public interface ReimbursementItemStatusLogService {

  ReimbursementItemStatusLog save(ReimbursementItemStatusLogDto reimbursementItemStatusLogDto);
}
