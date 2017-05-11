package com.dubovyk.bookmanager.Controllers.Command;

import com.dubovyk.bookmanager.Entities.Book;

import java.util.List;

/**
 * This class is used to remove a selected book.
 *
 * Message example: "remove book_name"
 *
 * @see CommandProcessor
 * @see CommandProcessorImp
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public class RemoveCommandProcessor extends CommandProcessorImp{

    public RemoveCommandProcessor(){
        this.pattern = "remove(.)*";
        this.badFormat = "You`ve entered a command in with wrong formatting. It should look like 'remove book_name'";
    }

    @Override
    public void handleMessage(String inputCommand){
        List<String> parts = splitArgs(inputCommand);
        if(parts.size() == 2){
            List<Book> books = bookService.findAllByName(parts.get(1).trim());
            if (books.size() == 0){
                System.out.println("Sorry, such book isn`t registered in our system");
                return;
            } else if (books.size() == 1){  // got a single book, can delete
                bookService.delete(books.get(0));
                System.out.println("Book was successfully deleted.");
            } else if (books.size() > 1){ // have a list, need to specify
                System.out.println("We have several books with the same name. Please, choose which one you want to remove.");
                int bookNum = selectBook(books);
                if (bookNum == -1){
                    System.out.println("Sorry, something went wrong. Operation cancelled.");
                    return;
                }
                bookService.delete(books.get(bookNum));
                System.out.println("Book was removed");
            }
        } else {
            System.out.println(badFormat);
        }
    }
}
