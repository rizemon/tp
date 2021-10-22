package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;
import org.junit.jupiter.api.Test;

class RecipeNewCommandTest {

    @Test
    void run_createNewRecipe_newRecipeCreated()
            throws UnknownModuleException, CommandException, RecipeException,
            ModuleException, RecipeManagerException, DataManagerException {
        String testRecipeName = "BaconPancakes";
        Command newRecipeCommand = new RecipeNewCommand(testRecipeName);

        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        RecipeManager recipeManager = new RecipeManager();

        newRecipeCommand.run(dataManager, moduleManager, ui, recipeManager);

        assertNotNull(recipeManager.getRecipe("BaconPancakes"));
    }
}
