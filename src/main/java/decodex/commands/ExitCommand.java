package decodex.commands;

import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public ExitCommand(DataManager dataManager, ModuleManager moduleManager, Ui ui) {
        super(dataManager, moduleManager, ui);
    }

    @Override
    public void run() {
        System.out.print("Goodbye!");
    }
}
