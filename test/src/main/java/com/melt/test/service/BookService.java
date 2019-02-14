package com.melt.test.service;

import com.melt.test.domain.Book;
import com.melt.test.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Iterable<Book> getAllBook() {
        return bookRepository.findAll();
    }
}
