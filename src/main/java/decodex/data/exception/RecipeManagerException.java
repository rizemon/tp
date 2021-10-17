package decodex.data.exception;

/**
 * This exception is raised when an error during recipe manager execution occurs.
 */
public class RecipeManagerException extends Exception {

    public static final String EMPTY_RECIPELIST_MESSAGE = "[x] There is nothing to be removed.";
    public static final String RECIPIE_NOT_FOUND_MESSAGE = "[x] Given recipe could not be found.";

    public RecipeManagerException(String message) {
        super(message);
    }
}
