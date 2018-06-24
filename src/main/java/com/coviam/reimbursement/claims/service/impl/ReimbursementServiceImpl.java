package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.entity.Status;
import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.repository.ReimbursementRepository;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import com.coviam.reimbursement.claims.service.api.ReimbursementService;
import com.coviam.reimbursement.claims.service.api.StatusService;
import com.coviam.reimbursement.claims.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class ReimbursementServiceImpl implements ReimbursementService {

    @Autowired private ReimbursementRepository reimbursementRepository;

    @Autowired private UserService userService;

    private ReimbursementItemService reimbursementItemService;

    @Override public Page<Reimbursement> findAll(String userId, int pageNo, int pageSize) {
        return this.reimbursementRepository
            .findByUserIdAndMarkForDeleteFalse(userService.findByEmail(userId), new PageRequest(pageNo, pageSize));
    }

    @Override public Reimbursement saveRmb(Reimbursement rmb) {
        this.reimbursementRepository.save(rmb);
        return rmb;
    }

    @Override public Reimbursement findReimburesementByRmbItemId(Long rmbId) {
        return reimbursementRepository.findOne(rmbId);
    }
}
