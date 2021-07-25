package com.learn.springguru.webapp.controllers;

import com.learn.springguru.webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model) {
        var books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "books/list";
    }
}
