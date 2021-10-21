package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

import org.junit.jupiter.api.Test;

public class ListCommandTest {

    private static final DataManager dataManager = new DataManager();
    private static final ModuleManager moduleManager = new ModuleManager();
    private static final RecipeManager recipeManager = new RecipeManager();
    private final Ui ui = new Ui();

    @Test
    public void run_listNull_success() throws CommandException {
        String listCategory = null;
        ListCommand listCommand = new ListCommand(listCategory);
        listCommand.run(dataManager, moduleManager, ui, recipeManager);
    }

    @Test
    public void run_listModules_success() throws CommandException {
        String listCategory = "modules";
        ListCommand listCommand = new ListCommand(listCategory);
        listCommand.run(dataManager, moduleManager, ui, recipeManager);
    }

    @Test
    public void run_listRecipes_success() throws CommandException {
        String listCategory = "recipes";
        ListCommand listCommand = new ListCommand(listCategory);
        listCommand.run(dataManager, moduleManager, ui, recipeManager);
    }

    @Test
    public void run_listUnknown_throwsCommandException() {
        String listCategory = "unknown";
        ListCommand listCommand = new ListCommand(listCategory);
        assertThrows(CommandException.class, () -> listCommand.run(dataManager, moduleManager, ui, recipeManager));
    }
}
