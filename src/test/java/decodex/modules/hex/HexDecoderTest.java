package decodex.modules.hex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import decodex.data.Data;
import org.junit.jupiter.api.Test;

class HexDecoderTest {

    @Test
    void run_HexEncoding_expectAllAscii() {
        String inputString = "202122232425262728292a2b2c2d2e2f303132333435363738393a3b3c3d3e3f40"
                + "4142434445464748494a4b4c4d4e4f505152535455565758595a5b5c5d5e5f606162636465666768"
                + "696a6b6c6d6e6f707172737475767778797a7b7c7d7e";
        HexDecoder hexDecoder = new HexDecoder();
        Data inputData = new Data(inputString);

        Data result = hexDecoder.run(inputData);
        String expectedString = " !\"#$%&'()*+,-./0123456789:;"
                + "<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

        assertEquals(expectedString, result.toString());
    }
}
