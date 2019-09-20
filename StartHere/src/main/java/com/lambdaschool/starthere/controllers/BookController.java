package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BookController
{
    @Autowired
    BookService bookService;

    // GET localhost:2019/books
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DATA') or hasRole('ROLE_USER')")
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> findAllBooks()
    {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    // PUT localhost:2019/data/books/{id}
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DATA')")
    @PutMapping(value = "/data/books/{id}")
    public ResponseEntity<?> updateBook(HttpServletRequest request,
                                        @RequestBody Book updateBook,
                                        @PathVariable long id)
    {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // DELETE /data/books/{id}
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DATA')")
    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
