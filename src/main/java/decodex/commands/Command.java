package decodex.commands;

import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

/**
 * Command serves as a foundation for other commands to be built upon.
 */
public abstract class Command {

    DataManager dataManager;
    ModuleManager moduleManager;
    Ui ui;

    public Command(DataManager dataManager, ModuleManager moduleManager, Ui ui) {
        this.dataManager = dataManager;
        this.moduleManager = moduleManager;
        this.ui = ui;
    }

    public abstract void run();

}
