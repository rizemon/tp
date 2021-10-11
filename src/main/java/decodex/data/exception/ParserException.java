package decodex.data.exception;

public class ParserException extends Exception {

    public static final String INVALID_TOKENS_LENGTH = "[x] In what universe did you think that would break us."
            + " Please try again and use the valid commands, thanks.";
    public static final String MISSING_COMMAND_ARGS_MESSAGE = "[-] Your command arguments is empty";
    public static final String MISSING_COMMAND_TYPE_MESSAGE = "[-] Command cannot be empty";

    public ParserException(String message) {
        super(message);
    }
}
