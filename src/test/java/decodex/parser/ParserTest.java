package decodex.parser;

import decodex.commands.Command;
import decodex.commands.ExitCommand;
import decodex.data.exception.ParserException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    Parser parser = new Parser();

    @Test
    void getCommandType_stringWithNoSeparator_expectCommandTypeString() throws ParserException {
        String testInput = "test";
        String expectedOutput = "test";

        String commandType = parser.getCommandType(testInput);

        assertEquals(expectedOutput, commandType);
    }

    @Test
    void getCommandType_stringWithOneSpaceSeparator_expectCommandTypeString() throws ParserException {
        String testInput = "test data";
        String expectedOutput = "test";

        String commandType = parser.getCommandType(testInput);

        assertEquals(expectedOutput, commandType);
    }

    @Test
    void getUserArguments_userInputWithOneSpaceSeparator_expectArrayOfOneArgument() throws ParserException {
        String testInput = "test data";
        String[] expectedOutput = new String[]{"data"};

        String[] arguments = parser.getUserArgumentsAsArray(testInput);

        assertArrayEquals(expectedOutput, arguments);
    }

    @Test
    void parseCommand_userInputSpecifyingExit_expectExitCommand() throws ParserException {
        String userInput = "exit";

        Command command = parser.parseCommand(userInput);

        assertTrue(command instanceof ExitCommand);
    }
}
