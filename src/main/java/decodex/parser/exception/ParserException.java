package decodex.parser.exception;

public class ParserException extends Exception {

    public static final String MISSING_COMMAND_TYPE_MESSAGE = "Command cannot be empty!!";
    public static final String MISSING_COMMAND_ARGS_MESSAGE = "Your command arguments is empty."
            + " Please try again after tone...BEEP";

    public ParserException(String message) {
        super(message);
    }
}
