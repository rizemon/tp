package decodex.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.data.exception.RecipeException;
import decodex.modules.Module;
import decodex.modules.base64.Base64Decoder;
import decodex.modules.base64.Base64Encoder;
import decodex.modules.hex.HexDecoder;
import decodex.modules.hex.HexEncoder;
import org.junit.jupiter.api.Test;

class RecipeTest {

    @Test
    void getName_recipeName() {
        Recipe recipe = new Recipe("testRecipe");
        String expectedName = "testRecipe";

        assertEquals(recipe.getName(), expectedName);
    }

    @Test
    void setName_validName_newRecipeName() {
        Recipe recipe = new Recipe("testRecipe");
        String newName = "chocolate pancakes";
        recipe.setName(newName);

        assertEquals(recipe.getName(), newName);
    }

    @Test
    void getModuleList_newRecipe_returnsNotNull() {
        Recipe recipe = new Recipe("testRecipe");
        assertNotNull(recipe.getModuleList());
    }

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

        assertEquals(poppedModule, poppedModule);
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

        assertEquals(recipe.getModuleList().size(), 0);
    }

}
