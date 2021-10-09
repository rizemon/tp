package decodex.commands;

import decodex.data.DataManager;
import decodex.modules.BaseModule;
import decodex.modules.ModuleManager;
import decodex.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super();
    }

    @Override
    public void run(DataManager dataManager, ModuleManager moduleManager, Ui ui) {
        BaseModule[] modules = moduleManager.getModules();

        StringBuilder moduleListString = new StringBuilder();
        int maxNameWidth = 0;

        // Find number of characters of the longest module name
        for (BaseModule module : modules) {
            String moduleName = module.getName();
            if (moduleName.length() > maxNameWidth) {
                maxNameWidth = moduleName.length();
            }
        }

        // Prepare and format list of modules
        for (BaseModule module : modules) {
            String moduleName = module.getName();
            String moduleDescription = module.getDescription();
            moduleListString.append(String.format("  %-" + maxNameWidth + "s - %s\n", moduleName, moduleDescription));
        }

        System.out.println("Here are the list of supported modules:");
        System.out.println(moduleListString);
    }
}
