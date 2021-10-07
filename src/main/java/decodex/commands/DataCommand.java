package decodex.commands;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

public class DataCommand extends Command {

    public static final String COMMAND_WORD = "data";
    public static final int DATA_STRING_INDEX = 0;
    public static final int ARGUMENT_COUNT = 1;
    private String[] arguments;

    public DataCommand(String[] arguments) {
        super();
        this.arguments = arguments;
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui) throws CommandException {
        if (arguments.length < ARGUMENT_COUNT) {
            throw new CommandException("[-] Missing arguments");
        }
        String dataString = arguments[DATA_STRING_INDEX];
        Data userData = new Data(dataString);
        dataManager.setOriginalData(userData);
        System.out.printf("[+] Input: %s\n", dataString);
    }
}
