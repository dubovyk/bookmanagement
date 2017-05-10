package com.dubovyk.bookmanager.Entities;


import javax.persistence.*;
import java.util.List;

/**
 * This class represents an Author table in
 * the database. We use it instead of just
 * a text field in Book table, as it makes
 * design of the db more scalable.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
@Entity
@Table(name = "Author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {}

    public Author(String name){
        this.name = name;
    }

    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
