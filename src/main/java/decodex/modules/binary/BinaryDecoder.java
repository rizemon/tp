package decodex.modules.binary;

import decodex.data.Data;
import decodex.data.exception.ModuleException;
import decodex.modules.Module;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BinaryDecoder handles the binary decoding operations.
 */
public class BinaryDecoder extends Module {

    public static final String MODULE_NAME = "bindecode";
    public static final String MODULE_DESCRIPTION = "Decodes the data from binary.";

    public BinaryDecoder() {
        super(MODULE_NAME, MODULE_DESCRIPTION);
    }

    /**
     * Performs binary decoding on the provided Data object.
     *
     * @param data The Data to be decoded.
     * @return A Data object containing the binary decoded data.
     */
    @Override
    public Data run(Data data) throws ModuleException {
        String binString = data.toString().trim();

        if (!isValidBinary(binString)) {
            throw new ModuleException("Invalid binary string");
        }

        binString = binString.replace(" ", "");
        String[] splitBinaryString = splitBinaryString(binString);

        byte[] newBytes = new byte[splitBinaryString.length];
        for (int i = 0; i < splitBinaryString.length; i++) {
            newBytes[i] = Byte.parseByte(splitBinaryString[i], 2);
        }

        return new Data(newBytes);
    }

    /**
     * Checks if the provided binary string is in valid format.
     *
     * @param binString Binary string to check if it is valid.
     * @return True if provided binary string is valid.
     */
    private boolean isValidBinary(String binString) {
        Pattern validBinaryPattern = Pattern.compile("^([01]{8}[ ]*)*$");
        Matcher validBinaryMatcher = validBinaryPattern.matcher(binString);
        return validBinaryMatcher.find();
    }

    /**
     * Splits the binary string into a String array of 8 characters each.
     *
     * @param binString Binary string to be split.
     * @return A String array of 8 character binary strings.
     */
    private String[] splitBinaryString(String binString) {
        assert isValidBinary(binString) : "Binary string should be valid";

        String[] splitString = new String[binString.length() / 8];

        for (int i = 0; i < binString.length(); i += 8) {
            splitString[i / 8] = binString.substring(i, i + 8);
        }

        return splitString;
    }
}
