package com.example.MyBookShopApp.mappers;

import com.example.MyBookShopApp.data.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setAuthor(rs.getString("author_name"));
        book.setTitle(rs.getString("title"));
        book.setPriceOld(rs.getString("priceOld"));
        book.setPrice(rs.getString("price"));
        return book;
    }
}
