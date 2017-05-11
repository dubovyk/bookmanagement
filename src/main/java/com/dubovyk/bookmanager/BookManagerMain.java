package com.dubovyk.bookmanager;

import com.dubovyk.bookmanager.Controllers.ChatController;
import com.dubovyk.bookmanager.Controllers.Command.AddCommandProcessor;
import com.dubovyk.bookmanager.Controllers.Command.EditCommandProcessor;
import com.dubovyk.bookmanager.Controllers.Command.ListCommandProcessor;
import com.dubovyk.bookmanager.Controllers.Command.RemoveCommandProcessor;
import com.dubovyk.bookmanager.Persistance.HibernateUtil;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;

/**
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public class BookManagerMain {
    public static void main(String ... args){
        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for ( Logger logger : loggers ) {
            logger.setLevel(Level.OFF);
        }
        ChatController chatController = new ChatController();
        chatController.registerProcessor(new AddCommandProcessor());
        chatController.registerProcessor(new ListCommandProcessor());
        chatController.registerProcessor(new RemoveCommandProcessor());
        chatController.registerProcessor(new EditCommandProcessor());
        chatController.run();
        HibernateUtil.getSessionFactory().close();
    }
}
