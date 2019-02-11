package com.melt.mongo;

import com.melt.mongo.repo.LanguageMongoRepo;
import com.melt.mongo.repo.LanguagePostgresRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class);
    }

    @Bean
    public CommandLineRunner preload(LanguagePostgresRepo postgres, LanguageMongoRepo mongo) {
        return args -> {
//            mongo.save(new LanguageMongo("java", "serg", "imperative"));
//            mongo.save(new LanguageMongo("java", "taya", "no data"));
//            mongo.save(new LanguageMongo("java 8", "noname", "lola"));
//            mongo.save(new LanguageMongo("java 9", "noname2", "lola2"));
//
//            postgres.save(new LanguagePostgres("java", "serg", "imperative"));
//            postgres.save(new LanguagePostgres("java", "taya", "no data"));
//            postgres.save(new LanguagePostgres("java 8", "noname", "lola"));
//            postgres.save(new LanguagePostgres("java 9", "noname2", "lola2"));
        };
    }
}
