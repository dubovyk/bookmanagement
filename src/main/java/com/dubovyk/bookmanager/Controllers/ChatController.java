package com.dubovyk.bookmanager.Controllers;

import com.dubovyk.bookmanager.Controllers.Command.CommandProcessor;
import com.dubovyk.bookmanager.Controllers.Command.CommandProcessorImp;

/**
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
        System.out.println("REGISTERED");
    }
}