package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.commands.recipe.RecipePopCommand;
import decodex.data.DataManager;
import decodex.data.exception.ModuleException;
import decodex.data.exception.ModuleManagerException;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.Module;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.storage.Storage;
import decodex.ui.Ui;
import java.io.IOException;
import org.junit.jupiter.api.Test;

// @@author rizemon
class RecipePopCommandTest {

    @Test
    public void run_oneModuleToEditingRecipe_recipeSizeIsZero()
            throws RecipeManagerException, ModuleManagerException, ModuleException, RecipeException, IOException {
        Storage storage = new Storage();
        RecipeManager recipeManager = new RecipeManager(storage);
        String testRecipeName = "test";
        Recipe testRecipe = new Recipe(testRecipeName);
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        ModuleManager moduleManager = new ModuleManager();
        String moduleName = "base64encode";
        String[] parameters = {};
        Module module = moduleManager.selectModule(moduleName, parameters);
        testRecipe.push(module);

        RecipePopCommand testCommand = new RecipePopCommand();
        DataManager dataManager = new DataManager();
        Ui ui = new Ui();
        testCommand.run(dataManager, moduleManager, ui, recipeManager);

        assertEquals(recipeManager.getEditingRecipe().getModuleList().size(), 0);
    }

    @Test
    public void run_emptyEditingRecipe_expectException() throws RecipeException, RecipeManagerException, IOException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Storage storage = new Storage();
        RecipeManager recipeManager = new RecipeManager(storage);
        Ui ui = new Ui();

        String testRecipeName = "test";
        Recipe testRecipe = new Recipe(testRecipeName);
        recipeManager.addRecipe(testRecipe);
        recipeManager.selectRecipeForEditing(testRecipeName);

        RecipePopCommand testCommand = new RecipePopCommand();

        assertThrows(RecipeException.class, () -> testCommand.run(dataManager, moduleManager, ui, recipeManager));
    }
}
