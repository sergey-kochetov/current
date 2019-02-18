package com.melt.grub.controller;

import com.google.common.collect.ImmutableList;
import com.melt.grub.GrubApplication;
import com.melt.grub.model.Site;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteControllerIT {

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void before() {
        mongoTemplate.dropCollection(Site.class);
        mongoTemplate.save(new Site("website1", "website1", "icon1", "title1", "description1"));
        mongoTemplate.save(new Site("website2", "website2", "icon2", "title2", "description2"));
    }

    @Test
    public void getListOfProviders() {
        ResponseEntity<List<Site>> responseEntity = restTemplate.exchange("http://localhost:9000/api", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Site>>() {
                });
        List<Site> actualList = responseEntity.getBody();
        // then
        assertThat(actualList.size(), is(2));
        List<String> actualId = actualList.stream()
                .map(site -> site.getId())
                .collect(collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        assertThat(actualId, containsInAnyOrder("website1", "website2"));

    }
}