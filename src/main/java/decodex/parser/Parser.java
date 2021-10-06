package decodex.parser;

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
}
