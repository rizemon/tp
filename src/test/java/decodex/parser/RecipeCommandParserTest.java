package decodex.parser;

import decodex.commands.Command;
import decodex.commands.recipe.RecipeDeleteCommand;
import decodex.commands.recipe.RecipeNewCommand;
import decodex.commands.recipe.RecipePopCommand;
import decodex.commands.recipe.RecipePushCommand;
import decodex.commands.recipe.RecipeResetCommand;
import decodex.commands.recipe.RecipeSelectCommand;
import decodex.data.exception.CommandException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipeCommandParserTest {

    RecipeCommandParser recipeCommandParser;

    @BeforeEach
    public void setupRecipeCommandParser() {
        recipeCommandParser = new RecipeCommandParser();
    }

    @Test
    public void parseCommand_validRecipeNew_expectRecipeNewCommand() throws CommandException {
        String[] recipeCommandArguments = {"new", "testRecipe"};
        Command recipeCommand = recipeCommandParser.parseCommand(recipeCommandArguments);
        assertTrue(recipeCommand instanceof RecipeNewCommand);
    }

    @Test
    public void parseCommand_recipeNewWithoutRecipeName_expectCommandExceptionThrown() {
        String[] recipeCommandArguments = {"new"};
        assertThrows(CommandException.class, () -> recipeCommandParser.parseCommand(recipeCommandArguments));
    }

    @Test
    public void parseCommand_recipeNewAdditionalArguments_expectCommandExceptionThrown() {
        String[] recipeCommandArguments = {"new", "testRecipe", "1"};
        assertThrows(CommandException.class, () -> recipeCommandParser.parseCommand(recipeCommandArguments));
    }

    @Test
    public void parseCommand_validRecipeSelect_expectRecipeSelectCommand() throws CommandException {
        String[] recipeCommandArguments = {"select", "testRecipe"};
        Command recipeCommand = recipeCommandParser.parseCommand(recipeCommandArguments);
        assertTrue(recipeCommand instanceof RecipeSelectCommand);
    }

    @Test
    public void parseCommand_recipeSelectWithoutRecipeName_expectCommandExceptionThrown() {
        String[] recipeCommandArguments = {"select"};
        assertThrows(CommandException.class, () -> recipeCommandParser.parseCommand(recipeCommandArguments));
    }


    @Test
    public void parseCommand_recipeSelectAdditionalArguments_expectCommandExceptionThrown() {
        String[] recipeCommandArguments = {"select", "testRecipe", "1"};
        assertThrows(CommandException.class, () -> recipeCommandParser.parseCommand(recipeCommandArguments));
    }


    @Test
    public void parseCommand_validRecipePush_expectRecipePushCommand() throws CommandException {
        String[] recipeCommandArguments = {"push", "base64encode"};
        Command recipeCommand = recipeCommandParser.parseCommand(recipeCommandArguments);
        assertTrue(recipeCommand instanceof RecipePushCommand);
    }

    @Test
    public void parseCommand_validRecipePushWithParameters_expectRecipePushCommand() throws CommandException {
        String[] recipeCommandArguments = {"push", "rotencode", "13"};
        Command recipeCommand = recipeCommandParser.parseCommand(recipeCommandArguments);
        assertTrue(recipeCommand instanceof RecipePushCommand);
    }

    @Test
    public void parseCommand_recipePushWithoutModuleName_expectCommandExceptionThrown() {
        String[] recipeCommandArguments = {"push"};
        assertThrows(CommandException.class, () -> recipeCommandParser.parseCommand(recipeCommandArguments));
    }

    @Test
    public void parseCommand_validRecipePop_expectRecipePopCommand() throws CommandException {
        String[] recipeCommandArguments = {"pop"};
        Command recipeCommand = recipeCommandParser.parseCommand(recipeCommandArguments);
        assertTrue(recipeCommand instanceof RecipePopCommand);
    }

    @Test
    public void parseCommand_recipePopAdditionalArguments_expectCommandExceptionThrown() {
        String[] recipeCommandArguments = {"pop", "1"};
        assertThrows(CommandException.class, () -> recipeCommandParser.parseCommand(recipeCommandArguments));
    }

    @Test
    public void parseCommand_validRecipeReset_expectRecipeResetCommand() throws CommandException {
        String[] recipeCommandArguments = {"reset"};
        Command recipeCommand = recipeCommandParser.parseCommand(recipeCommandArguments);
        assertTrue(recipeCommand instanceof RecipeResetCommand);
    }

    @Test
    public void parseCommand_recipeResetAdditionalArguments_expectCommandExceptionThrown() {
        String[] recipeCommandArguments = {"reset", "1"};
        assertThrows(CommandException.class, () -> recipeCommandParser.parseCommand(recipeCommandArguments));
    }

    @Test
    public void parseCommand_validRecipeDelete_expectRecipeDeleteCommand() throws CommandException {
        String[] recipeCommandArguments = {"delete", "testRecipe"};
        Command recipeCommand = recipeCommandParser.parseCommand(recipeCommandArguments);
        assertTrue(recipeCommand instanceof RecipeDeleteCommand);
    }

    @Test
    public void parseCommand_recipeDeleteNoRecipeName_expectCommandExceptionThrown() {
        String[] recipeCommandArguments = {"delete"};
        assertThrows(CommandException.class, () -> recipeCommandParser.parseCommand(recipeCommandArguments));
    }

    @Test
    public void parseCommand_recipeDeleteAdditionalArguments_expectCommandExceptionThrown() {
        String[] recipeCommandArguments = {"delete", "testRecipe", "1"};
        assertThrows(CommandException.class, () -> recipeCommandParser.parseCommand(recipeCommandArguments));
    }
}
