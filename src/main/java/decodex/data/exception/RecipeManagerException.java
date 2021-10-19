package decodex.data.exception;

/**
 * This exception is raised when an error during recipe manager execution occurs.
 */
public class RecipeManagerException extends Exception {

    public static final String DUPLICATE_RECIPE_NAME_MESSAGE = "[x] A recipe with the given name already exists.";
    public static final String RECIPE_NOT_FOUND_MESSAGE = "[x] The given recipe could not be found.";

    public RecipeManagerException(String message) {
        super(message);
    }
}
