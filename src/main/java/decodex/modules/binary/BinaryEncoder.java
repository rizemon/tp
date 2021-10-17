package decodex.modules.binary;

import decodex.data.Data;
import decodex.modules.Module;

/**
 * BinaryEncoder handles the binary encoding operations.
 */
public class BinaryEncoder extends Module {

    public static final String MODULE_NAME = "binencode";
    public static final String MODULE_DESCRIPTION = "Encodes the data into binary.";

    public BinaryEncoder() {
        super(MODULE_NAME, MODULE_DESCRIPTION);
    }

    /**
     * Performs binary encoding on the provided Data object.
     *
     * @param data The Data to be encoded.
     * @return A Data object containing the binary encoded data.
     */
    @Override
    public Data run(Data data) {
        StringBuilder encodedStringBuilder = new StringBuilder();

        for (byte b : data.getRawBytes()) {
            String binString = leftPad(Integer.toBinaryString(b));
            encodedStringBuilder.append(binString);
            encodedStringBuilder.append(" ");
        }

        String encodedString = encodedStringBuilder.toString().trim();
        return new Data(encodedString);
    }

    /**
     * Pads leading "0"s until the binary string is 8 characters long.
     *
     * @param binString Binary string to be padded.
     * @return A padded binary string.
     */
    private String leftPad(String binString) {
        if (binString.length() < 8) {
            return "0".repeat(8 - binString.length()) + binString;
        } else {
            return binString;
        }
    }
}
