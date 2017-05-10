package com.dubovyk.bookmanager.DAO.Book;

import com.dubovyk.bookmanager.DAO.GenericDAO;
import com.dubovyk.bookmanager.Entities.Book;

import java.util.List;

/**
 * This is an interface of accessing
 * database table of books. Implementing
 * classes should provide methods for basic
 * CRUD-operations and some additional ones.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public interface BookDAO extends GenericDAO<Book, Long> {
    /**
     * @param name A string name of the book to be found
     * @return If found non 0 results returns a list with them, else empty List of Book items is returned.
     */
    List<Book> getAllBooksByName(final String name);
}
