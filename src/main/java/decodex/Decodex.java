package decodex;

import decodex.commands.Command;
import decodex.commands.ExitCommand;
import decodex.data.DataManager;
import decodex.data.exception.CommandException;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.ParserException;
import decodex.data.exception.RecipeException;
import decodex.data.exception.RecipeManagerException;
import decodex.data.exception.ModuleManagerException;
import decodex.modules.ModuleManager;
import decodex.parser.Parser;
import decodex.recipes.Recipe;
import decodex.recipes.RecipeManager;
import decodex.storage.Storage;
import decodex.ui.Ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Decodex {

    public static Logger logger = Logger.getLogger(Decodex.class.getName());


    /**
     * Necessary objects to be initialized for Decodex to work properly.
     */
    private static DataManager dataManager;
    private static ModuleManager moduleManager;
    private static RecipeManager recipeManager;
    private static Parser parser;
    private static Ui ui;
    private static Storage storage;

    public Decodex() {
        initDecodex();
    }

    /**
     * Initializes the necessary Objects for Decodex.
     */
    private void initDecodex() {
        logger.setLevel(Level.INFO);
        dataManager = new DataManager();
        moduleManager = new ModuleManager();
        parser = new Parser();
        ui = new Ui();
        storage = new Storage();
        try {
            Recipe[] savedRecipes = loadSavedRecipes();
        } catch (IOException err) {
            ui.printError(err);
        }
        recipeManager = new RecipeManager();
    }

    /**
     * Decodex entry-point for the java.decodex.Decodex application.
     */
    public static void main(String[] args) {
        new Decodex().run();
    }

    public void run() {
        ui.printGreeting();

        Command command = null;

        do {
            String userInput = ui.readInput();
            logger.fine("User input: " + userInput);
            try {
                command = parser.parseCommand(userInput);
                assert command != null : "Command should not be null";
                command.run(dataManager, moduleManager, ui, recipeManager);
            } catch (ParserException | CommandException | ModuleManagerException
                    | DataManagerException | ModuleException | RecipeException | RecipeManagerException err) {
                ui.printError(err);
                logger.fine(err.getMessage());
            }
        } while (!(command instanceof ExitCommand));
    }

    private Recipe[] loadSavedRecipes() throws IOException{
        return storage.loadRecipesFromDirectory(moduleManager, ui);
    }
}
