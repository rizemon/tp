package decodex.storage;

import decodex.data.exception.ModuleException;
import decodex.data.exception.ModuleManagerException;
import decodex.modules.ModuleManager;
import decodex.modules.Module;
import decodex.recipes.Recipe;
import decodex.ui.Ui;
import decodex.ui.messages.ErrorMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

// @@author Kair0s3
public class Storage {

    /**
     * Specifies the default directories for input, output and recipes respectively.
     */
    private static final String DEFAULT_INPUT_DIRECTORY = "input";
    private static final String DEFAULT_OUTPUT_DIRECTORY = "output";
    private static final String DEFAULT_RECIPE_DIRECTORY = "recipe";

    /**
     * Specifies the corresponding file prefixes.
     */
    private static final String DATA_FILE_PREFIX = ".data";
    private static final String RECIPE_FILE_PREFIX = ".txt";

    private static final String OUTPUT_FILENAME_FORMAT = "yyyy-dd-MM__HH.mm.ss";

    private static final String LINE_BREAK_REGEX = "\\r?\\n";
    private static final String FILENAME_EXTENSION_SPLIT_REGEX = "[.]";



    /**
     * Specifies the index for the corresponding fields.
     */
    private static final int RECIPE_NAME_INDEX = 0;
    private static final int MODULE_NAME_INDEX = 0;
    private static final int STARTING_PARAMETER_INDEX = 1;

    /**
     * Other miscellaneous constants for condition checking.
     */
    private static final int EMPTY_LENGTH = 0;

    /**
     * Initializes a new Storage.
     *
     */
    public Storage() {
    }

    /**
     * Loads all the recipes previously saved in the recipe directory.
     *
     * @param moduleManager The ModuleManager object.
     * @param ui The Ui object.
     * @return The list of Recipe objects.
     * @throws IOException            If an error occurred when reading the file.
     * @throws ModuleException        If module parameters are invalid.
     * @throws ModuleManagerException If provided module name is not an available module.
     */
    public Recipe[] loadRecipesFromDirectory(ModuleManager moduleManager, Ui ui) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_RECIPE_DIRECTORY);
        File[] recipeFiles = getAllRecipeFiles();

        if (recipeFiles.length == EMPTY_LENGTH) {
            return new Recipe[EMPTY_LENGTH];
        }
        boolean hasError = false;
        ArrayList<Recipe> tempRecipeList = new ArrayList<>();
        for (File recipeFile : recipeFiles) {
            String recipeFilename = recipeFile.getName();
            String recipeName = recipeFilename.split(FILENAME_EXTENSION_SPLIT_REGEX)[RECIPE_NAME_INDEX];
            try {
                Recipe recipe = readRecipeFromFile(recipeName, recipeFile, moduleManager);
                tempRecipeList.add(recipe);
            } catch (IOException | ModuleException | ModuleManagerException err) {
                ui.printError(err);
            }
        }

        int tempRecipeListSize = tempRecipeList.size();
        Recipe[] recipeList = tempRecipeList.toArray(new Recipe[tempRecipeListSize]);
        return recipeList;
    }

    /**
     * Gets all the recipe files in the recipe directory.
     *
     * @return The list of recipe File objects.
     */
    private File[] getAllRecipeFiles() {
        File recipeDirectory = new File(DEFAULT_RECIPE_DIRECTORY);
        File[] files = recipeDirectory.listFiles();
        File[] recipeFiles = Arrays.stream(files)
                .filter(file -> file.isFile())
                .toArray(size -> new File[size]);

        return recipeFiles;
    }

    /**
     * Parses the saved recipe content and returns the Recipe.
     *
     * @param recipeFileName The name of the recipe file.
     * @param recipeContent  The contents of the recipe file.
     * @param moduleManager  The ModuleManger object.
     * @return The Recipe object.
     * @throws ModuleException        If module parameters are invalid.
     * @throws ModuleManagerException If provided module name is not an available module.
     */
    private Recipe parseContentToRecipe(String recipeFileName, String recipeContent, ModuleManager moduleManager)
            throws ModuleException, ModuleManagerException {
        Recipe recipe = new Recipe(recipeFileName);
        String[] recipeLines = recipeContent.split(LINE_BREAK_REGEX);

        for (String recipeLine : recipeLines) {
            String trimmedRecipeLine = recipeLine.trim();
            if (trimmedRecipeLine.isEmpty()) {
                continue;
            }
            String[] tokens = trimmedRecipeLine.split(" ");
            String moduleName = tokens[MODULE_NAME_INDEX];
            String[] moduleParameters = Arrays.copyOfRange(tokens, STARTING_PARAMETER_INDEX, tokens.length);
            Module module = moduleManager.selectModule(moduleName, moduleParameters);
            recipe.push(module);
        }
        return recipe;
    }

    /**
     * Reads the recipe file contents and returns the Recipe.
     *
     * @param recipeFilename The name of the recipe.
     * @param recipeFile     The recipe File object.
     * @param moduleManager  The ModuleManager object.
     * @return The Recipe object.
     * @throws IOException            If an error occurred when reading the file.
     * @throws ModuleException        If module parameters are invalid.
     * @throws ModuleManagerException If provided module name is not an available module.
     */
    public Recipe readRecipeFromFile(String recipeFilename, File recipeFile, ModuleManager moduleManager)
            throws IOException, ModuleException, ModuleManagerException {
        Path recipeFilePath = recipeFile.toPath();

        byte[] recipeContentBytes = readContentFromFile(recipeFilePath);
        String recipeContent = convertByteArrayToString(recipeContentBytes);
        Recipe loadedRecipe = parseContentToRecipe(recipeFilename, recipeContent, moduleManager);
        return loadedRecipe;
    }

    /**
     * Reads and returns the recipe from the provided file.
     *
     * @param fileName The name of the recipe file specified by the user.
     * @return The string formatted recipe from the provided file.
     * @throws IOException If an error occurred when reading the file or
     *                     the input file does not exist.
     */
    public String readRecipeFromFile(String fileName) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_RECIPE_DIRECTORY);

        File recipeDirectory = new File(DEFAULT_RECIPE_DIRECTORY);
        File recipeFile = new File(recipeDirectory, fileName);
        Path recipeFilePath = recipeFile.toPath();

        byte[] recipeContentBytes = readContentFromFile(recipeFilePath);
        String recipeContent = convertByteArrayToString(recipeContentBytes);
        return recipeContent;
    }

    /**
     * Reads and returns the input from the provided file.
     *
     * @param fileName The name of the input file specified by the user.
     * @return The byte contents from the input file.
     * @throws IOException If an error occurred when reading the file or
     *                     the input file does not exist.
     */
    public byte[] readInputFromFile(String fileName) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_INPUT_DIRECTORY);

        File inputDirectory = new File(DEFAULT_INPUT_DIRECTORY);
        File inputFile = new File(inputDirectory, fileName);
        Path inputFilePath = inputFile.toPath();

        byte[] inputContent = readContentFromFile(inputFilePath);
        return inputContent;
    }

    /**
     * Reads and returns the contents from the file.
     *
     * @param readFilePath The Path object of the file to be read.
     * @return The bytes of the read content.
     * @throws IOException           If an error occurred when reading the file.
     * @throws FileNotFoundException If the input file does not exist.
     */
    private byte[] readContentFromFile(Path readFilePath) throws IOException, FileNotFoundException {
        try {
            return Files.readAllBytes(readFilePath);
        } catch (FileNotFoundException err) {
            throw new FileNotFoundException(ErrorMessages.INPUT_FILE_DOES_NOT_EXIST_MESSAGE);
        } catch (IOException err) {
            throw new IOException(ErrorMessages.FILE_READ_ERROR_MESSAGE);
        }
    }

    /**
     * Saves the provided output into a file.
     *
     * @param outputBytes The encoded or decoded output.
     * @throws IOException If an error occurred when creating the file
     *                     or when writing to the file.
     */
    public void saveOutputToFile(byte[] outputBytes) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_OUTPUT_DIRECTORY);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(OUTPUT_FILENAME_FORMAT);
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        String newOutputFileName = formattedDateTime + DATA_FILE_PREFIX;

        File outputDirectory = new File(DEFAULT_OUTPUT_DIRECTORY);
        File outputFile = new File(outputDirectory, newOutputFileName);
        saveBytesToFile(outputFile, outputBytes);
    }

    /**
     * Saves the content bytes into the provided file.
     *
     * @param writeFile    The file to be saved to.
     * @param contentBytes The bytes of the content to be saved.
     * @throws IOException If an error occurred when creating the file
     *                     or when writing to the file.
     */
    private void saveBytesToFile(File writeFile, byte[] contentBytes) throws IOException {
        try {
            Path writeFilePath = writeFile.toPath();
            Files.write(writeFilePath, contentBytes);
        } catch (IOException err) {
            throw new IOException(ErrorMessages.FILE_WRITE_ERROR_MESSAGE);
        }
    }

    /**
     * Saves the provided recipe into a file.
     *
     * @param recipe The recipe to be saved.
     * @throws IOException If an error occurred when creating the file
     *                     or when writing to the file.
     */
    public void saveRecipeToFile(Recipe recipe) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_RECIPE_DIRECTORY);

        String newRecipeFileName = recipe.getName() + RECIPE_FILE_PREFIX;

        File recipeDirectory = new File(DEFAULT_RECIPE_DIRECTORY);
        File outputRecipeFile = new File(recipeDirectory, newRecipeFileName);
        saveRecipeModulesToFile(outputRecipeFile, recipe);
    }

    /**
     * Saves the list of recipe's modules into the file.
     *
     * @param writeFile       The file to be saved to.
     * @param recipeToBeSaved The recipe to be saved.
     * @throws IOException If an error occurred when creating the file
     *                     or when writing to the file.
     */
    private void saveRecipeModulesToFile(File writeFile, Recipe recipeToBeSaved) throws IOException {
        ArrayList<Module> modules = recipeToBeSaved.getModuleList();

        try {
            FileWriter writer = new FileWriter(writeFile);
            String formattedModuleList = formatModuleListForSaving(modules);
            writer.write(formattedModuleList);
            writer.close();
        } catch (IOException err) {
            throw new IOException(ErrorMessages.FILE_WRITE_ERROR_MESSAGE);
        }
    }

    /**
     * Formats the list of modules for saving to file.
     *
     * @param modules The list of modules to be saved.
     * @return The formatted list of modules.
     */
    private String formatModuleListForSaving(ArrayList<Module> modules) {
        if (modules.isEmpty()) {
            return "";
        }

        String formattedModuleList = "";
        for (Module module : modules) {
            formattedModuleList = formattedModuleList.concat(module.toString() + "\n");
        }
        return formattedModuleList;
    }

    /**
     * Deletes the saved recipe file.
     *
     * @param recipeName The name of the deleted recipe.
     */
    public void deleteRecipeFile(String recipeName) throws IOException {
        String newRecipeFileName = recipeName + RECIPE_FILE_PREFIX;

        File recipeDirectory = new File(DEFAULT_RECIPE_DIRECTORY);
        File outputRecipeFile = new File(recipeDirectory, newRecipeFileName);

        if (!outputRecipeFile.exists()) {
            return;
        }

        if (!outputRecipeFile.delete()){
            throw new IOException(ErrorMessages.RECIPE_FILE_DELETE_FAILED_MESSAGE);
        }
    }

    /**
     * Instantiates the given directory if it does not exist yet.
     */
    private void instantiateDirectoryIfNotExist(String directoryName) throws IOException {
        File outputDirectory = new File(directoryName);
        if (outputDirectory.exists()) {
            return;
        }

        boolean isSuccessful;
        isSuccessful = outputDirectory.mkdir();
        if (!isSuccessful) {
            throw new IOException(ErrorMessages.DIRECTORY_INSTANTIATION_FAILED_MESSAGE + directoryName);
        }
    }

    /**
     * Converts a byte array to string value.
     *
     * @param contentBytes The bytes of the content.
     * @return The string value of the content.
     */
    private String convertByteArrayToString(byte[] contentBytes) {
        return new String(contentBytes);
    }
}
