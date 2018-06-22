/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE: All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */
package com.gdn.lts.ui.service.impl;

import com.gdn.lts.backend.api.web.model.base.BaseRestResponse;
import com.gdn.lts.backend.api.web.model.request.RFQItemWebRequest;
import com.gdn.lts.backend.api.web.model.response.RFQItemResponse;
import com.gdn.lts.backend.api.web.model.response.RFQResponse;
import com.gdn.lts.backend.api.web.model.response.RFQTypeResponse;
import com.gdn.lts.backend.api.web.model.response.SystemParameterWebResponse;
import com.gdn.lts.backend.master.model.constants.Constants;
import com.gdn.lts.backend.master.model.constants.SystemParameterNames;
import com.gdn.lts.backend.master.model.enums.Error;
import com.gdn.lts.backend.master.model.exceptions.BusinessException;
import com.gdn.lts.ui.outbound.feign.LtsBackendFeign;
import com.gdn.lts.ui.service.api.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author manika_singh
 * @since 12/06/18
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

  @Autowired
  private LtsBackendFeign ltsBackendFeign;

  private static final String TYPES_SEPARATOR = "\\|";

  public void saveFile(String rfqTypeId, String rfqId, String rfqItemId, String username,
      MultipartFile file) throws Exception {
    final String originalFileName = file.getOriginalFilename();
    RFQTypeResponse rfqTypeResponse = this.getRFQTypeResponse(username, rfqTypeId);
    this.isValidImage(rfqTypeResponse.getRfqTypeAllowedFileTypes(), originalFileName);
    this.saveFileToFolder(username, originalFileName, rfqId, file);
    this.updateRFQItem(username, rfqItemId, originalFileName);
    if (Constants.RFQ_TYPE_BULK.equals(rfqTypeResponse.getRfqTypeCode())) {
      this.activateRFQ(username, rfqId);
    }
  }

  private RFQTypeResponse getRFQTypeResponse(String userName, String rfqTypeId) {
    BaseRestResponse<RFQTypeResponse> rfqTypeResponse =
        ltsBackendFeign.findByRFQTypeId(userName, rfqTypeId).toBlocking().value();
    if (!rfqTypeResponse.isSuccess() || rfqTypeResponse.getData() == null) {
      throw new BusinessException(Error.RFQ_TYPE_NOT_FOUND);
    }
    return rfqTypeResponse.getData();
  }

  private boolean isValidImage(String allowedTypes, String originalFileName) throws Exception {
    if (!Arrays.stream(getAllowedExtension(allowedTypes))
        .anyMatch(getExtension(originalFileName)::equalsIgnoreCase)) {
      log.warn("File type invalid for file - {}", originalFileName);
      throw new BusinessException(Error.INVALID_FILE_TYPE);
    }
    return true;
  }

  private SystemParameterWebResponse getFileUploadPathSystemParameterResponse(String userName) {
    BaseRestResponse<SystemParameterWebResponse> systemParameterResponse =
        ltsBackendFeign.findByParameterName(userName, SystemParameterNames.FILE_UPLOAD_FOLDER_PATH)
            .toBlocking().value();

    if (!systemParameterResponse.isSuccess() || systemParameterResponse.getData() == null) {
      throw new BusinessException(Error.FILE_UPLOAD_PATH_NOT_FOUND);
    }
    return systemParameterResponse.getData();
  }

  private void saveFileToFolder(String userName, String originalFileName, String rfqId,
      MultipartFile file) throws IOException {
    String fileBasePath = getFileUploadPathSystemParameterResponse(userName).getParameterValue();
    log.debug("baseFilePath: {}", fileBasePath);
    final String folderNameWithPath = fileBasePath + File.separator + FilenameUtils.getName(rfqId);

    File directoryPath = new File(folderNameWithPath);
    if (!directoryPath.exists()) {
      directoryPath.mkdirs();
    }

    final String fileNameWithPath =
        folderNameWithPath + File.separator + FilenameUtils.getName(originalFileName);
    File itemFile = new File(fileNameWithPath);
    OutputStream outputStream = null;
    try {
      outputStream = new BufferedOutputStream(new FileOutputStream(itemFile));
      outputStream.write(file.getBytes());
      outputStream.flush();
    } finally {
      if (outputStream != null) {
        outputStream.close();
      }
    }
  }

  private RFQResponse activateRFQ(String userName, String rfqId) {
    BaseRestResponse<RFQResponse> rfqBaseResponse =
        this.ltsBackendFeign.activateRFQ(userName, rfqId).toBlocking().value();
    log.debug("rfq updated for rfqId : {}", rfqId);
    if (!rfqBaseResponse.isSuccess() || rfqBaseResponse.getData() == null) {
      throw new BusinessException(Error.RFQ_NOT_ACTIVATED);
    }

    return rfqBaseResponse.getData();
  }

  private RFQItemResponse updateRFQItem(String userName, String rfqItemId,
      String originalFileName) {
    RFQItemWebRequest rfqItemWebRequest = new RFQItemWebRequest();
    rfqItemWebRequest.setRfqItemId(Long.valueOf(rfqItemId));
    rfqItemWebRequest.setRfqItemFileName(originalFileName);
    BaseRestResponse<RFQItemResponse> rfqItemResponse =
        this.ltsBackendFeign.updateRFQItem(userName, rfqItemWebRequest).toBlocking().value();
    if (!rfqItemResponse.isSuccess() || rfqItemResponse.getData() == null) {
      throw new BusinessException(Error.RFQ_ITEM_NOT_UPDATED);
    }
    return rfqItemResponse.getData();
  }

  private String getExtension(String fileName) {
    String[] splits = fileName.split(Constants.SPLIT);
    return splits[splits.length - 1];
  }

  private String[] getAllowedExtension(String fileTypes) {
    return fileTypes.split(TYPES_SEPARATOR);
  }

}
