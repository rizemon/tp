package decodex.data.exception;

/**
 * UnknownModuleException is thrown when an unknown module name is provided.
 */
public class UnknownModuleException extends Exception {

    public static final String EXCEPTION_MESSAGE = "Unknown module name";
    private final String unknownModuleName;

    public UnknownModuleException(String unknownModuleName) {
        super(EXCEPTION_MESSAGE);
        this.unknownModuleName = unknownModuleName;
    }

    public String getUnknownModuleName() {
        return unknownModuleName;
    }
}
