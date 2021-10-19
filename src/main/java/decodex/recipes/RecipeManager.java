package decodex.recipes;

import decodex.Decodex;
import decodex.data.exception.RecipeManagerException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * RecipeManager manages a list of all recipes that were created/loaded.
 */
public class RecipeManager {

    private Logger logger = Decodex.logger;

    HashMap<String, Recipe> recipeList;

    /**
     * Creates a new RecipeManager with no recipes.
     */
    public RecipeManager() {
        recipeList = new HashMap<>();
    }

    /**
     * Adds a recipe to recipe manager to be managed.
     *
     * @param recipe The recipe to be added to the recipe manager.
     * @throws RecipeManagerException If the given recipe name already exists in recipe manager.
     */
    public void addRecipe(Recipe recipe) throws RecipeManagerException {
        if (recipeList.containsKey(recipe.getName())) {
            throw new RecipeManagerException(RecipeManagerException.DUPLICATE_RECIPE_NAME_MESSAGE);
        }
        recipeList.put(recipe.getName(), recipe);
        logger.fine(String.format("[RecipeManager] Added recipe %s", recipe.getName()));
    }

    /**
     * Removes a recipe from the recipe manager.
     *
     * @param name The name of the recipe to be removed.
     * @return The recipe that was removed.
     * @throws RecipeManagerException If the recipe could not be found in the recipe manager.
     */
    public Recipe removeRecipe(String name) throws RecipeManagerException {
        Recipe recipeToRemove = getRecipe(name);
        recipeList.remove(recipeToRemove);
        logger.fine(String.format("[RecipeManager] Removed recipe %s", recipeToRemove.getName()));
        return recipeToRemove;
    }

    /**
     * Get a Recipe object from the recipe manager by name.
     *
     * @param name The name of the recipe to get.
     * @return The recipe that was found.
     * @throws RecipeManagerException If the recipe could not be found in the recipe manager.
     */
    public Recipe getRecipe(String name) throws RecipeManagerException {
        if (!recipeList.containsKey(name)) {
            throw new RecipeManagerException(RecipeManagerException.RECIPE_NOT_FOUND_MESSAGE);
        }
        return recipeList.get(name);
    }

}
