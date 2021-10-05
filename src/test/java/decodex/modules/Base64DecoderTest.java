package decodex.modules;

import decodex.data.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Base64DecoderTest {

    @Test
    void run_encodedString_base64DecodedData() {
        Base64Decoder base64Decoder = new Base64Decoder();
        String encodedString = "dGVzdA==";
        String expectedString = "test";

        Data encodedData = new Data(encodedString);
        Data decodedData = base64Decoder.run(encodedData);
        assertEquals(expectedString, decodedData.toString());
    }
}