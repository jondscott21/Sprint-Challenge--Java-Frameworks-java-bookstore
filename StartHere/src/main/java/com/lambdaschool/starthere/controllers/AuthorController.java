package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.AuthorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.URIException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class AuthorController
{
    @Autowired
    AuthorService authorService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    // GET localhost:2019/authors
    @ApiOperation(value = "Lists all Courses Pageable", response = Author.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Authors Found", response = Author.class),
            @ApiResponse(code = 404, message = "Authors Not Found", response = ErrorDetail.class)})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DATA') or hasRole('ROLE_USER')")
    @GetMapping(value = "/authors", produces = {"application/json"})
    public ResponseEntity<?> findAllAuthors(@PageableDefault(page = 0, size= 3, sort = "title", direction = Sort.Direction.DESC) Pageable pagable)
    {
        return new ResponseEntity<>(authorService.findAll(pagable), HttpStatus.OK);
    }

     // POST localhost:2019/data/books/{bookid}/authors/{authorid}
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DATA')")
    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}", consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> saveBookToAuthor(HttpServletRequest request, @Valid @RequestBody Author author, @PathVariable long bookid, @PathVariable long authorid) throws URISyntaxException
    {
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI authorURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{authorid}")
//                .buildAndExpand(author.getAuthorid())
//                .toUri();
//        responseHeaders.setLocation(authorURI);
        authorService.addBook(bookid, authorid);
        return new ResponseEntity<>(null,  HttpStatus.CREATED);
    }

}
