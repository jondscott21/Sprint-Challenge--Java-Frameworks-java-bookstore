package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController
{
    @Autowired
    AuthorService authorService;

    // GET localhost:2019/authors
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DATA') or hasRole('ROLE_USER')")
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> findAllAuthors()
    {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }

    // POST localhost:2019/data/books/{bookid}/authors/{authorid}
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DATA')")
//    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}")

}
