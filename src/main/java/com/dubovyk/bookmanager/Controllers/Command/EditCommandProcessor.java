package com.dubovyk.bookmanager.Controllers.Command;

import com.dubovyk.bookmanager.Entities.Author;
import com.dubovyk.bookmanager.Entities.Book;

import java.util.List;

/**
 * Message example: "edit book_name"
 *
 * @see CommandProcessor
 * @see CommandProcessorImp
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.4
 */
public class EditCommandProcessor extends CommandProcessorImp {

    public EditCommandProcessor(){
        this.pattern = "edit(.)*";
    }

    @Override
    public void handleMessage(String inputCommand){
        List<String> parts = splitArgs(inputCommand);
        if (parts.size() == 1){
            System.out.println(badFormat);
        }
        if(parts.size() == 2){
            List<Book> books = bookService.findAllByName(parts.get(1).trim());
            if (books.size() == 0){
                System.out.println("Sorry, such book isn`t registered in our system");
                return;
            } else if (books.size() == 1){
                updateBook(books.get(0));
            } else if (books.size() > 1){
                System.out.println("We have several books with the same name. Please, choose which one you want to remove.");
                int bookNum = selectBook(books);
                if (bookNum == -1){
                    System.out.println("Sorry, something went wrong. Operation cancelled.");
                    return;
                }
                updateBook(books.get(bookNum));
            }
        }
    }

    private void updateBook(Book book){
        System.out.println("Do you want to change books [a]uthor or [n]ame?");
        String choice = System.console().readLine();
        if (choice.toLowerCase().trim().equals("a")){
            System.out.println("Please enter a new books author press Enter");
            String newAuthorName = System.console().readLine().trim();
            Author author = new Author(newAuthorName);
            book.setAuthor(author);
            bookService.update(book);
            System.out.println("Books author was updated successfully");
        } else if (choice.toLowerCase().trim().equals("n")){
            System.out.println("Please enter a new books name and press Enter");
            String newName = System.console().readLine().trim();
            book.setName(newName);
            bookService.update(book);
            System.out.println("Books name was updated successfully");
        } else {
            System.out.println("Sorry, you entered something wrong. Operation cancelled.");
        }
    }
}
