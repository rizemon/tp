package decodex.modules.hex;

import decodex.data.Data;
import decodex.modules.BaseModule;

public class HexEncode extends BaseModule {

    public static final String MODULE_NAME = "hex-encode";

    public static final String MODULE_DESCRIPTION = "Converts the input string to hexadecimal bytes";
    public static final String MODULE_EXAMPLE = "\"takoyaki\" becomes \"74 61 6b 6f 79 61 6b 69\"";

    public HexEncode() {
        super(MODULE_NAME, MODULE_DESCRIPTION);

    }

    /**
     * Encodes the given Data object to Hexadecimal
     * @param data Data object to be encoded
     * @return new Data object representing the encoded input
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
