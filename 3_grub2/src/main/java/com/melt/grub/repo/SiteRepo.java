package com.melt.grub.repo;

import com.melt.grub.model.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteRepo extends MongoRepository<Site, String> {
    List<Site> findByWebsite(String website);
}
