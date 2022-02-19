package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepositoryJdbcTemplate implements AuthorRepository{

    private JdbcTemplate jdbcTemplate;

    public AuthorRepositoryJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = jdbcTemplate.query("SELECT * from authors", (ResultSet rs, int rowNum) -> {
            Author author = new Author();
            author.setAuthor_id(rs.getInt("author_id"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            return author;
        });
        return new ArrayList<>(authors);
    }
}
