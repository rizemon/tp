package decodex.commands;

import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

/**
 * Command serves as a foundation for other commands to be built upon.
 */
public abstract class Command {

    public Command() {
    }

    public abstract void run(DataManager dataManager, ModuleManager moduleManager, Ui ui)
            throws CommandException, UnknownModuleException, DataManagerException;
}
