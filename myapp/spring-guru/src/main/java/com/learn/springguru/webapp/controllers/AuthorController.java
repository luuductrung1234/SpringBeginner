package com.learn.springguru.webapp.controllers;

import com.learn.springguru.webapp.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors")
    public String getAuthors(Model model) {
        var authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors/list";
    }
}
