package com.dubovyk.bookmanager.Controllers;

import com.dubovyk.bookmanager.Controllers.Command.CommandProcessor;
import com.dubovyk.bookmanager.Controllers.Command.CommandProcessorImp;

/**
 * This class is a main entry point of the command processing.
 *
 * It is used as a manager of the responsibility chain
 * built of CommandProcessors.
 *
 * @see CommandProcessorImp
 * @see CommandProcessor
 *
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
public class ChatController {
    private CommandProcessor firstChain;

    public ChatController() {
        this(new CommandProcessorImp());
    }

    public ChatController(CommandProcessor firstChain){
        this.firstChain = firstChain;
    }

    public void run(){
        String newCommand;
        while (!(newCommand = System.console().readLine()).equals("exit")){
            firstChain.process(newCommand);
        }
    }

    public void registerProcessor(CommandProcessor processor){
        firstChain.registerNext(processor);
    }
}