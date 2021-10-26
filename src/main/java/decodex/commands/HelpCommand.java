package decodex.commands;

import decodex.commands.recipe.RecipeDeleteCommand;
import decodex.commands.recipe.RecipeNewCommand;
import decodex.commands.recipe.RecipePopCommand;
import decodex.commands.recipe.RecipePushCommand;
import decodex.commands.recipe.RecipeResetCommand;
import decodex.commands.recipe.RecipeSelectCommand;
import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_HELP_MESSAGE = "Shows list of available commands\n"
            + "Syntax: help";

    private static String[] BASIC_COMMAND_HELP_MESSAGES = {
        HelpCommand.COMMAND_HELP_MESSAGE,
        ExitCommand.COMMAND_HELP_MESSAGE,
        InputCommand.COMMAND_HELP_MESSAGE,
        ListCommand.COMMAND_HELP_MESSAGE,
        SelectCommand.COMMAND_HELP_MESSAGE
    };

    private static String[] RECIPE_COMMAND_HELP_MESSAGES = {
        RecipeNewCommand.COMMAND_HELP_MESSAGE,
        RecipeSelectCommand.COMMAND_HELP_MESSAGE,
        RecipePushCommand.COMMAND_HELP_MESSAGE,
        RecipePopCommand.COMMAND_HELP_MESSAGE,
        RecipeResetCommand.COMMAND_HELP_MESSAGE,
        RecipeDeleteCommand.COMMAND_HELP_MESSAGE
    };

    public HelpCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui, RecipeManager recipeManager) {
        ui.printCommandHelp(BASIC_COMMAND_HELP_MESSAGES, RECIPE_COMMAND_HELP_MESSAGES);
    }
}
