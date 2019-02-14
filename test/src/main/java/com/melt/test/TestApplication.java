package com.melt.test;

import com.melt.test.domain.Book;
import com.melt.test.repo.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            bookRepository.save(new Book("book1", "Serg"));
            bookRepository.save(new Book("book2", "Taya"));
            bookRepository.save(new Book("book3", "Nina"));
            bookRepository.save(new Book("book4", "Lina"));
        };
    }
}
