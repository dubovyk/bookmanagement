package com.dubovyk.bookmanager.Services.BookService;

import com.dubovyk.bookmanager.Entities.Book;
import com.dubovyk.bookmanager.Services.GenericService;

import java.util.List;

/**
 * This is an interface of the BookService which
 * is used to operate with books logic.
 *
 * It extends GenericService with a method,
 * which allows to find all books with given
 * name.
 *
 * @see GenericService
 * @see BookServiceImpl
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public interface BookService extends GenericService<Book, Long>{
    /**
     * @param name A name of desired books.
     * @return A list of books with given name or an empty list.
     */
    List<Book> findAllByName(String name);
}
