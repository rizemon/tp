package decodex.commands;

import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public ExitCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager) {
        ui.printGoodbye();
    }
}
