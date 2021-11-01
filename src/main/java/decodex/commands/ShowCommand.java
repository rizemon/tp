package decodex.commands;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.storage.Storage;
import decodex.ui.Ui;
import decodex.ui.messages.ErrorMessages;

public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";
    public static final String COMMAND_HELP_MESSAGE = "Shows the current data\n"
            + "Syntax: show";

    public ShowCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager,
                             Storage storage) throws DataManagerException, CommandException {
        Data currentData = dataManager.getCurrentData();
        if (currentData == null) {
            throw new CommandException(ErrorMessages.INPUT_EMPTY);
        }
        String currentDataInput = currentData.toString();
        ui.printOutput(currentDataInput);
    }
}
