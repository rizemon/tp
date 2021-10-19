package decodex.commands;

import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    private static final int MODULE_NAME_INDEX = 0;
    private static final int MODULE_DESCRIPTION_INDEX = 1;

    public ListCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui) {
        String[][] modules = moduleManager.getModules();
        assert modules.length > 0 : "Number of modules should be greater than 0";

        StringBuilder moduleListString = new StringBuilder();
        int maxNameWidth = 0;

        // Find number of characters of the longest module name
        for (String[] module : modules) {
            String moduleName = module[MODULE_NAME_INDEX];
            if (moduleName.length() > maxNameWidth) {
                maxNameWidth = moduleName.length();
            }
        }

        // Prepare and format list of modules
        for (String[] module : modules) {
            String moduleName = module[MODULE_NAME_INDEX];
            String moduleDescription = module[MODULE_DESCRIPTION_INDEX];
            moduleListString.append(String.format("  %-" + maxNameWidth + "s - %s\n", moduleName, moduleDescription));
        }

        ui.printModuleList(moduleListString.toString());
    }
}
