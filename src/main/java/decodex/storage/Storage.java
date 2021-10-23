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
    public Storage() throws IOException {
        instantiateDirectories();
    }

    // @@author Kair0s3
    /**
     * Reads the contents from the provided input file.
     *
     * @param fileName The name of the file specified by the user.
     * @return The byte contents from the default input file.
     * @throws IOException If the default input file does not exist.
     */
    public byte[] readFromInputFile(String fileName) throws IOException {
        File inputDirectory = new File(DEFAULT_DIRECTORY_LIST[INPUT_DIRECTORY_INDEX]);
        File inputFile = new File(inputDirectory, fileName);
        Path inputFilePath = inputFile.toPath();

        try {
            byte[] inputContent;
            inputContent = Files.readAllBytes(inputFilePath);
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
    public void writeOutputToFile(String output) throws IOException {
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
     * Instantiates the default directories if they do not exist yet.
     */
    private void instantiateDirectories() {
        for (String directoryName : DEFAULT_DIRECTORY_LIST) {
            instantiateDirectory(directoryName);
        }
    }

    // @@author Kair0s3
    /**
     * Instantiates the given directory.
     */
    private void instantiateDirectory(String directoryName) {
        File outputDirectory = new File(directoryName);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
    }
}
