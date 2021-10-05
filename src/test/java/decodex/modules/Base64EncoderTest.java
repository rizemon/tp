package decodex.modules;

import decodex.data.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Base64EncoderTest {

    private static final String BASE64_ENCODER_NAME = "base64encode";
    private static final String BASE64_ENCODER_DESC = "Base64-encodes the data";

    @Test
    void run_originalString_base64EncodedData() {
        Base64Encoder base64Encoder = new Base64Encoder(BASE64_ENCODER_NAME, BASE64_ENCODER_DESC);
        String testString = "test";
        String expectedString = "dGVzdA==";
        Data originalData = new Data(testString);
        Data encodedData = base64Encoder.run(originalData);
        assertEquals(expectedString, encodedData.toString());
    }
}