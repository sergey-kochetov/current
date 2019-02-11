package com.melt.mongo.repo;

import com.melt.mongo.domain.LanguagePostgres;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguagePostgresRepo extends CrudRepository<LanguagePostgres, Long> {
}
