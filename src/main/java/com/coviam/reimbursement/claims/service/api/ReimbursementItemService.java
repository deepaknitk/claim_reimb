package com.coviam.reimbursement.claims.service.api;

import com.coviam.reimbursement.claims.entity.Reimbursement;
import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
public interface ReimbursementItemService {

  List<ReimbursementItem> saveOrUpdate(List<ReimbursementItem> reimbursementItemList, List<MultipartFile> fileList);

  ReimbursementItem findByReimbursementItemByReimburesementItemId(Long rmbItemId);


}
