package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    private String lname;
    private String fname;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "wrote", joinColumns = @JoinColumn(name = "authorid"), inverseJoinColumns = @JoinColumn(name = "bookid"))
    @JsonIgnoreProperties({"authors"})
    private List<Book> books = new ArrayList<>();

    public Author()
    {
    }

    public Author(String firstname, String lastname, List<Book> books)
    {
        this.fname = firstname;
        this.lname = lastname;
        this.books = books;
    }

    public long getAuthorid()
    {
        return authorid;
    }

    public void setAuthorid(long authorid)
    {
        this.authorid = authorid;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public List<Book> getBooks()
    {
        return books;
    }

    public void setBooks(List<Book> books)
    {
        this.books = books;
    }
}
