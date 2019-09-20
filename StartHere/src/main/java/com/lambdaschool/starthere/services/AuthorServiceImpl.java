package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    @Override
    public List<Author> findAll(Pageable pageable)
    {
        List<Author> list = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void addBook(long bookid, long authorid)
    {
        authorRepository.addBookToAuthor(bookid, authorid);
    }
}
