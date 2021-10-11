package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import decodex.data.exception.ModuleException;
import decodex.data.exception.UnknownModuleException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

class ResetCommandTest {

    @Test
<<<<<<< refs/remotes/AY2122S1-CS2113T-T10-3/master
    public void run_noChanges_sameOriginalData()
            throws CommandException, DataManagerException, UnknownModuleException, ModuleException {
=======
    public void run_noChanges_sameOriginalData() throws CommandException, DataManagerException,
            UnknownModuleException, ModuleException {
>>>>>>> Add checks and JUnit tests for hexencode/hexdecode
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        Data originalData = new Data("hi");
        dataManager.setOriginalData(originalData);

        Command testCommand = new ResetCommand();
        testCommand.run(dataManager, moduleManager, ui);
        assertTrue(Arrays.equals(dataManager.getCurrentData().getRawBytes(), originalData.getRawBytes()));
    }

    @Test
    public void run_newData_sameOriginalData()
            throws CommandException, DataManagerException, UnknownModuleException, ModuleException {
        DataManager dataManager = new DataManager();
        ModuleManager moduleManager = new ModuleManager();
        Ui ui = new Ui();

        Data originalData = new Data("hi");
        dataManager.setOriginalData(originalData);
        Data newData = new Data("bye");
        dataManager.setCurrentData(newData);

        Command testCommand = new ResetCommand();
        testCommand.run(dataManager, moduleManager, ui);
        assertTrue(Arrays.equals(dataManager.getCurrentData().getRawBytes(), originalData.getRawBytes()));
    }
}
