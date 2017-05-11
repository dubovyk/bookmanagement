package com.dubovyk.bookmanager.Controllers.Command;

import com.dubovyk.bookmanager.Entities.Author;
import com.dubovyk.bookmanager.Entities.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class should be used as part of
 * command processing chain.
 *
 * It takes a user command, which should be
 * formatted as following:
 * add AUTHOR BOOK_NAME,
 * where AUTHOR and BOOK_NAME can be
 * or a single word w/o spaces, or a ' or " delimited
 * groups of words.
 *
 * E.g. add J.Rowling Potter
 * or
 * add 'J.K. Rowling' 'Harry Potter'
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public class AddCommandProcessor extends CommandProcessorImp {

    public AddCommandProcessor(){
        this.pattern = "add(.)*";
        this.badFormat = "You`ve entered a command in a wrong way. It should look like 'add author_name book_name'";
    }

    @Override
    public void handleMessage(String inputCommand){
        List<String> parts = splitArgs(inputCommand);

        if (parts.size() != 3){
            //TODO: add good help message
            System.out.println(badFormat);
            return;
        }

        String author_name = parts.get(1).replaceAll("[\"']", "").trim();
        String book_name = parts.get(2).replaceAll("[\"']", "").trim();

        Author author = new Author(author_name);
        Book book = new Book(book_name, author);
        if(this.authorService.getBookByNameAndAuthorName(book_name, author_name) == null) {
            this.bookService.save(book);
            System.out.println(String.format("Book %s by %s was successfully added to the database", book_name, author_name));
        } else {
            System.out.println("Sorry, this author already has such book registered.");
        }
    }
}
