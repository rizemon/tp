package decodex.commands;

import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

/**
 * Command serves as a foundation for other commands to be built upon.
 */
public abstract class Command {

    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui) {

    }

}
