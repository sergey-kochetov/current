package com.melt.grub.service;

import com.melt.grub.model.Site;
import com.melt.grub.repo.SiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class SiteService {

    @Autowired
    private SiteRepo siteRepo;

    private static List<Site> items = new ArrayList<>();
    static {
        items.add(new Site("1", "web1", "http://horizonteaustralia.com/uploads/3/5/1/3/35133503/published/icon-blue-globe.png?1500672733", "title1", "desc1"));
        items.add(new Site("2", "web2", "http://www.20azmoon.ir/image/data/homee.png", "title2", "desc2"));
        items.add(new Site("3", "web3", "http://www.essexhoa.com/images/visit-website.png", "title3", "desc3"));
        items.add(new Site("4", "web4", "http://internety.pro/img/icons/design2.png", "title4", "desc4"));
    }

    @PostConstruct
    public void addSite() {
        siteRepo.saveAll(items);
    }

    public List<Site> findAll() {
        return items;
    }
}
