package decodex.ui.messages;

public class ErrorMessages {

    public static final String MISSING_ARGUMENT = "Missing argument";
    public static final String MISSING_COMMAND_TYPE = "Your command cannot be blank";
    public static final String MISSING_MODULE_NAME = "Missing module name";
    public static final String INPUT_EMPTY = "Your input is empty";
    public static final String UNKNOWN_COMMAND = "Unknown command, please enter a valid command";
    public static final String NO_DATA_FOUND = "No data found";
    public static final String BASE64_DECODING_FAILED_MESSAGE = "Your data does not seem to be in Base64 format";
    public static final String HEX_DECODING_FAILED_MESSAGE = "Invalid hexadecimal string";
    public static final String DEFAULT_INPUT_FILE_DOES_NOT_EXIST_MESSAGE =
            "The default input file does not exists";
    public static final String DEFAULT_INPUT_FILE_CREATION_ERROR_MESSAGE =
            "The default input file failed to be created";
    public static final String FILE_CREATION_ERROR_MESSAGE =
            "Something went wrong while reading from default input file";
    public static final String FILE_WRITE_ERROR_MESSAGE = "Something went wrong while writing to output file";
}
