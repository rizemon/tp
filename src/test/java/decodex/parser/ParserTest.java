package decodex.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void getCommandType_stringWithNoSeparator_expectCommandTypeString() {
        String testInput = "test";
        String expectedOutput = "test";
        Parser parser = new Parser();

        String commandType = parser.getCommandType(testInput);
        assertEquals(expectedOutput, commandType);
    }

    @Test
    void getCommandType_stringWithOneSpaceSeparator_expectCommandTypeString() {
        String testInput = "test data";
        String expectedOutput = "test";
        Parser parser = new Parser();

        String commandType = parser.getCommandType(testInput);
        assertEquals(expectedOutput, commandType);
    }

    @Test
    void getUserArgument_userInputWithOneSpaceSeparator_expectArgumentString() {
        String testInput = "test data";
        String expectedOutput = "data";
        Parser parser = new Parser();

        String argumentString = parser.getUserArgument(testInput);
        assertEquals(expectedOutput, argumentString);
    }
}