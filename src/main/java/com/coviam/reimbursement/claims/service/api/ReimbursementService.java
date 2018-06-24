package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReimbursementService {

    Page<Reimbursement> findAll(String userId, int pageNo, int pageSize);

    Reimbursement saveRmb(Reimbursement rmb);

    ReimbursementItem findReimburesementByRmbItemId(Long rmbItemId);
}
