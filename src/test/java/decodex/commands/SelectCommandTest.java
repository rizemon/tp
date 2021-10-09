package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;
import org.junit.jupiter.api.Test;

public class SelectCommandTest {

    @Test
    public void run_validModuleName_success() throws UnknownModuleException, CommandException, DataManagerException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        DataCommand dataCommand = new DataCommand("hello world");
        dataCommand.run(dataManager, moduleManager, ui);

        String moduleName = "base64encode";
        Command selectCommand = new SelectCommand(moduleName);
        selectCommand.run(dataManager, moduleManager, ui);

        assertEquals("aGVsbG8gd29ybGQ=", dataManager.getCurrentData().toString());
    }

    @Test
    public void run_blankModuleName_expectException() throws CommandException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        DataCommand dataCommand = new DataCommand("hello world");
        dataCommand.run(dataManager, moduleManager, ui);

        String moduleName = "";
        Command selectCommand = new SelectCommand(moduleName);

        assertThrows(CommandException.class, () -> selectCommand.run(dataManager, moduleManager, ui));
    }

    @Test
    public void run_unknownModuleName_expectException() throws CommandException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        DataCommand dataCommand = new DataCommand("hello world");
        dataCommand.run(dataManager, moduleManager, ui);

        String moduleName = "unknownModule";
        Command selectCommand = new SelectCommand(moduleName);

        assertThrows(UnknownModuleException.class, () -> selectCommand.run(dataManager, moduleManager, ui));
    }

}
