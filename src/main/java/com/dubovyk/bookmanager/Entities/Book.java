package com.dubovyk.bookmanager.Entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This is a class for representing
 * a Book table in the database. It stores such basic
 * information as a name and an author of the book.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue
    //@GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_name", unique = true, length = 250, nullable = false)
    private String Name;

    @ManyToOne
    @Cascade(value = CascadeType.PERSIST)
    @JoinColumn(name ="author_id")
    private Author author;

    public Book(){}

    public Book(String name, Author author) {
        Name = name;
        this.author = author;
    }

    @Override
    public String toString(){
        return "\"" + getName() + "\" by " + getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
