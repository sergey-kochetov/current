package com.crud.melt;

import com.crud.melt.model.Book;
import com.crud.melt.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerDemoApplication.class);
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
