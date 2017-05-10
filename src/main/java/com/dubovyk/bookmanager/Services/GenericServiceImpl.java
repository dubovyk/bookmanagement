package com.dubovyk.bookmanager.Services;

import com.dubovyk.bookmanager.DAO.GenericDAO;

import java.io.Serializable;
import java.util.List;

/**
 * This class provides basic implementation
 * of the service objects logic. It should
 * extended or modified in child classes.
 *
 * As for now it simply proxies each method
 * to the following DAO method.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public abstract class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {
    protected GenericDAO<T, PK> dao;

    /**
     * We need this parameter, as we don`t use any kind of DI.
     *
     * @param dao A DAO to be used by service.
     */
    public GenericServiceImpl(GenericDAO<T, PK> dao){
        this.dao = dao;
    }

    /**
     * Simple CREATE implementation.
     *
     * @param entry Object to be stored in database. If contains duplicated
     *              primary key or other schema violation, exception is thrown.
     */
    @Override
    public void save(T entry){
        dao.save(entry);
    }

    /**
     * Simple UPDATE implementation.
     *
     * @param entry Object to be stored in database. If not found, throws
     *              exception.
     */
    @Override
    public void update(T entry){
        dao.update(entry);
    }

    /**
     * Simple DELETE implementation.
     *
     * @param entry Object to be removed from database. If not found, throws
     *              exception.
     */
    @Override
    public void delete(T entry){
        dao.delete(entry);
    }

    /**
     * Deletes an element by its PK value.
     *
     * @param entry PK of an object to be removed from database. If not found, throws
     *              exception.
     */
    @Override
    public void deleteById(PK id){
        dao.deleteById(id);
    }

    /**
     * Simple READ implementation. Returns an object with
     * given PK.
     *
     * @param id A value of PK of the object to be found.
     * @return An object with given PK or null if such object not found.
     */
    @Override
    public T findById(PK id){
        return dao.findById(id);
    }


    /**
     * Simple READ implementation. Returns all objects
     * in the table.
     *
     * @return A list of all T objects in the database table.
     */
    @Override
    public List<T> findAll(){
        return dao.findAll();
    }
}
