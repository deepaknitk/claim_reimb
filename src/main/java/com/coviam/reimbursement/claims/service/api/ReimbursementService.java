package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.entity.Status;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReimbursementService {

    Page<Reimbursement> findAll(String userId, int pageNo, int pageSize);

    Reimbursement saveRmb(Reimbursement rmb);

    Reimbursement findReimburesementByRmbItemId(Long rmbId);
}
