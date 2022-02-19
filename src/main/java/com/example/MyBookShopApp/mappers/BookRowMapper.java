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
        book.setFirstName(rs.getString("first_name"));
        book.setLastName(rs.getString("last_name"));
        book.setTitle(rs.getString("title"));
        book.setPriceOld(rs.getString("price_old"));
        book.setPrice(rs.getString("price"));
        return book;
    }
}
