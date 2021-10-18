package decodex.storage;

import decodex.data.exception.FileException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {
    
    @Test
    void readFromDefaultInputFile_inputFileWithSomeContent_stringContent() throws FileException {
        Storage storage = new Storage();
        String inputContent = storage.readFromDefaultInputFile();

        assertEquals("TESTING FOR JUNIT", inputContent);
    }

    @Test
    void writeOutputToFile_decodedOrEncodedString_successfulWriteToFile() throws FileException {
        Storage storage = new Storage();
        String testStringToBeWritten = "Hi I am a Test!";

        storage.writeOutputToFile(testStringToBeWritten);
    }
}
