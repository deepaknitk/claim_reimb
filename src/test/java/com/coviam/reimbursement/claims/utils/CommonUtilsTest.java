package com.coviam.reimbursement.claims.utils;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.coviam.reimbursement.claims.utils.CommonUtils;

import java.util.Collections;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class CommonUtilsTest {

    @Test
    public void getUserNameTest() {
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(
                new User("username", "password", Collections.EMPTY_LIST), null));
        Assert.assertEquals("username", CommonUtils.getUserName());
    }

    @Test
    public void getUserNameTest_principalNull() {
        SecurityContextHolder.getContext()
            .setAuthentication(new UsernamePasswordAuthenticationToken(null, null));
        Assert.assertEquals(null, CommonUtils.getUserName());
    }

    @Test
    public void getUserNameTest_authNull() {
        SecurityContextHolder.getContext().setAuthentication(null);
        Assert.assertEquals(null, CommonUtils.getUserName());
    }
}
