package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.mappers.BookRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryJdbcTemplate implements BookRepository{

    private JdbcTemplate jdbcTemplate;

    public BookRepositoryJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getAllBooks() {

        String sql = "SELECT books.id, authors.author_name, " +
                "books.title, books.priceOld, books.price FROM books, " +
                "authors WHERE authors.author_id = books.author_id";

        List<Book> books = jdbcTemplate.query(sql, new BookRowMapper());
        return books;
    }
}
