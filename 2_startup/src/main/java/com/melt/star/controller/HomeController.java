package com.melt.star.controller;

import com.melt.star.model.Book;
import com.melt.star.repo.BookRepository;
import com.melt.star.service.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BossService bossService;

    @GetMapping
    public String getIndex(Model model, Book book) {
        model.addAttribute("books", bookRepository.findAll());
        if (book == null) {
            book = new Book();
        }
        model.addAttribute("book", book);
        //model.addAttribute("boss", bossService.getAnswerById("1").getData());
        return "index";
    }

    @PostMapping
    public String addBook(@Valid Book book, Errors errors) {
        if (errors.hasErrors()) {
            return "index";
        }
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
    public String updateBook(@PathVariable("id") Long id, @Valid Book book, Errors errors) {
        if (errors.hasErrors()) {
            return "edit";
        }
        bookRepository.save(book);
        return "redirect:/";
    }
}
