package com.dubovyk.bookmanager.Services.BookService;

import com.dubovyk.bookmanager.DAO.Book.BookDAO;
import com.dubovyk.bookmanager.DAO.Book.BookDAOImpl;
import com.dubovyk.bookmanager.Entities.Author;
import com.dubovyk.bookmanager.Entities.Book;
import com.dubovyk.bookmanager.Services.AuthorService.AuthorService;
import com.dubovyk.bookmanager.Services.AuthorService.AuthorServiceImpl;
import com.dubovyk.bookmanager.Services.GenericServiceImpl;

import java.util.Comparator;
import java.util.List;

/**
 * This class implements BookService interface which
 * is an extended with findAllByName() GenericService.
 *
 * @see BookService
 * @see com.dubovyk.bookmanager.Services.GenericService
 * @see GenericServiceImpl
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public class BookServiceImpl extends GenericServiceImpl<Book, Long> implements BookService {
    private AuthorService authorService;

    /**
     * During initialization create a DAO and AuthorService (which
     * is used during save() operation.
     */
    public BookServiceImpl(){
        super(new BookDAOImpl());
        this.authorService = new AuthorServiceImpl();
    }

    /**
     * This method saves a Book object into a database. Before that
     * it checks if an Author object which is set as books author
     * already exists in database. If so, it assigns existing object
     * as Books author to avoid conflicts.
     *
     * @param book A Book object to be save into the database.
     */
    @Override
    public void save(Book book){
        Author author = authorService.findOneByName(book.getAuthor().getName());
        if (author == null){
            authorService.save(book.getAuthor());
        } else {
            book.setAuthor(author);
        }
        dao.save(book);
    }

    /**
     * @param book
     */
    @Override
    public void update(Book book){
        Author author = authorService.findOneByName(book.getAuthor().getName());
        if (author == null){
            authorService.save(book.getAuthor());
        } else {
            book.setAuthor(author);
        }
        dao.update(book);
    }

    /**
     * @param name A name of desired books.
     * @return A list of books with given name or an empty list.
     */
    @Override
    public List<Book> findAllByName(String name){
        List<Book> books = ((BookDAO) dao).getAllBooksByName(name);
        books.sort(Comparator.comparing(o -> o.getName().toLowerCase()));
        return books;
    }

    @Override
    public List<Book> findAll(){
        List<Book> books = dao.findAll();
        books.sort(Comparator.comparing(o -> o.getName().toLowerCase()));
        return books;
    }
}
