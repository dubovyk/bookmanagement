package com.dubovyk.bookmanager.DAO;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * This interface is used to define the
 * default methods (basically that are just
 * a core CRUD operations).
 *
 * Must be extended/implemented by all other
 * DAO interfaces/classes.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.2
 */
public interface GenericDAO<T, PK extends Serializable> {

    /**
     * Basic operation to find an item by its id (of its Primary Key type).
     *
     * @param id The value of items Primary Key in table.
     * @return In case of successful search return a found T object, else
     *           null is returned.
     */
    T findById(final PK id);

    /**
     * @return All items in the desired table or an empty list.
     */
    List<T> findAll();

    /**
     * @param t An object to be saved.
     * @return In case of successful operation a value of Primary Key of object is returned.
     */
    PK save(final T t);

    /**
     * @param t An object to be modified (by PK value). If no such object found --
     *          throws TransientObjectException or StaleStateException.
     * @see org.hibernate.TransientObjectException
     * @see org.hibernate.StaleStateException
     */
    void update(final T t);

    /**
     * @param t An object to be deleted (by PK value). If no such object found --
     *          throws EntityNotFoundException.
     *
     * @see javax.persistence.EntityNotFoundException
     */
    void delete(final T t);

    /**
     * @param id A value of PK of an object to
     *           be deleted (by PK value). If no such object found --
     *           throws EntityNotFoundException.
     *
     * @see javax.persistence.EntityNotFoundException
     */
    void deleteById(PK id);
}
