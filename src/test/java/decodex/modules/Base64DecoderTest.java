package decodex.modules;

import decodex.data.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Base64DecoderTest {

    private static final String BASE64_DECODER_NAME = "base64decode";
    private static final String BASE64_DECODER_DESC = "Base64-decodes the data";

    @Test
    void run_encodedString_base64DecodedData() {
        Base64Decoder base64Decoder = new Base64Decoder(BASE64_DECODER_NAME, BASE64_DECODER_DESC);
        String encodedString = "dGVzdA==";
        String expectedString = "test";
        Data encodedData = new Data(encodedString);
        Data decodedData = base64Decoder.run(encodedData);
        assertEquals(expectedString, decodedData.toString());
    }
}