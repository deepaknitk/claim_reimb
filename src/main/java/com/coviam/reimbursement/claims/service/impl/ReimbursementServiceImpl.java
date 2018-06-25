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

import java.util.ArrayList;
import java.util.List;

@Service public class ReimbursementServiceImpl implements ReimbursementService {

    @Autowired private ReimbursementRepository reimbursementRepository;

    @Autowired private UserService userService;

    @Autowired private StatusService statusService;

    private ReimbursementItemService reimbursementItemService;

    @Override public List<Reimbursement> findAll(String userId) {
        return this.reimbursementRepository
            .findByUserIdAndMarkForDeleteFalse(userService.findByEmail(userId));
    }

    @Override public Reimbursement saveRmb(Reimbursement rmb) {
        return this.reimbursementRepository.save(rmb);
    }

    @Override public Reimbursement findReimburesementByRmbItemId(Long rmbId) {
        return reimbursementRepository.findOne(rmbId);
    }

    @Override public List<Reimbursement> findAllByUserTypeCode(String userTypeCode) {
        List<Reimbursement> reimbursements = new ArrayList<>();

        if (userTypeCode.equals("FINANCE")) {
            Status status = statusService.findByStatusCode("OPEN");
            reimbursements.addAll(reimbursementRepository.findByStatusId(status));
            status = statusService.findByStatusCode("CLOSED");
            reimbursements.addAll(reimbursementRepository.findByStatusId(status));

        } else if (userTypeCode.equals("ADMIN")) {
            Status status = statusService.findByStatusCode("VERIFIED");
            reimbursements = reimbursementRepository.findByStatusId(status);
        }
        return reimbursements;
    }

}
