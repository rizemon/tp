package decodex.data;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataTest {

    @Test
    public void getRawBytes_stringConstructor_actualBytesOfString() {
        Data testData = new Data("hi");
        byte[] expectedBytes = new byte[]{104, 105}; // ASCII values of "hi"
        assertTrue(Arrays.equals(testData.getRawBytes(), expectedBytes));
    }

    @Test
    public void getRawBytes_bytesConstructor_actualBytesOfString() {
        byte[] expectedBytes = new byte[]{104, 105}; // ASCII values of "hi"
        Data testData = new Data(expectedBytes);
        assertTrue(Arrays.equals(testData.getRawBytes(), expectedBytes));
    }

    @Test
    public void toString_correctStringRepresentation() {
        String testString = "hi";
        Data testData = new Data(testString);
        assertTrue(testString.equals(testData.toString()));
    }
}
