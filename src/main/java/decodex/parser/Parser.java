package decodex.parser;

import decodex.commands.Command;
import decodex.commands.ExitCommand;
import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

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
    public String getCommandType(String userInput) {
        String[] tokens = userInput.split(" ");
        String commandType = tokens[0];
        return commandType;
    }

    /**
     * Returns the Argument portion of the user input.
     *
     * @param userInput The input specified by the user.
     * @return The argument portion of the user input.
     */
    public String getUserArgument(String userInput) {
        try {
            String[] tokens = userInput.split(" ");
            String userArgument = tokens[1];
            return userArgument;
        } catch (ArrayIndexOutOfBoundsException err) {
            // Temporary error handling
            return "";
        }
    }

    /**
     * Parses the user input provided by user and returns its respective command.
     * Returns a null object if the command is invalid.
     *
     * @param userInput The user input specified by the user.
     * @param dataManager The DataManager object
     * @param moduleManager The ModuleManager object
     * @param ui The Ui object
     * @return The respective Command object.
     */
    public Command parseCommand(String userInput, DataManager dataManager, ModuleManager moduleManager, Ui ui) {
        Command command;
        String commandType = getCommandType(userInput);

        switch (commandType) {
        case ExitCommand.COMMAND_WORD:
            command = new ExitCommand(dataManager, moduleManager, ui);
            break;
        default:
            return null;
        }

        return command;
    }
}
