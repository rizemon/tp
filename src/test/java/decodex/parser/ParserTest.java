package decodex.parser;

import decodex.commands.Command;
import decodex.commands.ExitCommand;
import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.data.exception.ParserException;
import decodex.ui.Ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    DataManager dataManager = new DataManager();
    ModuleManager moduleManager = new ModuleManager();
    Parser parser = new Parser();
    Ui ui = new Ui();

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

        String[] arguments = parser.getUserArguments(testInput);

        assertArrayEquals(expectedOutput, arguments);
    }

    @Test
    void parseCommand_userInputSpecifyingExit_expectExitCommand() throws ParserException {
        String userInput = "exit";

        Command command = parser.parseCommand(userInput, dataManager, moduleManager, ui);

        assertTrue(command instanceof ExitCommand);
    }
}
