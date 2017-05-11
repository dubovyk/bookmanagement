package com.dubovyk.bookmanager.Controllers.Command;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Sergey Dubovyk aka knidarkness
 * @version 1.0
 */
@SuppressWarnings("Duplicates")
public class CommandProcessorImpTest {
    @Test
    public void matchesEditTrue() throws Exception {
        String pattern = "edit (.)+";
        CommandProcessorImp commandProcessorImp = new CommandProcessorImp();
        commandProcessorImp.setPattern(pattern);
        boolean actual = commandProcessorImp.matches("edit book");
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    public void matchesEditFalse() throws Exception {
        String pattern = "edit (.)+";
        CommandProcessorImp commandProcessorImp = new CommandProcessorImp();
        commandProcessorImp.setPattern(pattern);
        boolean actual = commandProcessorImp.matches("edit");
        boolean expected = false;
        assertEquals(actual, expected);
    }

    @Test
    public void splitArgs() throws Exception {
        String inputMessage = "add \"J.K. Rowling\" 'Harry Potter'";
        List<String> expected = Arrays.asList("add", "J.K. Rowling", "Harry Potter");
        List<String> actual = new CommandProcessorImp().splitArgs(inputMessage);
        assertEquals(actual, expected);
    }

}