package decodex.commands;

import decodex.data.DataManager;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.Module;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

public class RecipePopCommand extends Command {

    public static final String COMMAND_WORD = "pop";

    public RecipePopCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager)
            throws RecipeException, RecipeManagerException {
        Module module = recipeManager.popModuleFromEditedRecipe();
        Recipe editingRecipe = recipeManager.getEditingRecipe();
        ui.printModuleRemovedFromRecipe(module.getName(), editingRecipe.getName());
    }
}
