/*
 * Copyright (C) 2018 PT Global Digital Niaga - All Rights Reserved
 *
 * NOTICE: All information contained herein is, and remains the property of PT Global Digital Niaga.
 *
 * Dissemination of this information or reproduction of this material is strictly forbidden.
 */
package com.coviam.reimbursement.claims.service.api;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author manika_singh
 * @since 12/06/18
 */
public interface FileService {
/*
  *//**
   * saveFile method allows to save the file into the specified folder and updates the RFQItem with
   * appropriate details
   *
   * @param username
   * @param rfqTypeId
   * @param rfqItemId
   * @param rfqId
   * @param file
   * @throws Exception
   *//*
  void saveFile(String rfqTypeId, String rfqId, String rfqItemId, String username,
      MultipartFile file) throws Exception;*/
}

