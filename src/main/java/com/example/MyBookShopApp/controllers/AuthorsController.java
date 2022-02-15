package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.services.AuthorService;
import com.example.MyBookShopApp.data.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    private final AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<Author>> authorsMap() {
        return authorService.getAuthorsMap();
    }

    @GetMapping("/authors")
    public String authorsPage(Model model) {
        model.addAttribute("authorData", authorService.getAuthorData());
        return "/authors/index";
    }

}
