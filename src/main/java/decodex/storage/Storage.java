package decodex.storage;

import decodex.ui.messages.ErrorMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// @@author Kair0s3
public class Storage {

    /**
     * List of default directories used.
     * 1. input folder - for reading user input from file.
     * 2. output folder - for writing processed data into file.
     * 3. recipe folder - for reading and writing recipe from and to file.
     */
    private static final String[] DEFAULT_DIRECTORY_LIST = {"input", "output", "recipe"};

    /**
     * The indexes of the corresponding directory in the list.
     */
    private static final int INPUT_DIRECTORY_INDEX = 0;
    private static final int OUTPUT_DIRECTORY_INDEX = 1;
    private static final int RECIPE_DIRECTORY_INDEX = 2;

    private static final String TEXT_FILE_PREFIX = ".txt";
    
    private static final String RECIPE_FILENAME_FORMAT = "yyyy-dd-MM__HH.mm.ss";

    /**
     * Initializes a new Storage.
     */
    public Storage() {
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
        instantiateDirectoryIfNotExist(DEFAULT_DIRECTORY_LIST[INPUT_DIRECTORY_INDEX]);

        File inputDirectory = new File(DEFAULT_DIRECTORY_LIST[INPUT_DIRECTORY_INDEX]);
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
        instantiateDirectoryIfNotExist(DEFAULT_DIRECTORY_LIST[RECIPE_DIRECTORY_INDEX]);

        File recipeDirectory = new File(DEFAULT_DIRECTORY_LIST[RECIPE_DIRECTORY_INDEX]);
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
            byte[] inputContent;
            inputContent = Files.readAllBytes(readFilePath);
            return inputContent;
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
        instantiateDirectoryIfNotExist(DEFAULT_DIRECTORY_LIST[OUTPUT_DIRECTORY_INDEX]);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(RECIPE_FILENAME_FORMAT);
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        String newOutputFileName = formattedDateTime + TEXT_FILE_PREFIX;

        File outputDirectory = new File(DEFAULT_DIRECTORY_LIST[OUTPUT_DIRECTORY_INDEX]);
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
        instantiateDirectoryIfNotExist(DEFAULT_DIRECTORY_LIST[RECIPE_DIRECTORY_INDEX]);

        String newRecipeFileName = recipeName + TEXT_FILE_PREFIX;

        File recipeDirectory = new File(DEFAULT_DIRECTORY_LIST[RECIPE_DIRECTORY_INDEX]);
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
        } catch (IOException e) {
            throw new IOException(ErrorMessages.FILE_WRITE_ERROR_MESSAGE);
        }
    }

    /**
     * Instantiates the given directory if it does not exist yet.
     */
    private void instantiateDirectoryIfNotExist(String directoryName) throws IOException {
        File outputDirectory = new File(directoryName);
        boolean isSuccessful;
        if (outputDirectory.exists()) {
            return;
        }

        isSuccessful = outputDirectory.mkdir();
        if (!isSuccessful) {
            throw new IOException(ErrorMessages.DIRECTORY_INSTANTIATION_FAILED_MESSAGE + directoryName);
        }
    }
}
