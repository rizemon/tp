package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.commands.recipe.RecipeDeleteCommand;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.ModuleManagerException;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;
import org.junit.jupiter.api.Test;

class RecipeDeleteCommandTest {

    @Test
    void run_deleteValidRecipe_recipeDeleted() throws CommandException, RecipeException, ModuleException,
            RecipeManagerException, DataManagerException, ModuleManagerException {
        String testRecipeName = "BaconPancakes";

        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        RecipeManager recipeManager = new RecipeManager();

        recipeManager.addRecipe(new Recipe(testRecipeName));

        Command recipeDeleteCommand = new RecipeDeleteCommand(testRecipeName);
        recipeDeleteCommand.run(dataManager, moduleManager, ui, recipeManager);

        assertThrows(RecipeManagerException.class, () -> recipeManager.getRecipe(testRecipeName));
    }

    @Test
    void run_deleteInvalidRecipe_expectException() throws RecipeManagerException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        RecipeManager recipeManager = new RecipeManager();

        recipeManager.addRecipe(new Recipe("BaconPancakes"));

        String testRecipeName = "IDoNotExist";
        Command recipeDeleteCommand = new RecipeDeleteCommand(testRecipeName);

        assertThrows(RecipeManagerException.class,
            () -> recipeDeleteCommand.run(dataManager, moduleManager, ui, recipeManager));
    }
}
