package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.RecipeManagerException;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

class RecipePushCommandTest {

    @Test
    public void run_oneModuleToEditingRecipe_recipeSizeIsOne()
            throws RecipeManagerException, UnknownModuleException, CommandException, ModuleException {
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
    public void run_emptyModuleName_expectException()
            throws RecipeManagerException {
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
