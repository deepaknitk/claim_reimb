package com.gdn.lts.ui.controllers;

import com.gdn.lts.ui.model.constants.LtsUiApiPath;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.authController).build();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void login_test() throws Exception {
        this.mockMvc.perform(get(LtsUiApiPath.LOGIN)).andExpect(status().is3xxRedirection());
    }

    @Test
    public void logout_test() throws Exception {
        this.mockMvc.perform(get(LtsUiApiPath.LOGOUT)).andExpect(status().is3xxRedirection());
    }

    @Test
    public void secured_test() throws Exception {
        this.mockMvc.perform(get(LtsUiApiPath.SECURED)).andExpect(status().isOk());
    }

    @Test
    public void secured_loggedIn_test() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(
                new User("username", "password", Collections.EMPTY_LIST), null));
        this.mockMvc.perform(get(LtsUiApiPath.SECURED)).andExpect(status().isOk());
    }
}
