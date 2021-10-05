package decodex.modules.hex;

import decodex.data.Data;
import decodex.modules.BaseModule;

public class HexEncoder extends BaseModule {

    public static final String MODULE_NAME = "hex-encode";

    public static final String MODULE_DESCRIPTION = "Converts the input string to hexadecimal bytes";

    public HexEncoder() {
        super(MODULE_NAME, MODULE_DESCRIPTION);

    }

    /**
     * Encodes the given Data object to Hexadecimal.
     *
     * @param data Data object to be encoded.
     * @return new Data object representing the encoded input.
     */
    @Override
    public Data run(Data data) {
        StringBuilder encodedStringBuilder = new StringBuilder();

        for (byte b : data.getRawBytes()) {
            encodedStringBuilder.append(String.format("%02X ", b));
        }

        String encodedString = encodedStringBuilder.toString().trim();
        return new Data(encodedString);
    }
}
