package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import org.springframework.data.domain.Page;

public interface ReimbursementService {

    Page<Reimbursement> findAll(Long userId, int pageNo, int pageSize);

    Reimbursement saveRmb(Reimbursement rmb);

    ReimbursementItem findReimburesementByRmbItemId(Long rmbItemId);
}
