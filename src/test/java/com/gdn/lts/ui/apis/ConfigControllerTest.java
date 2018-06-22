package com.gdn.lts.ui.apis;

import com.gdn.lts.ui.TestUtils;
import com.gdn.lts.ui.config.properties.FileServerProperties;
import com.gdn.lts.ui.model.constants.Constants;
import com.gdn.lts.ui.model.constants.LtsUiApiPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class ConfigControllerTest {

    @InjectMocks
    private ConfigController propertiesController;

    private MockMvc mockMvc;
    private FileServerProperties fileServerProperties;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.propertiesController).build();

        this.fileServerProperties = new FileServerProperties();

        ReflectionTestUtils
            .setField(this.propertiesController, "fileSizeInMB", TestUtils.FILE_SIZE_MB);
        ReflectionTestUtils
            .setField(this.propertiesController, "fileServerProperties", this.fileServerProperties);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getClientParams_test() throws Exception {
        this.mockMvc.perform(get(LtsUiApiPath.GET_CONFIGS)).andExpect(status().isOk())
            .andExpect(jsonPath("$.success", equalTo(true)))
            .andExpect(jsonPath("$.data." + Constants.FILE_SIZE_MB, equalTo("25")));
    }

    @Test
    public void getClientParams_fileSizeInvalid_test() throws Exception {
        ReflectionTestUtils
            .setField(this.propertiesController, "fileSizeInMB", "");

        this.mockMvc.perform(get(LtsUiApiPath.GET_CONFIGS)).andExpect(status().isOk())
            .andExpect(jsonPath("$.success", equalTo(true)))
            .andExpect(jsonPath("$.data." + Constants.FILE_SIZE_MB, equalTo("2")));
    }
}
