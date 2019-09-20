package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll()
    {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Book update(Book book, long id)
    {
        Book currentBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
        if(book.getTitle() != null)
        {
            currentBook.setTitle(book.getTitle());
        }
        if(book.getISBN() != null)
        {
            currentBook.setISBN(book.getISBN());
        }
        if(book.getCopy() != 0)
        {
            currentBook.setCopy(book.getCopy());
        }
        return bookRepository.save(currentBook);
    }

    @Override
    public void delete(long id)
    {
        if(bookRepository.findById(id).isPresent())
        {
            bookRepository.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }

    }
}
