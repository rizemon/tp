package decodex.parser;

import java.util.Arrays;

import decodex.commands.Command;
import decodex.commands.ExitCommand;
import decodex.commands.InputCommand;
import decodex.commands.ListCommand;
import decodex.commands.ResetCommand;
import decodex.commands.SelectCommand;
import decodex.data.exception.CommandException;
import decodex.data.exception.ParserException;
import decodex.ui.messages.ErrorMessages;

/**
 * The Parser class handles the parsing and validation of the user input.
 */
public class Parser {

    /**
     * Specifies the index of the tokens where specific parameters can be found.
     */
    private static final int COMMAND_INDEX = 0;
    private static final int MODULE_NAME_INDEX_IN_TOKENS = 0;

    /**
     * Specifies the starting index where the arguments can be found.
     */
    private static final int STARTING_ARGUMENTS_INDEX = 1;

    /**
     * Specifies the starting index where the module parameters can be found.
     */
    private static final int MODULE_PARAMETERS_INDEX = 2;

    /**
     * Specifies the token used to split the user input by.
     */
    private static final String SPLIT_REGEX = "\\s+";

    /**
     * Specifies the valid length of the tokens and used to check validity of tokens.
     */
    private static final int VALID_TOKENS_LENGTH_FOR_COMMAND = 1;

    /**
     * Specifies the number of arguments for list command with no category and with a category.
     */
    private static final int LIST_ARGUMENT_LENGTH_NO_CATEGORY = 0;
    private static final int LIST_ARGUMENT_LENGTH_HAS_CATEGORY = 1;
    private static final int LIST_ARGUMENT_STARTING_INDEX = 0;

    /**
     * Returns the type of command that the user has specified.
     *
     * @param userInput The input specified by the user.
     * @return The type of the command.
     * @throws ParserException If the user input is empty or missing the command type.
     */
    public String getCommandType(String userInput) throws ParserException {
        String strippedUserInput = userInput.stripLeading();
        if (strippedUserInput.isEmpty()) {
            throw new ParserException(ErrorMessages.MISSING_COMMAND_TYPE);
        }

        String[] tokens = strippedUserInput.split(" ");

        boolean isInvalidTokensLength = tokens.length < VALID_TOKENS_LENGTH_FOR_COMMAND;

        if (isInvalidTokensLength) {
            throw new ParserException(ErrorMessages.MISSING_COMMAND_TYPE);
        }

        String commandType = tokens[COMMAND_INDEX];
        assert !commandType.isEmpty() : "Command Type should not be empty";
        return commandType;
    }

    /**
     * Returns the name of the module that the user has specified.
     *
     * @param userInput The input specified by the user.
     * @return The name of the module.
     * @throws ParserException If the user input is missing the module name.
     */
    public String getModuleName(String userInput) throws ParserException {
        String strippedUserInput = userInput.stripLeading();
        String[] tokens = strippedUserInput.split(" ", -1);

        String[] argumentTokens = Arrays.stream(Arrays.copyOfRange(tokens, STARTING_ARGUMENTS_INDEX, tokens.length))
                .filter(value -> !value.isBlank())
                .toArray(size -> new String[size]);
        if (argumentTokens.length == 0) {
            throw new ParserException(ErrorMessages.MISSING_MODULE_NAME);
        }
        return argumentTokens[MODULE_NAME_INDEX_IN_TOKENS];
    }

    /**
     * Returns the module parameters that the user has specified.
     *
     * @param userInput The input specified by the user.
     * @return A String array of module parameters.
     */
    public String[] getModuleParameters(String userInput) {
        String strippedUserInput = userInput.stripLeading();
        String[] tokens = strippedUserInput.split(SPLIT_REGEX);
        String[] parameters = Arrays.copyOfRange(tokens, MODULE_PARAMETERS_INDEX, tokens.length);
        return parameters;
    }

    /**
     * Returns the InputCommand's argument string that the user has specified.
     *
     * @param userInput The input specified by the user.
     * @return The argument string.
     * @throws ParserException If the argument string is empty.
     */
    public String getInputString(String userInput) throws ParserException {
        String strippedUserInput = userInput.stripLeading();
        String[] tokens = strippedUserInput.split(" ", -1);

        String argumentString = String.join(" ",
                Arrays.copyOfRange(tokens, STARTING_ARGUMENTS_INDEX, tokens.length));
        if (argumentString.isEmpty()) {
            throw new ParserException(ErrorMessages.INPUT_EMPTY);
        }
        return argumentString;
    }

    /**
     * Parses the user input specified by the user and returns its corresponding Command.
     *
     * @param userInput The user input specified by the user.
     * @return The corresponding Command object.
     * @throws ParserException  If the command type is unknown or invalid.
     * @throws CommandException If the command has invalid number of arguments.
     */
    public Command parseCommand(String userInput) throws ParserException, CommandException {
        Command command;
        String commandType = getCommandType(userInput);

        switch (commandType) {
        case ExitCommand.COMMAND_WORD:
            command = craftExitCommand();
            break;
        case InputCommand.COMMAND_WORD:
            command = craftInputCommand(userInput);
            break;
        case ListCommand.COMMAND_WORD:
            command = craftListCommand(userInput);
            break;
        case ResetCommand.COMMAND_WORD:
            command = craftResetCommand();
            break;
        case SelectCommand.COMMAND_WORD:
            command = craftSelectCommand(userInput);
            break;
        default:
            throw new ParserException(ErrorMessages.UNKNOWN_COMMAND);
        }
        return command;
    }

    /**
     * Prepares and returns the ExitCommand.
     *
     * @return The ExitCommand object.
     */
    private ExitCommand craftExitCommand() {
        return new ExitCommand();
    }

    /**
     * Prepares and returns the InputCommand using the specified user input.
     *
     * @param userInput The user input specified by the user.
     * @return The InputCommand object.
     * @throws ParserException If the argument string is empty.
     */
    private InputCommand craftInputCommand(String userInput) throws ParserException {
        String inputData = getInputString(userInput);
        return new InputCommand(inputData);
    }

    /**
     * Returns the list category for the list command.
     *
     * @param userInput The user input specified by the user.
     * @return The list category String.
     * @throws CommandException If the command has more than 1 argument.
     */
    private String getListCategory(String userInput) throws CommandException {
        String[] tokens = userInput.split(SPLIT_REGEX);
        String[] arguments = Arrays.copyOfRange(tokens, STARTING_ARGUMENTS_INDEX, tokens.length);

        if (arguments.length == LIST_ARGUMENT_LENGTH_NO_CATEGORY) {
            return null;
        } else if (arguments.length == LIST_ARGUMENT_LENGTH_HAS_CATEGORY) {
            return arguments[LIST_ARGUMENT_STARTING_INDEX];
        } else {
            throw new CommandException(ErrorMessages.TOO_MANY_COMMAND_ARGUMENTS);
        }
    }

    /**
     * Prepares and returns the ListCommand.
     *
     * @return The ListCommand object.
     * @throws CommandException If the number of command argument is invalid.
     */
    private ListCommand craftListCommand(String userInput) throws CommandException {
        String listCategory = getListCategory(userInput);
        if (listCategory != null) {
            listCategory = listCategory.strip();
        }
        return new ListCommand(listCategory);
    }

    /**
     * Prepares and returns the ResetCommand.
     *
     * @return The ResetCommand object.
     */
    private ResetCommand craftResetCommand() {
        return new ResetCommand();
    }

    /**
     * Prepares and returns the SelectCommand using the specified user input.
     *
     * @param userInput The user input specified by the user.
     * @return The SelectCommand object.
     * @throws ParserException If the user input is missing the module name.
     */
    private SelectCommand craftSelectCommand(String userInput) throws ParserException {
        String moduleName = getModuleName(userInput);
        String[] parameters = getModuleParameters(userInput);
        return new SelectCommand(moduleName, parameters);
    }

}
