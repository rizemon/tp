package decodex.storage;

import decodex.data.exception.FileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {

    /**
     * Variables for loading from the default input filepath.
     */
    public static final String DEFAULT_INPUT_DIRECTORY = "input";
    public static final String DEFAULT_INPUT_FILE = "input.txt";

    /**
     * Variable for the default output directory.
     */
    public static final String DEFAULT_OUTPUT_DIRECTORY = "output";

    /**
     * Initializes a new Storage.
     */
    public Storage() throws FileException {
        instantiateDirectories();
        instantiateInputFile();
    }

    /**
     * Reads the contents from the default input file.
     *
     * @return The contents from the default input file.
     * @throws FileException If the default input file does not exist.
     */
    public String readFromDefaultInputFile() throws FileException {
        String inputContent = "";
        File inputDirectory = new File(DEFAULT_INPUT_DIRECTORY);
        File inputFile = new File(inputDirectory, DEFAULT_INPUT_FILE);

        try {
            Scanner in = new Scanner(inputFile);
            while (in.hasNext()) {
                String fileLine = in.nextLine();
                inputContent = inputContent + fileLine;
            }
        } catch (FileNotFoundException e) {
            throw new FileException(FileException.DEFAULT_INPUT_FILE_DOES_NOT_EXIST_MESSAGE);
        }
        return inputContent;
    }

    /**
     * Writes the given output into a file.
     *
     * @param output The encoded or decoded output.
     * @throws FileException If an I/O exception is caught when creating the output file
     *                       or when writing to the output file.
     */
    public void writeOutputToFile(String output) throws FileException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM__HH.mm.ss");
        String formattedDateTime = currentDateTime.format(dateTimeFormatter);
        String newOutputFileName = formattedDateTime + ".txt";

        File outputDirectory = new File(DEFAULT_OUTPUT_DIRECTORY);
        File outputFile = new File(outputDirectory, newOutputFileName);

        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            throw new FileException(FileException.FILE_CREATION_ERROR_MESSAGE);
        }

        try {
            FileWriter fw = new FileWriter(outputFile);
            fw.write(output + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instantiates the necessary directories if they do not exist yet.
     */
    private void instantiateDirectories() {
        instantiateInputDirectory();
        instantiateOutputDirectory();
    }

    /**
     * Instantiates the default input directory.
     */
    private void instantiateInputDirectory() {
        File inputDirectory = new File(DEFAULT_INPUT_DIRECTORY);
        if (!inputDirectory.exists()) {
            inputDirectory.mkdir();
        }
    }

    /**
     * Instantiates the default output directory.
     */
    private void instantiateOutputDirectory() {
        File outputDirectory = new File(DEFAULT_OUTPUT_DIRECTORY);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdir();
        }
    }

    /**
     * Instantiates the default input file.
     *
     * @throws FileException If an I/O exception is caught when creating the file.
     */
    private void instantiateInputFile() throws FileException {
        File inputDirectory = new File(DEFAULT_INPUT_DIRECTORY);
        File inputFile = new File(inputDirectory, DEFAULT_INPUT_FILE);

        if (inputFile.exists()) {
            return;
        }

        try {
            inputFile.createNewFile();
        } catch (IOException e) {
            throw new FileException(FileException.FILE_CREATION_ERROR_MESSAGE);
        }
    }
}
