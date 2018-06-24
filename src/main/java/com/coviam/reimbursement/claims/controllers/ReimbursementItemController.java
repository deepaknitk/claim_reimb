package com.coviam.reimbursement.claims.controllers;

import com.coviam.reimbursement.claims.entity.ReimbursementItem;
import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.base.ReimbursementDto;
import com.coviam.reimbursement.claims.model.base.ReimbursementItemDto;
import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import com.coviam.reimbursement.claims.model.enums.Error;
import com.coviam.reimbursement.claims.service.api.ReimbursementItemService;
import com.coviam.reimbursement.claims.service.api.RestWebModelConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Foram Shah on 23/06/18
 */
@Slf4j
@RestController
@RequestMapping(value = ClaimReimbursementApiPath.RMB_ITEM_BASE_PATH)
public class ReimbursementItemController {

  @Autowired
  private ReimbursementItemService reimbursementItemService;

  @RequestMapping(value = {ClaimReimbursementApiPath.FIND_RMB_ITEM_BY_ID},
      method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
  @ResponseBody
  public BaseRestResponse<List<ReimbursementItemDto>> findRmbItemById(@RequestParam Long rmbItemId)
      throws Exception {
    try {
      List<ReimbursementItemDto> reimbursementItemDtos =
          reimbursementItemService.findByReimbursementItemListByReimburesementItemId(rmbItemId);
      return new BaseRestResponse<>(true, reimbursementItemDtos);
    } catch (Exception e) {
      return new BaseRestResponse<>(Error.SYSTEM_ERROR.getMessage(), Error.SYSTEM_ERROR.getCode(),
          false, null);
    }
  }

}
