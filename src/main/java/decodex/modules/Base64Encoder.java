package decodex.modules;

import java.util.Base64;

import decodex.data.Data;

public class Base64Encoder extends BaseModule {

    private final Base64.Encoder base64Encoder;

    public Base64Encoder(String name, String description) {
        super(name, description);
        base64Encoder = Base64.getEncoder();
    }

    /**
     * Performs Base64 Encoding on the provided Data object.
     *
     * @param data The Data object of the original/previous data.
     * @return The Data object of the Base64-encoded data.
     */
    @Override
    public Data run(Data data) {
        Data encodedData;
        byte[] originalBytes = data.getRawBytes();
        byte[] base64EncodedBytes = base64Encoder.encode(originalBytes);
        encodedData = new Data(base64EncodedBytes);
        return encodedData;
    }
}
