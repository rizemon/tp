package decodex.storage;

import decodex.ui.messages.ErrorMessages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
    private final String [] DEFAULT_DIRECTORY_LIST = {"input", "output", "recipe"};

    /**
     * The indexes of the corresponding directory in the list.
     */
    private final int INPUT_DIRECTORY_INDEX = 0;
    private final int OUTPUT_DIRECTORY_INDEX = 1;
    private final int RECIPE_DIRECTORY_INDEX = 2;

    /**
     * Initializes a new Storage.
     */
    public Storage() {
    }

    // @@author Kair0s3
    /**
     * Reads and returns the input from the provided file.
     *
     * @param fileName The name of the file specified by the user.
     * @return The byte contents from the default input file.
     * @throws IOException If something went wrong when reading the file or
     *                     the input file does not exist.
     */
    private byte[] readInputFromFile(String fileName) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_DIRECTORY_LIST[INPUT_DIRECTORY_INDEX]);

        File inputDirectory = new File(DEFAULT_DIRECTORY_LIST[INPUT_DIRECTORY_INDEX]);
        File inputFile = new File(inputDirectory, fileName);
        Path inputFilePath = inputFile.toPath();

        byte[] inputContent = readContent(inputFilePath);
        return inputContent;
    }

    // @@author Kair0s3
    /**
     * Reads and returns the recipe from the provided file.
     *
     * @param fileName The name of the file specified by the user.
     * @return The string formatted recipe from the provided file.
     * @throws IOException If something went wrong when reading the file or
     *                     the input file does not exist.
     */
    private String readRecipeFromFile(String fileName) throws IOException {
        instantiateDirectoryIfNotExist(DEFAULT_DIRECTORY_LIST[RECIPE_DIRECTORY_INDEX]);

        File recipeDirectory = new File(DEFAULT_DIRECTORY_LIST[RECIPE_DIRECTORY_INDEX]);
        File recipeFile = new File(recipeDirectory, fileName);
        Path recipeFilePath = recipeFile.toPath();

        byte[] recipeContentBytes = readContent(recipeFilePath);
        String recipeContent = recipeContentBytes.toString();
        return recipeContent;
    }

    // @@author Kair0s3
    /**
     * Reads and returns the contents.
     *
     * @param readFilePath The Path object of the file to be read.
     * @return The bytes of the read content.
     * @throws IOException           If something went wrong when reading the file.
     * @throws FileNotFoundException If the input file does not exist.
     */
    private byte[] readContent(Path readFilePath) throws IOException, FileNotFoundException {
        try {
            byte[] inputContent;
            inputContent = Files.readAllBytes(readFilePath);
            return inputContent;
        } catch (FileNotFoundException fileNotFoundError) {
            throw new FileNotFoundException(ErrorMessages.INPUT_FILE_DOES_NOT_EXIST_MESSAGE);
        } catch (IOException ioException) {
            throw new IOException("Error during file read");
        }
    }

    // @@author Kair0s3
    /**
     * Writes the given output into a file.
     *
     * @param output The encoded or decoded output.
     * @throws IOException If an I/O exception is caught when creating the output file
     *                     or when writing to the output file.
     */
    private void writeOutputToFile(String output) throws IOException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM__HH.mm.ss");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        String newOutputFileName = formattedDateTime + ".txt";

        File outputDirectory = new File(DEFAULT_DIRECTORY_LIST[OUTPUT_DIRECTORY_INDEX]);
        File outputFile = new File(outputDirectory, newOutputFileName);

        try {
            outputFile.createNewFile();
            FileWriter fw = new FileWriter(outputFile);
            fw.write(output + "\n");
            fw.close();
        } catch (IOException e) {
            throw new IOException(ErrorMessages.FILE_WRITE_ERROR_MESSAGE);
        }
    }

    // @@author Kair0s3

    /**
     * Instantiates the given directory if it does not exist yet.
     */
    private void instantiateDirectoryIfNotExist(String directoryName) throws IOException {
        File outputDirectory = new File(directoryName);
        boolean isSuccessful = false;
        if (!outputDirectory.exists()) {
            isSuccessful = outputDirectory.mkdir();
        }

        if (!isSuccessful) {
            throw new IOException("Failed to create the directory for " + directoryName);
        }
    }
}
