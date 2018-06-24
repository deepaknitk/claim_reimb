package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.entity.Status;
import com.coviam.reimbursement.claims.model.constants.Constants;
import com.coviam.reimbursement.claims.repository.ReimbursementRepository;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import com.coviam.reimbursement.claims.service.api.ReimbursementService;
import com.coviam.reimbursement.claims.service.api.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service public class ReimbursementServiceImpl implements ReimbursementService {

    @Autowired private ReimbursementRepository reimbursementRepository;

    @Autowired private StatusService statusService;

    private ReimbursementItemService reimbursementItemService;

    @Override public Page<Reimbursement> findAll(Long userId, int pageNo, int pageSize) {
        return this.reimbursementRepository
            .findByUserIdAndMarkForDeleteFalse(userId, new PageRequest(pageNo, pageSize));
    }

    @Override public Reimbursement saveRmb(Reimbursement rmb) {
        Status rfqInitialStatus = this.statusService
            .findByStatusCodeAndStatusGroupNameAndMarkForDeleteFalse(Constants.STATUS_CODE_OPEN,
                Constants.RMB_STATUS_GROUP);
        rmb.setStatusId(rfqInitialStatus);

        this.reimbursementRepository.save(rmb);
        rmb.getRmbItemList().forEach(rfqItem -> this.reimbursementItemService
            .save(this.populateRMBItemData(rmb, rfqInitialStatus, rfqItem)));
        return rmb;

    }

    private ReimbursementItem populateRMBItemData(Reimbursement rmb, Status status,
        ReimbursementItem rmbItem) {
        rmbItem.setReimbursement(rmb);
        rmbItem.setItemStatus(status);
        return rmbItem;
    }
}
