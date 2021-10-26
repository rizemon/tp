package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.data.exception.RecipeException;
import org.junit.jupiter.api.Test;

import decodex.commands.recipe.RecipePushCommand;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.RecipeManagerException;
import decodex.data.exception.ModuleManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

class RecipePushCommandTest {

    @Test
    public void run_oneModuleToEditingRecipe_recipeSizeIsOne()
            throws RecipeException, RecipeManagerException, CommandException, ModuleException, ModuleManagerException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        RecipeManager recipeManager = new RecipeManager();
        Ui ui = new Ui();

        String testRecipeName = "test";
        Recipe testRecipe = new Recipe(testRecipeName);
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        String moduleName = "base64encode";
        String[] parameters = {};

        RecipePushCommand testCommand = new RecipePushCommand(moduleName, parameters);
        testCommand.run(dataManager, moduleManager, ui, recipeManager);

        assertEquals(recipeManager.getEditingRecipe().getModuleList().size(), 1);
    }

    @Test
    public void run_emptyModuleName_expectException() throws RecipeException, RecipeManagerException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        RecipeManager recipeManager = new RecipeManager();
        Ui ui = new Ui();

        String testRecipeName = "test";
        Recipe testRecipe = new Recipe(testRecipeName);
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        String moduleName = "";
        String[] parameters = {};

        RecipePushCommand testCommand = new RecipePushCommand(moduleName, parameters);

        assertThrows(CommandException.class, () -> testCommand.run(dataManager, moduleManager, ui, recipeManager));
    }
}
