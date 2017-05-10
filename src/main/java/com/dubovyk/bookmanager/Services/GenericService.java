package com.dubovyk.bookmanager.Services;

import java.io.Serializable;
import java.util.List;

/**
 * This interface declares methods of a generic
 * service layer object of the book management project.
 *
 * Basically it provides simplest CRUD operations, which
 * can be extended or modified according to the logic of
 * the application in the child classes/interfaces.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public interface GenericService<T, PK extends Serializable> {
    List<T> findAll();
    T findById(PK id);
    void save(T entry);
    void update(T entry);
    void delete(T entry);
    void deleteById(PK id);
}
