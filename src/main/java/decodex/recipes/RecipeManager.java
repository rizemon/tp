package decodex.recipes;

import decodex.Decodex;
import decodex.data.exception.RecipeManagerException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * RecipeManager manages a list of all recipes that were created/loaded.
 */
public class RecipeManager {

    private Logger logger = Decodex.logger;

    ArrayList<Recipe> recipeList;

    /**
     * Creates a new RecipeManager with no recipes.
     */
    public RecipeManager() {
        this.recipeList = new ArrayList<>();
    }

    /**
     * Creates a new RecipeManager with a predefined list of recipes.
     *
     * @param recipeList list of recipes to be loaded.
     */
    public RecipeManager(ArrayList<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    /**
     * Adds a recipe to recipe manager to be managed.
     *
     * @param recipe Recipe to be added to the recipe manager.
     */
    public void addRecipe(Recipe recipe) {
        recipeList.add(recipe);
        logger.fine(String.format("[RecipeManager] Added recipe %s", recipe.getName()));
    }

    /**
     * Removes a recipe from the recipe manager.
     *
     * @param name Name of the recipe to be removed.
     * @return Recipe that was removed.
     * @throws RecipeManagerException Recipe could not be found in the recipe manager.
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
     * @param name Name of the recipe to get.
     * @return Recipe that was found.
     * @throws RecipeManagerException Recipe could not be found in the recipe manager.
     */
    public Recipe getRecipe(String name) throws RecipeManagerException {
        for (Recipe recipe : recipeList) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        throw new RecipeManagerException(RecipeManagerException.RECIPIE_NOT_FOUND_MESSAGE);
    }

}
