package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;

import java.util.List;

public interface BookService
{
    List<Book> findAll();

    Book update(Book book, long id);

    void delete(long id);
}
