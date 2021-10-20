package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

import org.junit.jupiter.api.Test;

public class SelectCommandTest {

    @Test
    public void run_validModuleName_success()
            throws UnknownModuleException, CommandException, DataManagerException, ModuleException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        Data data = new Data("hello world");
        dataManager.setOriginalData(data);

        String moduleName = "base64encode";
        String[] parameters = {};
        SelectCommand selectCommand = new SelectCommand(moduleName, parameters);
        selectCommand.run(dataManager, moduleManager, ui);

        assertEquals("aGVsbG8gd29ybGQ=", dataManager.getCurrentData().toString());
    }

    @Test
    public void run_moduleWithParameters_success()
            throws UnknownModuleException, CommandException, ModuleException, DataManagerException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        Data data = new Data("hello world");
        dataManager.setOriginalData(data);

        String moduleName = "rotencode";
        String[] parameters = {"13"};
        SelectCommand selectCommand = new SelectCommand(moduleName, parameters);
        selectCommand.run(dataManager, moduleManager, ui);

        assertEquals("uryyb jbeyq", dataManager.getCurrentData().toString());
    }

    @Test
    public void run_blankModuleName_expectException() throws CommandException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        Data data = new Data("hello world");
        dataManager.setOriginalData(data);

        String moduleName = "";
        String[] parameters = {};
        SelectCommand selectCommand = new SelectCommand(moduleName, parameters);

        assertThrows(CommandException.class, () -> selectCommand.run(dataManager, moduleManager, ui));
    }

    @Test
    public void run_unknownModuleName_expectException() throws CommandException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        Data data = new Data("hello world");
        dataManager.setOriginalData(data);

        String moduleName = "unknownModule";
        String[] parameters = {};
        SelectCommand selectCommand = new SelectCommand(moduleName, parameters);

        assertThrows(UnknownModuleException.class, () -> selectCommand.run(dataManager, moduleManager, ui));
    }

}
