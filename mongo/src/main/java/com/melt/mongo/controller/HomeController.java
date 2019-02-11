package com.melt.mongo.controller;

import com.melt.mongo.domain.LanguageMongo;
import com.melt.mongo.domain.LanguagePostgres;
import com.melt.mongo.repo.LanguageMongoRepo;
import com.melt.mongo.repo.LanguagePostgresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class HomeController {

    @Autowired
    private LanguageMongoRepo mongoRepo;

    @Autowired
    private LanguagePostgresRepo mySQLRepo;

    @GetMapping("/nosql")
    public Iterable<LanguageMongo> getAllLanguagesByMongo() {
        return mongoRepo.findAll();
    }

    @GetMapping({"/", "/sql"})
    public Iterable<LanguagePostgres> getAllLanguagesByMySQL() {
        return mySQLRepo.findAll();
    }
}
