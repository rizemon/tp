package decodex.storage;

import decodex.ui.messages.ErrorMessages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// @@author authorName Kair0s3
public class Storage {

    /**
     * Variables for loading from the default input filepath.
     */
    public static final String DEFAULT_INPUT_DIRECTORY = "input";

    /**
     * Variable for the default output directory.
     */
    public static final String DEFAULT_OUTPUT_DIRECTORY = "output";

    /**
     * Initializes a new Storage.
     */
    public Storage() throws IOException {
        instantiateDirectories();
    }

    // @@author authorName Kair0s3
    /**
     * Reads the contents from the provided input file.
     *
     * @param fileName The name of the file specified by the user.
     * @return The byte contents from the default input file.
     * @throws IOException If the default input file does not exist.
     */
    public byte[] readFromInputFile(String fileName) throws IOException {
        byte[] inputContent;
        File inputDirectory = new File(DEFAULT_INPUT_DIRECTORY);
        File inputFile = new File(inputDirectory, fileName);
        Path inputFilePath = inputFile.toPath();

        try {
            inputContent = Files.readAllBytes(inputFilePath);
        } catch (IOException e) {
            throw new IOException(ErrorMessages.INPUT_FILE_DOES_NOT_EXIST_MESSAGE);
        }
        return inputContent;
    }

    // @@author authorName Kair0s3
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

        File outputDirectory = new File(DEFAULT_OUTPUT_DIRECTORY);
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

    // @@author authorName Kair0s3
    /**
     * Instantiates the necessary directories if they do not exist yet.
     */
    private void instantiateDirectories() {
        instantiateInputDirectory();
        instantiateOutputDirectory();
    }

    // @@author authorName Kair0s3
    /**
     * Instantiates the default input directory.
     */
    private void instantiateInputDirectory() {
        File inputDirectory = new File(DEFAULT_INPUT_DIRECTORY);
        if (!inputDirectory.exists()) {
            inputDirectory.mkdir();
        }
    }

    // @@author authorName Kair0s3
    /**
     * Instantiates the default output directory.
     */
    private void instantiateOutputDirectory() {
        File outputDirectory = new File(DEFAULT_OUTPUT_DIRECTORY);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
    }
}
