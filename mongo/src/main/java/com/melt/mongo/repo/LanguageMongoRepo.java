package com.melt.mongo.repo;

import com.melt.mongo.domain.LanguageMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageMongoRepo extends MongoRepository<LanguageMongo, String> {
}
