package com.melt.star.controller;

import com.melt.star.model.Book;
import com.melt.star.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("newbook", new Book());
        return "index";
    }

    @PostMapping
    public String addBook(Model model, Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable("id") Long id, Model model) {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isPresent()) {
            model.addAttribute("book", byId.get());
        } else {
            model.addAttribute("book", new Book());
        }
        return "show";
    }

    @GetMapping("/{id}/delete")
    public String delById(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editFormById(@PathVariable("id") Long id, Model model) {
        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isPresent()) {
            model.addAttribute("book", byId.get());
        } else {
            model.addAttribute("book", new Book());
        }
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") Long id, Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }
}
