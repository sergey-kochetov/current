package com.melt.grub.controller;

import com.melt.grub.model.Site;
import com.melt.grub.service.SiteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SiteControllerTest {

    @Mock
    private SiteService siteService;

    @InjectMocks
    private SiteController siteController;

    @Test
    public void getListOfProviders() {
        //given
        when(siteService.findAll()).thenReturn(Collections.emptyList());
        // when
        List<Site> list = siteController.getListOfProviders();
        //then
        verify(siteService).findAll();
    }
}