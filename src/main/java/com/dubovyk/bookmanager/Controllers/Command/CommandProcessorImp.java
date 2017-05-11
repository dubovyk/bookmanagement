package com.dubovyk.bookmanager.Controllers.Command;

import com.dubovyk.bookmanager.Entities.Author;
import com.dubovyk.bookmanager.Entities.Book;
import com.dubovyk.bookmanager.Services.AuthorService.AuthorService;
import com.dubovyk.bookmanager.Services.AuthorService.AuthorServiceImpl;
import com.dubovyk.bookmanager.Services.BookService.BookService;
import com.dubovyk.bookmanager.Services.BookService.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a default implementation of the
 * CommandProcessor interface. It can only simply
 * forward a message to the next processor or return
 * an error, if there`s no next unit.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public class CommandProcessorImp implements CommandProcessor {
    protected BookService bookService;
    protected String pattern = "notarealcommmand";
    protected AuthorService authorService;
    protected CommandProcessor next;
    protected String errorText = "Sorry, your message can`t be processed. Command not recognized";
    protected String badFormat = "Sorry, you`ve entered a command in a wrong way.";
    protected String splittingPattern = "[^\\s]*\"(\\\\+\"|[^\"])*?\"|[^\\s]*'(\\\\+'|[^'])*?'|(\\\\\\s|[^\\s])+";

    public CommandProcessorImp(){
        this(new BookServiceImpl(), new AuthorServiceImpl());
    }

    public CommandProcessorImp(BookService bookService, AuthorService authorService){
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void registerNext(CommandProcessor next){
        if(this.next == null){
            this.next = next;
        } else {
            this.next.registerNext(next);
        }
    }

    /**
     * This method checks if can process message
     * and processes it or proxies forward.
     *
     * @see CommandProcessor
     *
     * @param userCommand A text of a command.
     */
    @Override
    public void process(String userCommand){
        if(matches(userCommand)){
            handleMessage(userCommand);
        } else if(next != null){
            next.process(userCommand);
        } else {
            System.out.println(errorText);
        }
    }

    /**
     * This method does all the processing and
     * must be overriden in child classes.
     * for details see CommandProcessor.
     * @see CommandProcessor
     *
     * @param userCommand
     */
    @Override
    public void handleMessage(String userCommand){}

    /**
     * This method uses regular expression in this.pattern
     * to check if given a valid message.
     *
     * @param inputCommand Text of user message
     * @return True -- if it can be processed, in other case -- false.
     */
    @Override
    public boolean matches(String inputCommand){
        Pattern addPattern = Pattern.compile(pattern);
        Matcher matcher = addPattern.matcher(inputCommand);
        return matcher.matches();
    }

    /**
     * This method takes a single string and
     * splits it according to the UNIX-like
     * shell method.
     *
     * @param input A line to split
     * @return A list of splited arguments.
     */
    protected List<String> splitArgs(String input){
        Pattern pattern = Pattern.compile(this.splittingPattern);
        Matcher matcher = pattern.matcher(input);
        List<String> parts = new ArrayList<>();
        while (matcher.find()){
            parts.add(matcher.group(0));
        }
        return parts;
    }

    /**
     * This method prints a list of books, asks user
     * to enter a number of the book and returns this
     * number. If failed -- returns -1.
     *
     * @param list A list of Book items
     * @return a number of the book in the list
     */
    protected int selectBook(List<Book> list){
        for (int bookNum = 0; bookNum < list.size(); bookNum++) {
            System.out.println(String.format("%d. %s", bookNum + 1, list.get(bookNum)));
        }
        String input = System.console().readLine();
        int bookNum;
        try {
            bookNum = Integer.valueOf(input.trim()) - 1;
        } catch (Exception ex){
            System.out.println("Sorry, you entered your choice in bad format. It should be a single number");
            return -1;
        }
        if (bookNum > list.size() - 1){
            System.out.println("Sorry, this book is not in given list");
            return -1;
        }
        return bookNum;
    }
}
