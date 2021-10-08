package decodex.commands;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

public class DataCommand extends Command {

    public static final String COMMAND_WORD = "data";

    private final String argument;

    public DataCommand(String argument) {
        super();
        this.argument = argument;
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui) throws CommandException {
        if (argument.isEmpty()) {
            throw new CommandException("[-] Missing argument");
        }
        String dataString = argument;
        Data userData = new Data(dataString);
        dataManager.setOriginalData(userData);
        System.out.printf("[+] Input: %s\n", dataString);
    }
}
