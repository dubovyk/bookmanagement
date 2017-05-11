package com.dubovyk.bookmanager.Controllers.Command;

/**
 * This interface defines a user input processors,
 * which will be organized into pattern chain of responsibility.
 *
 * That allows to make an easily scalable system for semi-stateless
 * command flow.
 *
 * @see CommandProcessorImp
 * @see com.dubovyk.bookmanager.Controllers.ChatController
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.3
 */
public interface CommandProcessor {
    /**
     * This method adds a handler which is called if
     * user matches(user message) is false.
     *
     * @param next A CommandProcessor object to be registered as a next handler.
     */
    void registerNext(CommandProcessor next);

    /**
     * This method should be called from outside the object
     * to process a message (if it fits pattern) or
     * to call a next handler.
     *
     * @param userCommand A text of a command.
     */
    void process(String userCommand);

    /**
     * This method should be called only if
     * user knows that message fits pattern
     * and can be processed with target
     * object.
     *
     * Also, this is a method inside of which
     * all processing is done.
     *
     * @param userCommand
     */
    void handleMessage(String userCommand);

    /**
     * This method checks if given message can
     * be processed with target object.
     *
     * @param userCommand Text of user message.
     * @return True -- if it can be processed, in other case -- false.
     */
    boolean matches(String userCommand);
}
