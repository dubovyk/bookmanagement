package com.dubovyk.bookmanager.Controllers.Command;

import com.dubovyk.bookmanager.Entities.Book;

import java.util.List;

/**
 * This class is used to get a list of all books
 * in the system or all books of a given author.
 *
 * Message example: "list" or "list author_name"
 *
 * @see CommandProcessor
 * @see CommandProcessorImp
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.3
 */
public class ListCommandProcessor extends CommandProcessorImp {

    public ListCommandProcessor(){
        this.pattern = "list(.)*";
    }

    @Override
    public void process(String inputCommand){
        List<String> com_parts = splitArgs(inputCommand);
        if (com_parts.size() == 1) {
            List<Book> allBooks = bookService.findAll();
            if (allBooks.size() > 0) {
                System.out.println("Now we have such books available:");
                for (int bookNum = 0; bookNum < allBooks.size(); bookNum++) {
                    System.out.println(String.format("%d. %s", bookNum + 1, allBooks.get(bookNum)));
                }
            } else {
                System.out.println("Sorry, but as for now we have no books. Just a great time to add some!");
            }
        } else if (com_parts.size() == 2){
            String author_name = com_parts.get(1).trim();
            List<Book> authorBooks = authorService.getAllBooksForAuthor(author_name);
            if (authorBooks.size() > 0) {
                System.out.println(String.format("Now we have such books available for author \"%s\":", author_name));
                for (int bookNum = 0; bookNum < authorBooks.size(); bookNum++) {
                    System.out.println(String.format("%d. %s", bookNum + 1, authorBooks.get(bookNum)));
                }
            } else {
                System.out.println("Sorry, but as for now we have no books for this author. Just a great time to add some!");
            }
        }
    }
}
