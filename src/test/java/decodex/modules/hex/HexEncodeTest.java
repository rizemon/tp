package decodex.modules.hex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import decodex.data.Data;
import org.junit.jupiter.api.Test;

class HexEncodeTest {

    @Test
    void run_ascii_expectHexEncoding() {
        String inputString = " !\"#$%&'()*+,-./0123456789:;"
                + "<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
        HexEncode hexEncode = new HexEncode();
        Data inputData = new Data(inputString);

        Data result = hexEncode.run(inputData);
        String expectedString = "20 21 22 23 24 25 26 27 28 29 2A 2B 2C 2D 2E 2F 30 31 32 33 34 35 36 37 38 39 3A 3B "
                + "3C 3D 3E 3F 40 41 42 43 44 45 46 47 48 49 4A 4B 4C 4D 4E 4F 50 51 52 53 54 55 56 57 58 59 5A 5B 5C"
                + " 5D 5E 5F 60 61 62 63 64 65 66 67 68 69 6A 6B 6C 6D 6E 6F 70 71 72 73 74 75 76 77 78 79 7A 7B 7C "
                + "7D 7E";

        assertEquals(expectedString, result.toString());
    }

}