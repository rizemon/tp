package decodex.data.exception;

public class FileException extends Exception {

    public static final String DEFAULT_INPUT_FILE_DOES_NOT_EXIST_MESSAGE =
            "[x] The default input file does not exists";
    public static final String DEFAULT_INPUT_FILE_CREATION_ERROR_MESSAGE =
            "[x] The default input file failed to be created";
    public static final String FILE_CREATION_ERROR_MESSAGE =
            "[x] Something went wrong while reading from default input file";

    public static final String FILE_WRITE_ERROR_MESSAGE = "[x] Something went wrong while writing to output file";

    public FileException(String message) {
        super(message);
    }
}
