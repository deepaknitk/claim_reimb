/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE: All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */
package com.coviam.reimbursement.claims.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coviam.reimbursement.claims.service.api.FileService;
import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;
import com.gdn.lts.backend.master.model.constants.LtsApiPath;
import com.gdn.lts.backend.master.model.enums.Error;
import com.gdn.lts.backend.master.model.exceptions.BusinessException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author manika_singh
 * @since 05/06/18
 */
@Slf4j
@RestController
@RequestMapping(value = LtsApiPath.FILE)
public class FileController {

  @Autowired
  FileService fileService;

  @RequestMapping(value = LtsApiPath.FILE_UPLOAD, method = RequestMethod.POST,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public BaseRestResponse<String> saveFile(
      @RequestParam(required = false, defaultValue = "lts-ui-user") String userName,
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
  }


}
