package decodex.data.exception;

/**
 * This exception is raised when an error during recipe execution occurs.
 */
public class RecipeException extends Exception {

    public static final String EMPTY_RECIPE_MESSAGE = "[x] There is nothing to be removed.";

    public RecipeException(String message) {
        super(message);
    }
}
