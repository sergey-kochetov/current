package com.melt.rest.repo;

import com.melt.rest.domain.Language;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LanguageRepo extends ReactiveMongoRepository<Language, String> {

    Mono<Language> findByName(String name);
}

