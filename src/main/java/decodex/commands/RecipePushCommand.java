package decodex.commands;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.RecipeManagerException;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.Module;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;
import decodex.ui.messages.ErrorMessages;

public class RecipePushCommand extends Command {

    public static final String COMMAND_WORD = "push";

    private final String moduleName;
    private final String[] parameters;

    public RecipePushCommand(String moduleName, String[] parameters) {
        super();
        this.moduleName = moduleName;
        this.parameters = parameters;
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager)
            throws CommandException, UnknownModuleException, ModuleException, RecipeManagerException {
        if (moduleName.isBlank()) {
            throw new CommandException(ErrorMessages.MISSING_MODULE_NAME);
        }

        Module module = moduleManager.selectModule(moduleName, parameters);
        recipeManager.pushModuleIntoEditedRecipe(module);
        ui.printModuleAddedToRecipe(module.getName(), recipeManager.getEditingRecipe().getName());
    }
}
