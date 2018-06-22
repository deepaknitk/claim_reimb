package com.coviam.reimbursement.claims.apis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.coviam.reimbursement.claims.apis.IndexController;
import com.coviam.reimbursement.claims.model.constants.LtsUiApiPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class IndexPageControllerTest {

    @InjectMocks
    private IndexController indexPageController;

    private MockMvc mockMvc;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.indexPageController).build();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void index_test() throws Exception {
        this.mockMvc.perform(get(LtsUiApiPath.BASE + LtsUiApiPath.LTS_UI + LtsUiApiPath.WILD_CARD)).andExpect(status().isOk());
    }

    // @Test
    // public void createRFQ_test() throws Exception {
    //     this.mockMvc.perform(get("/rfq")).andExpect(status().isOk());
    // }
}
