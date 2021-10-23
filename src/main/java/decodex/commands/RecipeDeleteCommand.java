package decodex.commands;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;
import decodex.ui.messages.ErrorMessages;

public class RecipeDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    private final String recipeName;

    public RecipeDeleteCommand(String recipeName) {
        super();
        this.recipeName = recipeName;
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager)
            throws CommandException, ModuleException, RecipeManagerException {
        if (recipeName.isBlank()) {
            throw new CommandException(ErrorMessages.MISSING_RECIPE_NAME);
        }

        recipeManager.removeRecipe(recipeName);
        ui.printRecipeDeleted(recipeName);
    }
}
