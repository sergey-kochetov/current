package com.melt.star.controller;

import com.melt.star.model.Book;
import com.melt.star.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = {"/api"}, produces = "application/json")
public class HomeControllerRest {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Optional<Book> bookMaybe = bookRepository.findById(id);
        if (bookMaybe.isPresent()) {
            return new ResponseEntity<>(bookMaybe.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Book(), HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "aplication/jason")
    public Book postBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delBookById(@PathVariable("id") Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }

    @PutMapping("/{id}")
    public Book putBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Book patchBook(@PathVariable("id") Long id, @RequestBody Book newBook) {
        Book bookRefresh = bookRepository.findById(id).get();
        if (newBook.getAuthor() != null) {
            bookRefresh.setAuthor(newBook.getAuthor());
        }
        if (newBook.getName() != null) {
            bookRefresh.setName(newBook.getName());
        }
        return bookRepository.save(bookRefresh);
    }
}
