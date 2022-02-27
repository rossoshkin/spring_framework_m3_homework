package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthorData() {
        return authorRepository.findAll();
    }

    public Map<String, List<Author>> getAuthorsMap() {
        List<Author> authors = getAuthorData();
        return authors.stream().collect(Collectors.groupingBy((Author a) -> {return a.getLastName().substring(0,1);}));
    }
}
