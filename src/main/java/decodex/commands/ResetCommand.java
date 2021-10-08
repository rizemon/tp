package decodex.commands;

import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

public class ResetCommand extends Command {

    public static final String COMMAND_WORD = "reset";

    public ResetCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui) {
        dataManager.resetToOriginalData();
        System.out.println("[+] Reverted all changes");
    }
}