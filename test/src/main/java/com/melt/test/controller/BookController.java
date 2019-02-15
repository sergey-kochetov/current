package com.melt.test.controller;

import com.melt.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String getMain() {
        return "index";
    }

    @GetMapping("/books")
    public String getAllBook(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }
}
