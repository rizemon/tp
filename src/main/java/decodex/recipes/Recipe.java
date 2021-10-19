package decodex.recipes;

import java.util.ArrayList;
import java.util.logging.Logger;

import decodex.Decodex;
import decodex.data.exception.RecipeException;
import decodex.modules.Module;

/**
 * The Recipe class manages a list of Modules to be run sequentially.
 */
public class Recipe {

    private Logger logger = Decodex.logger;

    private String name;
    private ArrayList<Module> moduleList;

    /**
     * Instantiates a new recipe of modules.
     *
     * @param name The name of the recipe object to be created.
     */
    public Recipe(String name) {
        moduleList = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        assert name != null;
        this.name = name;
    }

    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    /**
     * Appends a new module to the end of the module list.
     *
     * @param module The module to be added to the recipe.
     */
    public void push(Module module) {
        moduleList.add(module);
        logger.fine(String.format("[Recipe %s] Added module %s", name, module.getName()));
    }

    /**
     * Removes the latest module that was added to the list.
     *
     * @return The module that was popped from the list.
     */
    public Module pop() throws RecipeException {
        if (moduleList.isEmpty()) {
            throw new RecipeException(RecipeException.EMPTY_RECIPE_MESSAGE);
        }

        Module removedModule = moduleList.remove(moduleList.size() - 1);
        logger.fine(String.format("[Recipe %s] Removed module %s", name, removedModule.getName()));
        return removedModule;
    }

    /**
     * Completely removes all the modules from the recipe.
     */
    public void reset() {
        moduleList.clear();
        logger.fine(String.format("[Recipe %s] Cleared", name));
    }
}
