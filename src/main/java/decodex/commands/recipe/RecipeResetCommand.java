package decodex.commands.recipe;

import decodex.commands.Command;
import decodex.data.DataManager;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;
import java.io.IOException;

public class RecipeResetCommand extends Command {

    public static final String COMMAND_WORD = "reset";
    public static final String COMMAND_HELP_MESSAGE = "Clear all modules from recipe\n"
            + "Syntax: recipe reset";

    public RecipeResetCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager)
            throws RecipeManagerException, IOException {
        Recipe editingRecipe = recipeManager.getEditingRecipe();
        recipeManager.resetEditedRecipe();
        ui.printRecipeReset(editingRecipe.getName());
    }
}
