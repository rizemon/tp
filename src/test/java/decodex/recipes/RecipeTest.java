package decodex.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import decodex.data.Data;
import decodex.data.DataManager;
import decodex.data.exception.DataManagerException;
import decodex.data.exception.ModuleException;
import decodex.data.exception.RecipeException;
import decodex.modules.base64.Base64Decoder;
import decodex.modules.base64.Base64Encoder;
import decodex.modules.binary.BinaryEncoder;
import decodex.modules.hex.HexDecoder;
import decodex.modules.hex.HexEncoder;
import decodex.modules.Module;
import decodex.modules.rot.RotEncoder;
import decodex.ui.Ui;
import org.junit.jupiter.api.Test;

class RecipeTest {

    @Test
    void push_singleModule_recipeContainsOneModule() {
        Recipe recipe = new Recipe("testRecipe");
        Module module = new HexEncoder();
        recipe.push(module);

        assertEquals(recipe.getModuleList().size(), 1);
    }

    @Test
    void pop_singleModule_latestModule() throws RecipeException {
        Recipe recipe = new Recipe("testRecipe");
        Module module = new HexDecoder();
        recipe.push(module);

        Module poppedModule = recipe.pop();

        assertEquals(module, poppedModule);
    }

    @Test
    void pop_emptyRecipe_expectException() {
        Recipe recipe = new Recipe("testRecipe");

        assertThrows(RecipeException.class, () -> recipe.pop());
    }

    @Test
    void reset_emptyModuleList() {
        Recipe recipe = new Recipe("testRecipe");
        recipe.push(new HexEncoder());
        recipe.push(new HexDecoder());
        recipe.push(new Base64Encoder());
        recipe.push(new Base64Decoder());

        recipe.reset();

        assertTrue(recipe.getModuleList().isEmpty());
    }

    @Test
    void run_chainOfModules_encodedOutput() throws RecipeException, ModuleException, DataManagerException {
        Recipe recipe = new Recipe("rainbowTest");

        recipe.push(new HexEncoder());
        recipe.push(new Base64Encoder());
        recipe.push(new RotEncoder(5));
        recipe.push(new BinaryEncoder());

        DataManager dataManager = new DataManager();
        Data inputData = new Data("egg");
        dataManager.setOriginalData(inputData);

        Ui ui = new Ui();

        recipe.run(dataManager, ui);

        String result = dataManager.getCurrentData().toString();
        String expectedOutput = "0101001101101111010110100011001001010011011001010100010000110011";

        assertEquals(result, expectedOutput);
    }

}
