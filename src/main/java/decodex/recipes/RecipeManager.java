package decodex.recipes;

import java.util.HashMap;
import java.util.logging.Logger;

import decodex.Decodex;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.Module;
import decodex.ui.messages.ErrorMessages;

/**
 * RecipeManager manages a list of all recipes that were created/loaded.
 */
public class RecipeManager {

    private Logger logger = Decodex.logger;

    /**
     * Stores mapping between recipe name and recipe.
     */
    private HashMap<String, Recipe> recipeList;

    /**
     * Name of the recipe that is selected for editing/modification.
     */
    private String editingRecipeName;

    /**
     * Creates a new RecipeManager with no recipes.
     */
    public RecipeManager() {
        recipeList = new HashMap<>();
        editingRecipeName = null;
    }

    /**
     * Adds a recipe to recipe manager to be managed.
     *
     * @param recipe The recipe to be added to the recipe manager.
     * @throws RecipeManagerException If the given recipe name already exists in recipe manager.
     */
    public void addRecipe(Recipe recipe) throws RecipeManagerException {
        if (recipeList.containsKey(recipe.getName())) {
            throw new RecipeManagerException(ErrorMessages.DUPLICATE_RECIPE_NAME_MESSAGE);
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
        recipeList.remove(name);
        if (editingRecipeName != null && editingRecipeName.equals(name)) {
            editingRecipeName = null;
        }
        logger.fine(String.format("[RecipeManager] Removed recipe %s", recipeToRemove.getName()));
        return recipeToRemove;
    }

    /**
     * Gets a Recipe object from the recipe manager by name.
     *
     * @param name The name of the recipe to get.
     * @return The recipe that was found.
     * @throws RecipeManagerException If the recipe could not be found in the recipe manager.
     */
    public Recipe getRecipe(String name) throws RecipeManagerException {
        if (!recipeList.containsKey(name)) {
            throw new RecipeManagerException(ErrorMessages.RECIPE_NOT_FOUND_MESSAGE);
        }
        return recipeList.get(name);
    }

    /**
     * Gets the Recipe object that is currently being edited.
     *
     * @return The current recipe being edited.
     * @throws RecipeManagerException If no recipe is selected as currently being edited.
     */
    public Recipe getEditingRecipe() throws RecipeManagerException {
        if (editingRecipeName == null) {
            throw new RecipeManagerException(ErrorMessages.EDITING_RECIPE_NOT_FOUND_MESSAGE);
        }
        return getRecipe(editingRecipeName);
    }

    /**
     * Sets the recipe of the given name to be the recipe currently being edited.
     * @param name The name of the recipe to select.
     * @throws RecipeManagerException If the recipe could not be found in the recipe manager.
     */
    public void selectRecipeForEditing(String name) throws RecipeManagerException {
        if (!recipeList.containsKey(name)) {
            throw new RecipeManagerException(ErrorMessages.RECIPE_NOT_FOUND_MESSAGE);
        }
        editingRecipeName = name;
    }

    /**
     * Appends a new module to the end of the recipe currently being edited.
     *
     * @param module The module to be added to the recipe.
     * @throws RecipeManagerException If no recipe is selected as currently being edited.
     */
    public void pushModuleIntoEditedRecipe(Module module) throws RecipeManagerException {
        Recipe editingRecipe = getEditingRecipe();
        editingRecipe.push(module);
    }

    /**
     * Removes the latest module from the recipe currently being edited.
     *
     * @return The module that was popped from the recipe.
     * @throws RecipeManagerException If no recipe is selected as currently being edited.
     */
    public Module popModuleFromEditedRecipe() throws RecipeManagerException, RecipeException {
        Recipe editingRecipe = getEditingRecipe();
        return editingRecipe.pop();
    }

    /**
     * Completely removes all the modules from the recipe currently being edited.
     *
     * @throws RecipeManagerException If no recipe is selected as currently being edited.
     */
    public void resetEditedRecipe() throws RecipeManagerException {
        Recipe editingRecipe = getEditingRecipe();
        editingRecipe.reset();
    }
}
