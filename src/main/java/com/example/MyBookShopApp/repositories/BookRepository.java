package com.example.MyBookShopApp.repositories;

import com.example.MyBookShopApp.data.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
}
