package decodex.commands;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;
import decodex.ui.messages.ErrorMessages;

public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";
    public static final String COMMAND_HELP_MESSAGE = "Shows your original input\n"
            + "Syntax: show";

    public ShowCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager)
            throws DataManagerException, CommandException {
        Data originalData = dataManager.getOriginalData();
        if (originalData == null) {
            throw new CommandException(ErrorMessages.INPUT_EMPTY);
        }
        String originalDataInput = originalData.toString();

        ui.printInput(originalDataInput);
    }
}
