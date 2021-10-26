package decodex.commands;

import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;
import decodex.ui.messages.RegularMessages;

public class ResetCommand extends Command {

    public static final String COMMAND_WORD = "reset";
    public static final String COMMAND_HELP_MESSAGE = "Resets the data\n"
            + "Syntax: reset\n";

    public ResetCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager) {
        dataManager.resetToOriginalData();
        ui.printSuccess(RegularMessages.REVERTED_ALL_CHANGES);
    }
}
