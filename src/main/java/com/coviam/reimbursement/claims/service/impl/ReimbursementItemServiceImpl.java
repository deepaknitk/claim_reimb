package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.repository.ReimbursementItemRepository;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Foram Shah on 23/06/18
 */
@Service public class ReimbursementItemServiceImpl implements ReimbursementItemService {

    @Autowired private ReimbursementItemRepository reimbursementItemRepository;

    @Override public ReimbursementItem save(ReimbursementItem reimbursementItem) {
        return this.reimbursementItemRepository.save(reimbursementItem);
    }
}
