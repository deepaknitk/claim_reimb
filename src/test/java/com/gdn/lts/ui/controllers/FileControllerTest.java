package com.gdn.lts.ui.controllers;

import com.gdn.lts.backend.master.model.constants.LtsApiPath;
import com.gdn.lts.backend.master.model.enums.Error;
import com.gdn.lts.backend.master.model.exceptions.BusinessException;
import com.gdn.lts.ui.TestUtils;
import com.gdn.lts.ui.service.api.FileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;


/**
 * @author manika_singh
 * @since 12/06/18
 */
public class FileControllerTest {
    private MockMvc mockMvc;
    private MockMultipartFile multipartFile;
    private byte[] image;

    @InjectMocks
    private FileController fileController;

    @Mock
    private FileService fileService;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.fileController).build();
        File file = new File(getClass().getClassLoader().getResource("test.PNG").getFile());
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
        }

        image = bos.toByteArray();
        fis.close();
        this.multipartFile = new MockMultipartFile("file", "filename.png", "image/png", image);

    }

    @Test
    public void saveFileTest() throws Exception {

        this.mockMvc.perform(fileUpload(LtsApiPath.FILE + LtsApiPath.FILE_UPLOAD).file(this.multipartFile)
            .param("requestId", TestUtils.REQUEST_ID).param("storeId", TestUtils.STORE_ID)
            .param("clientId", TestUtils.CLIENT_ID).param("userName", TestUtils.USERNAME)
            .param("channelId", TestUtils.CHANNEL_ID).param("rfqItemId", TestUtils.ID)
            .param("rfqId", TestUtils.ID).param("rfqTypeId", TestUtils.ID))
            .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(true)));
    }

    @Test
    public void saveFileBusinessExceptionTest() throws Exception {

        doThrow(new BusinessException(Error.SYSTEM_PARAMETER_NOT_FOUND)).when(this.fileService)
            .saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        this.mockMvc.perform(fileUpload(LtsApiPath.FILE + LtsApiPath.FILE_UPLOAD).file(this.multipartFile)
            .param("requestId", TestUtils.REQUEST_ID).param("storeId", TestUtils.STORE_ID)
            .param("clientId", TestUtils.CLIENT_ID).param("userName", TestUtils.USERNAME)
            .param("channelId", TestUtils.CHANNEL_ID).param("rfqItemId", TestUtils.ID)
            .param("rfqId", TestUtils.ID).param("rfqTypeId", TestUtils.ID))
            .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(false)));
    }

    @Test
    public void saveFileExceptionTest() throws Exception {

        doThrow(new Exception()).when(this.fileService)
            .saveFile(TestUtils.ID, TestUtils.ID, TestUtils.ID, TestUtils.USERNAME, multipartFile);
        this.mockMvc.perform(fileUpload(LtsApiPath.FILE + LtsApiPath.FILE_UPLOAD).file(this.multipartFile)
            .param("requestId", TestUtils.REQUEST_ID).param("storeId", TestUtils.STORE_ID)
            .param("clientId", TestUtils.CLIENT_ID).param("userName", TestUtils.USERNAME)
            .param("channelId", TestUtils.CHANNEL_ID).param("rfqItemId", TestUtils.ID)
            .param("rfqId", TestUtils.ID).param("rfqTypeId", TestUtils.ID))
            .andExpect(status().isOk()).andExpect(jsonPath("$.success", equalTo(false)));
    }
}
