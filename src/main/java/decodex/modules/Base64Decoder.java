package decodex.modules;

import java.util.Base64;

import decodex.data.Data;

public class Base64Decoder extends BaseModule {

    private final Base64.Decoder base64Decoder;

    public Base64Decoder(String name, String description) {
        super(name, description);
        base64Decoder = Base64.getDecoder();
    }

    /**
     * Performs Base64 Decoding on the provided Data object.
     *
     * @param data The Data object of the encoded data.
     * @return The Data object of the Base64-decoded data.
     */
    @Override
    public Data run(Data data) {
        Data decodedData;
        byte[] originalBytes = data.getRawBytes();
        byte[] base64DecodedBytes = base64Decoder.decode(originalBytes);
        decodedData = new Data(base64DecodedBytes);
        return decodedData;
    }
}
