package decodex.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;

import decodex.storage.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import decodex.data.DataManager;
import decodex.modules.ModuleManager;
import decodex.recipes.RecipeManager;
import decodex.ui.Ui;

class HelpCommandTest {

    private ByteArrayOutputStream outputStream;
    private final PrintStream originalOutputStream = System.out;

    private final DataManager dataManager = new DataManager();
    private final ModuleManager moduleManager = new ModuleManager();
    private final Storage storage = new Storage();
    private final RecipeManager recipeManager = new RecipeManager(storage);
    private final Ui ui = new Ui();

    @BeforeEach
    public void setOutputStream() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreOutputStream() {
        System.setOut(originalOutputStream);
    }

    @Test
    public void run_allCommands_success() {
        HelpCommand command = new HelpCommand();
        command.run(dataManager, moduleManager, ui, recipeManager);
        assertFalse(outputStream.toString().isBlank());
    }
}
