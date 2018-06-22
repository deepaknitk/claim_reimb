package com.gdn.lts.ui.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

/**
 * Created by jugalkishorsahu on Feb, 2018
 */
public class ViewConfigTest {

    @InjectMocks
    private ViewConfig viewConfig;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void reactViewResolverTest() {
        this.viewConfig.reactViewResolver();
    }

    @Test
    public void reactConfigurerTest() {
        this.viewConfig.reactConfigurer();
    }
}
