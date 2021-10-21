package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

import org.junit.jupiter.api.Test;

public class SelectCommandTest {

    @Test
    public void run_validModuleName_success()
            throws UnknownModuleException, CommandException, DataManagerException, ModuleException, RecipeException,
            RecipeManagerException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        RecipeManager recipeManager = new RecipeManager();
        Ui ui = new Ui();

        Data data = new Data("hello world");
        dataManager.setOriginalData(data);

        String moduleName = "base64encode";
        String[] parameters = {};
        SelectCommand selectCommand = new SelectCommand(moduleName, parameters);
        selectCommand.run(dataManager, moduleManager, ui, recipeManager);

        assertEquals("aGVsbG8gd29ybGQ=", dataManager.getCurrentData().toString());
    }

    @Test
    public void run_moduleWithParameters_success()
            throws UnknownModuleException, CommandException, ModuleException, DataManagerException, RecipeException,
            RecipeManagerException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();
        RecipeManager recipeManager = new RecipeManager();

        Data data = new Data("hello world");
        dataManager.setOriginalData(data);

        String moduleName = "rotencode";
        String[] parameters = {"13"};
        SelectCommand selectCommand = new SelectCommand(moduleName, parameters);
        selectCommand.run(dataManager, moduleManager, ui, recipeManager);

        assertEquals("uryyb jbeyq", dataManager.getCurrentData().toString());
    }

    @Test
    public void run_blankModuleName_expectException() {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        RecipeManager recipeManager = new RecipeManager();
        Ui ui = new Ui();

        Data data = new Data("hello world");
        dataManager.setOriginalData(data);

        String moduleName = "";
        String[] parameters = {};
        SelectCommand selectCommand = new SelectCommand(moduleName, parameters);

        assertThrows(CommandException.class, () -> selectCommand.run(dataManager, moduleManager, ui, recipeManager));
    }

    @Test
    public void run_unknownModuleName_expectException() {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        RecipeManager recipeManager = new RecipeManager();
        Ui ui = new Ui();

        Data data = new Data("hello world");
        dataManager.setOriginalData(data);

        String moduleName = "unknownModule";
        String[] parameters = {};
        SelectCommand selectCommand = new SelectCommand(moduleName, parameters);

        assertThrows(UnknownModuleException.class, () -> selectCommand.run(dataManager, moduleManager, ui,
                recipeManager));
    }
}
