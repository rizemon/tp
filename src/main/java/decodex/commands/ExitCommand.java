package decodex.commands;

import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public ExitCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui) {
        System.out.print("Goodbye!");
    }
}
