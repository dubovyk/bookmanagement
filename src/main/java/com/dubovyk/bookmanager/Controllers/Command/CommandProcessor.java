package com.dubovyk.bookmanager.Controllers.Command;

/**
 * This interface defines a user input processors,
 * which will be organized into pattern chain of responsibility.
 *
 * That allows to make an easily scalable system for semi-stateless
 * command flow.
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public interface CommandProcessor {
    void registerNext(CommandProcessor next);
    void process(String userCommand);
    boolean matches(String userCommand);
}
