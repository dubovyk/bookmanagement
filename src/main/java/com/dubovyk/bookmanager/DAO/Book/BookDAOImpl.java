package com.dubovyk.bookmanager.DAO.Book;

import com.dubovyk.bookmanager.DAO.GenericDAOImpl;
import com.dubovyk.bookmanager.Entities.Book;
import com.dubovyk.bookmanager.Persistance.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * This is an implementation of the Book
 * data access object. It extends a generic
 * one and only adds a getAllBooksByName method.
 *
 * @see com.dubovyk.bookmanager.DAO.GenericDAO
 * @see GenericDAOImpl
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public class BookDAOImpl extends GenericDAOImpl<Book, Long> implements BookDAO {

    public BookDAOImpl(){
        super(Book.class);
    }


    /**
     * @param name A string name of the book to be found
     * @return If found non 0 results returns a list with them, else empty List of Book items is returned.
     */
    @Override
    public List<Book> getAllBooksByName(final String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Book> foundBooks = (List<Book>) session.createQuery(String.format("from Book where name is %s", name)).list();
        session.close();
        return foundBooks;
    }
}
