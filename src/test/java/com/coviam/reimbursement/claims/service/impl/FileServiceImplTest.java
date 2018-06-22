package com.coviam.reimbursement.claims.service.impl;

import com.coviam.reimbursement.claims.TestUtils;
import com.coviam.reimbursement.claims.service.impl.FileServiceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author manika_singh
 * @since 11/06/18
 */
public class FileServiceImplTest {

    @InjectMocks
    private FileServiceImpl fileService;

    /*private RFQTypeResponse rfqTypeResponse;
    private BaseRestResponse<RFQTypeResponse> rfqTypeBaseResponse;
    private SystemParameterWebResponse systemParameterWebResponse;
    private BaseRestResponse<SystemParameterWebResponse> systemParameterBaseResponse;
    private RFQResponse rfqResponse;
    private BaseRestResponse<RFQResponse> rfqBaseRestResponse;
    private RFQItemWebRequest rfqItemWebRequest;
    private MockMultipartFile multipartFile;
    private byte[] image;
    private RFQItemResponse rfqItemResponse;
    private BaseRestResponse<RFQItemResponse> rfqItemBaseRestResponse;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        File file = new File(getClass().getClassLoader().getResource("test.PNG").getFile());
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        rfqTypeResponse = new RFQTypeResponse();
        rfqTypeResponse.setRfqTypeAllowedFileTypes("png|jpg");
        rfqTypeResponse.setRfqTypeCode("RFQ_BULK");
        rfqTypeBaseResponse = new BaseRestResponse<>(true, rfqTypeResponse);
        systemParameterWebResponse = new SystemParameterWebResponse();
        systemParameterWebResponse.setParameterValue("lts-test-data");
        systemParameterBaseResponse = new BaseRestResponse<>(true, systemParameterWebResponse);

        rfqResponse = new RFQResponse();
        rfqBaseRestResponse = new BaseRestResponse<>(true, rfqResponse);
        rfqItemWebRequest = new RFQItemWebRequest();
        rfqItemWebRequest.setRfqItemId(Long.valueOf(TestUtils.ID));
        rfqItemWebRequest.setRfqItemFileName(file.getName());
        rfqItemResponse = new RFQItemResponse();
        rfqItemResponse.setRfqItemId(Long.valueOf(TestUtils.ID));
        rfqItemBaseRestResponse = new BaseRestResponse<>(true, rfqItemResponse);

        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
        }
        image = bos.toByteArray();
        fis.close();
        this.multipartFile = new MockMultipartFile("file", "filename.PNG", "image/png", image);

        when(this.ltsBackendFeign.findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID))
            .thenReturn(Single.just(rfqTypeBaseResponse));
        when(this.ltsBackendFeign
            .findByParameterName(TestUtils.USERNAME, SystemParameterNames.FILE_UPLOAD_FOLDER_PATH))
            .thenReturn(Single.just(systemParameterBaseResponse));
        when(this.ltsBackendFeign.activateRFQ(TestUtils.USERNAME, TestUtils.ID))
            .thenReturn(Single.just(rfqBaseRestResponse));
        when(this.ltsBackendFeign
            .updateRFQItem(eq(TestUtils.USERNAME), any(RFQItemWebRequest.class)))
            .thenReturn(Single.just(rfqItemBaseRestResponse));

    }

    @Test
    public void saveFileTest() throws Exception {
        this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);

        verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
        verify(this.ltsBackendFeign)
            .findByParameterName(TestUtils.USERNAME, SystemParameterNames.FILE_UPLOAD_FOLDER_PATH);
        verify(this.ltsBackendFeign).activateRFQ(TestUtils.USERNAME, TestUtils.ID);
        verify(this.ltsBackendFeign).updateRFQItem(eq(TestUtils.USERNAME), any(RFQItemWebRequest.class));

    }

    @Test
    public void saveFileMultilineRFQTest() throws Exception {
        rfqTypeResponse.setRfqTypeCode(Constants.RFQ_TYPE_MULTILINE);
        rfqTypeBaseResponse.setData(rfqTypeResponse);

        this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);

        verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
        verify(this.ltsBackendFeign)
            .findByParameterName(TestUtils.USERNAME, SystemParameterNames.FILE_UPLOAD_FOLDER_PATH);
        verify(this.ltsBackendFeign).updateRFQItem(eq(TestUtils.USERNAME), any(RFQItemWebRequest.class));
    }

    @Test
    public void saveFileTestRFQTypeNotFound() throws Exception {
        rfqTypeBaseResponse.setSuccess(false);
        try {
          this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        } catch (BusinessException be) {
            assertEquals(be.getCode(), Error.RFQ_TYPE_NOT_FOUND.getCode());
            verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
        }
    }

    @Test
    public void saveFileTestRFQTypeDataNullFound() throws Exception {
        rfqTypeBaseResponse.setData(null);
        try {
          this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        } catch (BusinessException be) {
            assertEquals(be.getCode(), Error.RFQ_TYPE_NOT_FOUND.getCode());
            verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
        }
    }

    @Test
    public void saveFileTestSystemParameterNotFound() throws Exception {
        systemParameterBaseResponse.setSuccess(false);
        try {
          this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        } catch (BusinessException be) {
            assertEquals(be.getCode(), Error.FILE_UPLOAD_PATH_NOT_FOUND.getCode());
            verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
            verify(this.ltsBackendFeign).findByParameterName(TestUtils.USERNAME,
                SystemParameterNames.FILE_UPLOAD_FOLDER_PATH);
        }
    }

    @Test
    public void saveFileTestSystemParameterNull() throws Exception {
        systemParameterBaseResponse.setData(null);
        try {
          this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        } catch (BusinessException be) {
            assertEquals(be.getCode(), Error.FILE_UPLOAD_PATH_NOT_FOUND.getCode());
            verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
            verify(this.ltsBackendFeign).findByParameterName(TestUtils.USERNAME,
                SystemParameterNames.FILE_UPLOAD_FOLDER_PATH);
        }
    }

    @Test
    public void saveFileTestActivateRFQFail() throws Exception {
        rfqBaseRestResponse.setSuccess(false);
        try {
          this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        } catch (BusinessException be) {
            assertEquals(be.getCode(), Error.RFQ_NOT_ACTIVATED.getCode());
            verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
            verify(this.ltsBackendFeign).findByParameterName(TestUtils.USERNAME,
                SystemParameterNames.FILE_UPLOAD_FOLDER_PATH);
            verify(this.ltsBackendFeign).activateRFQ(TestUtils.USERNAME, TestUtils.ID);
            verify(this.ltsBackendFeign).updateRFQItem(eq(TestUtils.USERNAME), any(RFQItemWebRequest.class));
        }
    }

    @Test
    public void saveFileTestActivateRFQNull() throws Exception {
        rfqBaseRestResponse.setData(null);
        try {
          this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        } catch (BusinessException be) {
            assertEquals(be.getCode(), Error.RFQ_NOT_ACTIVATED.getCode());
            verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
            verify(this.ltsBackendFeign).findByParameterName(TestUtils.USERNAME,
                SystemParameterNames.FILE_UPLOAD_FOLDER_PATH);
            verify(this.ltsBackendFeign).activateRFQ(TestUtils.USERNAME, TestUtils.ID);
            verify(this.ltsBackendFeign).updateRFQItem(eq(TestUtils.USERNAME), any(RFQItemWebRequest.class));
        }
    }

    @Test
    public void saveFileTestUpdateRFQItemNull() throws Exception {
        rfqItemBaseRestResponse.setData(null);
        try {
          this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        } catch (BusinessException be) {
            assertEquals(be.getCode(), Error.RFQ_ITEM_NOT_UPDATED.getCode());
            verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
            verify(this.ltsBackendFeign).findByParameterName(TestUtils.USERNAME,
                SystemParameterNames.FILE_UPLOAD_FOLDER_PATH);
            verify(this.ltsBackendFeign)
                .updateRFQItem(eq(TestUtils.USERNAME), any(RFQItemWebRequest.class));
        }
    }

    @Test
    public void saveFileExceptionTest() {
        when(this.ltsBackendFeign.findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID))
            .thenThrow(Exception.class);
        try {
          this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        } catch (Exception e) {
            verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
        }
    }

    @Test
    public void saveFileTestInvalidImage() throws Exception {
        this.multipartFile = new MockMultipartFile("file", "filename.xls", "image/png", image);
        try {
          this.fileService.saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        } catch (BusinessException be) {
            assertEquals(be.getCode(), Error.INVALID_FILE_TYPE.getCode());
            verify(this.ltsBackendFeign).findByRFQTypeId(TestUtils.USERNAME, TestUtils.ID);
        }
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(this.ltsBackendFeign);
    }*/
}
