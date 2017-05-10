package com.dubovyk.bookmanager.DAO.Author;

import com.dubovyk.bookmanager.DAO.GenericDAO;
import com.dubovyk.bookmanager.Entities.Author;

/**
 * A simple DAO interface for accessing
 * authors in the database. Extends
 * GenericDAO interface and adds method
 * to find author by his name.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public interface AuthorDAO extends GenericDAO<Author, Long> {
    /**
     * @param name Authors name to be found.
     * @return If there`s such author in db, it`s returned, else null is returned.
     */
    Author findByName(final String name);
}
