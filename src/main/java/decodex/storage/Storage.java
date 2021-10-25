package decodex.storage;

import decodex.data.exception.ModuleException;
import decodex.data.exception.ModuleManagerException;
import decodex.modules.ModuleManager;
import decodex.modules.Module;
import decodex.recipes.Recipe;
import decodex.ui.messages.ErrorMessages;

import java.io.File;
import java.io.FileNotFoundException;
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

    /**
     * Initializes a new Storage.
     *
     * @param moduleManager
     */
    public Storage(ModuleManager moduleManager) {
    }

    /**
     * Loads all the recipes previously saved in the recipe directory.
     *
     * @param moduleManager The ModuleManager object.
     * @return The list of Recipe objects.
     * @throws IOException            If something went wrong when reading the file.
     * @throws ModuleException        If module parameters are invalid.
     * @throws ModuleManagerException If provided module name is not an available module.
     */
    public Recipe[] loadRecipesFromDirectory(ModuleManager moduleManager)
            throws IOException, ModuleException, ModuleManagerException {
        instantiateDirectoryIfNotExist(DEFAULT_RECIPE_DIRECTORY);
        File[] recipeFiles = getAllRecipeFiles();

        if (recipeFiles.length == 0) {
            return null;
        }

        ArrayList<Recipe> tempRecipeList = new ArrayList<>();
        for (File recipeFile : recipeFiles) {
            Recipe recipe = readRecipeFromFile(recipeFile.getName(), recipeFile, moduleManager);
            tempRecipeList.add(recipe);
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
     * @param moduleManager  The ModuleManger object
     * @return The Recipe object.
     * @throws ModuleException        If module parameters are invalid.
     * @throws ModuleManagerException If provided module name is not an available module.
     */
    private Recipe parseContentToRecipe(String recipeFileName, String recipeContent, ModuleManager moduleManager)
            throws ModuleException, ModuleManagerException {
        Recipe recipe = new Recipe(recipeFileName);
        String[] recipeLines = recipeContent.split("\\r?\\n");

        for (String recipeLine : recipeLines) {
            if (recipeLine.isEmpty()) {
                continue;
            }
            String[] tokens = recipeLine.split(" ");
            String moduleName = tokens[0];
            String[] moduleParameters = Arrays.copyOfRange(tokens, 1, tokens.length);
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
     * @throws IOException            If something went wrong when reading the file.
     * @throws ModuleException        If module parameters are invalid.
     * @throws ModuleManagerException If provided module name is not an available module.
     */
    public Recipe readRecipeFromFile(String recipeFilename, File recipeFile, ModuleManager moduleManager)
            throws IOException, ModuleException, ModuleManagerException {
        Path recipeFilePath = recipeFile.toPath();

        byte[] recipeContentBytes = readFromFile(recipeFilePath);
        String recipeContent = new String(recipeContentBytes);
        Recipe loadedRecipe = parseContentToRecipe(recipeFilename, recipeContent, moduleManager);
        return loadedRecipe;
    }

    /**
     * Reads and returns the input from the provided file.
     *
     * @param fileName The name of the input file specified by the user.
     * @return The byte contents from the input file.
     * @throws IOException If something went wrong when reading the file or
     *                     the input file does not exist.
     */
    public byte[] readInputFromFile(String fileName) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_INPUT_DIRECTORY);

        File inputDirectory = new File(DEFAULT_INPUT_DIRECTORY);
        File inputFile = new File(inputDirectory, fileName);
        Path inputFilePath = inputFile.toPath();

        byte[] inputContent = readFromFile(inputFilePath);
        return inputContent;
    }

    /**
     * Reads and returns the recipe from the provided file.
     *
     * @param fileName The name of the recipe file specified by the user.
     * @return The string formatted recipe from the provided file.
     * @throws IOException If something went wrong when reading the file or
     *                     the input file does not exist.
     */
    public String readRecipeFromFile(String fileName) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_RECIPE_DIRECTORY);

        File recipeDirectory = new File(DEFAULT_RECIPE_DIRECTORY);
        File recipeFile = new File(recipeDirectory, fileName);
        Path recipeFilePath = recipeFile.toPath();

        byte[] recipeContentBytes = readFromFile(recipeFilePath);
        String recipeContent = recipeContentBytes.toString();
        return recipeContent;
    }

    /**
     * Reads and returns the contents from the file.
     *
     * @param readFilePath The Path object of the file to be read.
     * @return The bytes of the read content.
     * @throws IOException           If something went wrong when reading the file.
     * @throws FileNotFoundException If the input file does not exist.
     */
    private byte[] readFromFile(Path readFilePath) throws IOException, FileNotFoundException {
        try {
            return Files.readAllBytes(readFilePath);
        } catch (FileNotFoundException err) {
            throw new FileNotFoundException(ErrorMessages.INPUT_FILE_DOES_NOT_EXIST_MESSAGE);
        } catch (IOException err) {
            throw new IOException(ErrorMessages.FILE_READ_ERROR_MESSAGE);
        }
    }

    /**
     * Writes the provided output into a file.
     *
     * @param outputBytes The encoded or decoded output.
     * @throws IOException If something went wrong when creating the file
     *                     or when writing to the file.
     */
    public void writeOutputToFile(byte[] outputBytes) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_OUTPUT_DIRECTORY);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(OUTPUT_FILENAME_FORMAT);
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        String newOutputFileName = formattedDateTime + DATA_FILE_PREFIX;

        File outputDirectory = new File(DEFAULT_OUTPUT_DIRECTORY);
        File outputFile = new File(outputDirectory, newOutputFileName);
        writeToFile(outputFile, outputBytes);
    }

    /**
     * Writes the provided recipe into a file.
     *
     * @param recipeName  The name of the recipe.
     * @param recipeBytes The recipe formatted in bytes.
     * @throws IOException If something went wrong when creating the file
     *                     or when writing to the file.
     */
    public void writeRecipeToFile(String recipeName, byte[] recipeBytes) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_RECIPE_DIRECTORY);

        String newRecipeFileName = recipeName + RECIPE_FILE_PREFIX;

        File recipeDirectory = new File(DEFAULT_RECIPE_DIRECTORY);
        File outputRecipeFile = new File(recipeDirectory, newRecipeFileName);
        writeToFile(outputRecipeFile, recipeBytes);
    }

    /**
     * Writes the contents into the provided file.
     *
     * @param writeFile    The file to be written to.
     * @param contentBytes The bytes of the content to be written.
     * @throws IOException If something went wrong when creating the file
     *                     or when writing to the file.
     */
    private void writeToFile(File writeFile, byte[] contentBytes) throws IOException {
        try {
            writeFile.createNewFile();
            Path writeFilePath = writeFile.toPath();
            Files.write(writeFilePath, contentBytes);
        } catch (IOException err) {
            throw new IOException(ErrorMessages.FILE_WRITE_ERROR_MESSAGE);
        }
    }

    /**
     * Instantiates the given directory if it does not exist yet.
     */
    public void instantiateDirectoryIfNotExist(String directoryName) throws IOException {
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
}
