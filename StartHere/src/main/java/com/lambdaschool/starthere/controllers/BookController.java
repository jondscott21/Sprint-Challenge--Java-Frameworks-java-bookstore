package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    // GET localhost:2019/books
    @ApiOperation(value = "Lists all Courses Pageable", response = Book.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Authors Found", response = Book.class),
            @ApiResponse(code = 404, message = "Authors Not Found", response = ErrorDetail.class)})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DATA') or hasRole('ROLE_USER')")
    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> findAllBooks(@PageableDefault(page = 0, size= 3, sort = "title", direction = Sort.Direction.DESC)Pageable pageable)
    {
        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
    }

    // PUT localhost:2019/data/books/{id}
    @ApiOperation(value = "Edit a Book based on Id", response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Book Edited", response = void.class),
            @ApiResponse(code = 500, message = "Book Editing Failed", response = ErrorDetail.class)})
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
    @ApiOperation(value = "Delete Books By Id", response = void.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Book Deleted", response = void.class),
            @ApiResponse(code = 500, message = "Book Delete Failed", response = ErrorDetail.class)})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DATA')")
    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
