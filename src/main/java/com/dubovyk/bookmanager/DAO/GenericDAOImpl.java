package com.dubovyk.bookmanager.DAO;

import com.dubovyk.bookmanager.Persistance.HibernateUtil;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 *
 * This is a generic implementation of the data access object
 * used in the project. It provides basic CRUD operations
 * and must be extended by each other DAO implementation.
 *
 * @see GenericDAO
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public abstract class GenericDAOImpl<T, PK extends Serializable> implements GenericDAO<T, PK>{
    private Class<T> clas;

    /**
     * @param classToUse We need this as can`t get class directly, while it`s
     *                   necessary for Hibernate operations.
     */
    public GenericDAOImpl(Class<T> classToUse){
        this.clas = classToUse;
    }

    /**
     * Creates session to the database and returns it.
     *
     * @return A new db connection session.
     */
    protected Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * Basic operation to find an item by its id (of its Primary Key type).
     *
     * @param id The value of items Primary Key in table.
     * @return In case of successful search return a found T object, else
     *           null is returned.
     */
    @Override
    public T findById(final PK id){
        Session session = getSession();
        T res = session.get(clas, id);
        session.close();
        return res;
    }

    /**
     * @return All items in the desired table or an empty list.
     */
    @Override
    public List<T> findAll(){
        Session session = getSession();
        List<T> res =  (List<T>) session.createQuery("from " + clas.getName()).list();
        session.close();
        return res;
    }

    /**
     * @param t An object to be saved.
     * @return In case of successful operation a value of Primary Key of object is returned.
     */
    @Override
    public PK save(final T t){
        Session session = getSession();
        session.beginTransaction();
        PK id =(PK) session.save(t);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    /**
     * @param t An object to be modified (by PK value). If no such object found --
     *          throws TransientObjectException or StaleStateException.
     * @see org.hibernate.TransientObjectException
     * @see org.hibernate.StaleStateException
     */
    @Override
    public void update(final T t){
        Session session = getSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * @param t An object to be deleted (by PK value). If no such object found --
     *          throws EntityNotFoundException.
     *
     * @see javax.persistence.EntityNotFoundException
     */
    @Override
    public void delete(final T t){
        Session session = getSession();
        session.beginTransaction();
        session.delete(t);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * @param id A value of PK of an object to
     *           be deleted (by PK value). If no such object found --
     *           throws EntityNotFoundException.
     *
     * @see javax.persistence.EntityNotFoundException
     */
    @Override
    public void deleteById(PK id){
        T entity = findById(id);
        Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }
}
