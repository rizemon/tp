package decodex.parser;

import decodex.commands.Command;
import decodex.commands.ExitCommand;
import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.parser.exception.ParserException;
import decodex.ui.Ui;

import java.util.Arrays;

/**
 * Parses and validates the user input.
 */
public class Parser {

    /**
     * Returns the type of command that user has entered.
     *
     * @param userInput The input specified by the user.
     * @return The type of command.
     */
    public String getCommandType(String userInput) throws ParserException {
        String[] tokens = userInput.split(" ");
        String commandType = tokens[0];
        if (commandType.isEmpty()) {
            throw new ParserException(ParserException.MISSING_COMMAND_TYPE_MESSAGE);
        }
        return tokens[0];
    }

    /**
     * Returns the Argument portion of the user input.
     *
     * @param userInput The input specified by the user.
     * @return The argument portion of the user input.
     */
    public String[] getUserArguments(String userInput) throws ParserException {
        try {
            String[] tokens = userInput.split(" ");
            return Arrays.copyOfRange(tokens, 1, tokens.length);
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new ParserException(ParserException.MISSING_COMMAND_ARGS_MESSAGE);
        }
    }

    /**
     * Parses the user input provided by user and returns its respective command.
     * Returns a null object if the command is invalid.
     *
     * @param userInput     The user input specified by the user.
     * @param dataManager   The DataManager object
     * @param moduleManager The ModuleManager object
     * @param ui            The Ui object
     * @return The respective Command object.
     */
    public Command parseCommand(String userInput, DataManager dataManager, ModuleManager moduleManager, Ui ui) throws ParserException {
        Command command;
        try {
            String commandType = getCommandType(userInput);

            switch (commandType) {
            case ExitCommand.COMMAND_WORD:
                command = new ExitCommand(dataManager, moduleManager, ui);
                break;
            default:
                throw new ParserException(userInput + " !To be replaced with invalid command message!");
            }
        } catch (ParserException err) {
            throw new ParserException(err.getMessage());
        }
        return command;
    }
}
