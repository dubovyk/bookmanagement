package com.dubovyk.bookmanager.Services.AuthorService;

import com.dubovyk.bookmanager.DAO.Author.AuthorDAO;
import com.dubovyk.bookmanager.DAO.Author.AuthorDAOImpl;
import com.dubovyk.bookmanager.Entities.Author;
import com.dubovyk.bookmanager.Entities.Book;
import com.dubovyk.bookmanager.Services.GenericServiceImpl;

/**
 * This class implements AuthorService interface and
 * used to provide logic to operate with Author table.
 *
 * It extends the GenericService with findOneByName() method,
 * which is used to find an Author by given name.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public class AuthorServiceImpl extends GenericServiceImpl<Author, Long> implements AuthorService{
    public AuthorServiceImpl(){
        super(new AuthorDAOImpl());
    }

    /**
     * @param name A name of the searched author
     * @return Author object with given name or null, if such object couldn`t be found in database.
     */
    @Override
    public Author findOneByName(String name){
        return ((AuthorDAO) dao).findByName(name);
    }

    @Override
    public Book getBookByNameAndAuthorName(final String book_name, final String author_name){
        Author author = ((AuthorDAO)dao).findByName(author_name);
        if (author == null){
            return null;
        }
        for(Book book : author.getBooks()){
            if(book.getName().equals(book_name)){
                System.out.println(book_name);
                return book;
            }
        }
        return null;
    }
}
