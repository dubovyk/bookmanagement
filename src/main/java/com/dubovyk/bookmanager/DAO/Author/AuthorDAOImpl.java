package com.dubovyk.bookmanager.DAO.Author;

import com.dubovyk.bookmanager.DAO.GenericDAOImpl;
import com.dubovyk.bookmanager.Entities.Author;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
//TODO add a method to find a list of authors, which fall under given pattern
public class AuthorDAOImpl extends GenericDAOImpl<Author, Long> implements AuthorDAO{
    public AuthorDAOImpl(){
        super(Author.class);
    }

    /**
     * @param name Authors name to be found.
     * @return If there`s such author in db, it`s returned, else null is returned.
     */
    public Author findByName(String name){
        Session session = this.getSession();
        session.beginTransaction();
        List l = session.createQuery(String.format("from Author where name is \'%s\'", name)).list();
        Author author;
        if (l.size() == 0){
            author = null;
        } else {
            author = (Author) l.get(0);
        }
        session.close();
        return author;

    }
}
