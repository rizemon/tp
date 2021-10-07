package decodex.parser.exception;

public class ParserException extends Exception {

    public static final String MISSING_COMMAND_ARGS_MESSAGE = "Your command arguments is empty."
            + " Please try again after tone...BEEP";
    public static final String MISSING_COMMAND_TYPE_MESSAGE = "Command cannot be empty!!";

    /**
     * This error message is dedicated to users intending to find bugs in our program.
     */
    public static final String WEIRD_COMMAND_TYPE_MESSAGE = "In what universe did you think that would break us."
            + " Please try again and use the valid commands, thanks.";

    public ParserException(String message) {
        super(message);
    }
}
