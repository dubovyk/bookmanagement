package com.dubovyk.bookmanager.Services.AuthorService;

import com.dubovyk.bookmanager.Entities.Author;
import com.dubovyk.bookmanager.Services.GenericService;

/**
 * This is an interface for Author services.
 *
 * This interface is used to extend the
 * GenericService with findOneByName() method,
 * which allows to find an author by given name.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public interface AuthorService extends GenericService<Author, Long>{
    /**
     * @param name A name of the searched author
     * @return Author object if exists or null.
     */
    Author findOneByName(String name);
}
