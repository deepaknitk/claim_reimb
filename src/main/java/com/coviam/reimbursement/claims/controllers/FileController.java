/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE: All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */
package com.coviam.reimbursement.claims.controllers;

import java.io.IOException;

import com.coviam.reimbursement.claims.model.base.BaseRestResponse;
import com.coviam.reimbursement.claims.model.constants.ClaimReimbursementApiPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coviam.reimbursement.claims.service.api.FileService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Payal
 * @since 24/06/18
 */
@Slf4j
@RestController
public class FileController {

  @Autowired
  FileService fileService;

  /*@RequestMapping(value = ClaimReimbursementApiPath.FILE_UPLOAD, method = RequestMethod.POST,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseRestResponse<String> saveFile(
      @RequestParam(required = false, defaultValue = "rmb-ui-user") String userName,
      @RequestParam String rfqId, @RequestParam String rfqItemId, @RequestParam String rfqTypeId,
      @RequestParam("file") MultipartFile file) {
    try {
      this.fileService.saveFile(rfqTypeId, rfqId, rfqItemId, userName, file);
    } catch (BusinessException be) {
      log.error("Failed to upload file: {} for rfqId: {} and rfqItemId: {}, cause: {}",
          file.getOriginalFilename(), rfqId, rfqItemId, be.getCause(), be);
      return new BaseRestResponse<>(be.getCode(), be.getMessage(), false);
    } catch (Exception e) {
      log.error("Failed to upload file: {} for rfqId: {} and rfqItemId: {}, cause: {}",
          file.getOriginalFilename(), rfqId, rfqItemId, e.getCause(), e);
      return new BaseRestResponse<>(Error.SYSTEM_ERROR.getCode(), Error.SYSTEM_ERROR.getMessage(),
          false);
    }
    return new BaseRestResponse<>(true, file.getOriginalFilename());
  }*/


}
