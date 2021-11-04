package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.storage.Storage;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

// @@author rizemon
public class InputCommandTest {

    @Test
    public void run_missingArguments_expectException() {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Storage storage = new Storage();
        RecipeManager recipeManager = new RecipeManager();
        Ui ui = new Ui();
        String dataString = "";
        InputCommand testCommand = new InputCommand(dataString);
        assertThrows(CommandException.class, () -> testCommand.run(dataManager, moduleManager, ui, recipeManager,
                storage));
    }

    @Test
    public void run_insertData_insertedData() throws CommandException, DataManagerException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Storage storage = new Storage();
        RecipeManager recipeManager = new RecipeManager();
        Ui ui = new Ui();
        String dataString = "something";
        InputCommand testCommand = new InputCommand(dataString);
        testCommand.run(dataManager, moduleManager, ui, recipeManager, storage);
        Data testData = new Data(dataString);
        assertTrue(Arrays.equals(dataManager.getOriginalData().getRawBytes(), testData.getRawBytes()));
    }
}
