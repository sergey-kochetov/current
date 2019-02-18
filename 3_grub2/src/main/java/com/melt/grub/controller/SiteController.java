package com.melt.grub.controller;

import com.melt.grub.model.Site;
import com.melt.grub.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @RequestMapping
    public List<Site> getListOfProviders() {
        return siteService.findAll();
    }
}
