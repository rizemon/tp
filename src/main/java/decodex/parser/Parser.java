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
     * Specifies the index of the tokens where command can be found.
     */
    private static final int COMMAND_INDEX = 0;

    /**
     * Specifies the starting index where the arguments can be found.
     */
    private static final int STARTING_ARGUMENTS_INDEX = 1;

    /**
     * Specifies the valid length of the tokens and used to check validity of tokens.
     */
    private static final int VALID_TOKENS_LENGTH = 1;

    /**
     * Returns the type of command that user has entered.
     *
     * @param userInput The input specified by the user.
     * @return The type of command.
     */
    public String getCommandType(String userInput) throws ParserException {
        if (userInput.isEmpty()) {
            throw new ParserException(ParserException.MISSING_COMMAND_TYPE_MESSAGE);
        }

        String[] tokens = userInput.split(" ");

        boolean isInvalidTokensLength = tokens.length < VALID_TOKENS_LENGTH;

        if (isInvalidTokensLength) {
            throw new ParserException(ParserException.WEIRD_COMMAND_TYPE_MESSAGE);
        }
        return tokens[COMMAND_INDEX];
    }

    /**
     * Returns the Argument portion of the user input.
     *
     * @param userInput The input specified by the user.
     * @return The argument portion of the user input.
     */
    public String[] getUserArguments(String userInput) throws ParserException {
        String[] tokens = userInput.split(" ");

        if (tokens.length <= VALID_TOKENS_LENGTH) {
            throw new ParserException(ParserException.MISSING_COMMAND_ARGS_MESSAGE);
        }
        return Arrays.copyOfRange(tokens, STARTING_ARGUMENTS_INDEX, tokens.length);
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
    public Command parseCommand(String userInput, DataManager dataManager, ModuleManager moduleManager, Ui ui)
            throws ParserException {
        Command command;

        String commandType = getCommandType(userInput);

        switch (commandType) {
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand(dataManager, moduleManager, ui);
            break;
        default:
            throw new ParserException(userInput + " !To be replaced with invalid command message!");
        }
        return command;
    }
}
