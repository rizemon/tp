package decodex.commands.recipe;

import decodex.commands.Command;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.RecipeManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;
import decodex.ui.messages.ErrorMessages;

public class RecipeSelectCommand extends Command {

    public static final String COMMAND_WORD = "select";

    private final String recipeName;

    public RecipeSelectCommand(String recipeName) {
        super();
        this.recipeName = recipeName;
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager)
            throws CommandException, RecipeManagerException {
        if (recipeName.isBlank()) {
            throw new CommandException(ErrorMessages.MISSING_RECIPE_NAME);
        }

        recipeManager.selectRecipeForEditing(recipeName);
        ui.printRecipeSelected(recipeName);
    }
}
