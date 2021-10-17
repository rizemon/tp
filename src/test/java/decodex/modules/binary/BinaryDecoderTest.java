package decodex.modules.binary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import decodex.data.Data;
import decodex.data.exception.ModuleException;
import org.junit.jupiter.api.Test;

public class BinaryDecoderTest {

    BinaryDecoder binaryDecoder = new BinaryDecoder();

    @Test
    public void run_binaryStringWithSpaces_asciiString() throws ModuleException {
        Data data = new Data(" 01110100 01100101 01110011 01110100 ");
        Data newData = binaryDecoder.run(data);
        assertEquals(newData.toString(), "test");
    }

    @Test
    public void run_binaryStringWithoutSpaces_asciiString() throws ModuleException {
        Data data = new Data("01110100011001010111001101110100");
        Data newData = binaryDecoder.run(data);
        assertEquals(newData.toString(), "test");
    }

    @Test
    public void run_binaryStringMixSpaces_asciiString() throws ModuleException {
        Data data = new Data(" 01110100 0110010101110011 01110100");
        Data newData = binaryDecoder.run(data);
        assertEquals(newData.toString(), "test");
    }

    @Test
    public void run_blankString_blankBinaryString() throws ModuleException {
        Data data = new Data("");
        Data newData = binaryDecoder.run(data);
        assertEquals(newData.toString(), "");
    }

    @Test
    public void run_nullByteBinaryString_success() throws ModuleException {
        Data data = new Data("00000000");
        Data newData = binaryDecoder.run(data);
        assertEquals(newData.toString(), "\00");
    }

    @Test
    public void run_invalidBinaryString_moduleExceptionThrown() {
        Data data = new Data("01110100 1110100");
        assertThrows(ModuleException.class, () -> binaryDecoder.run(data));
    }
}
