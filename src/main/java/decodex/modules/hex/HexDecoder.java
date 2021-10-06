package decodex.modules.hex;

import decodex.data.Data;
import decodex.modules.BaseModule;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The HexDecoder class converts a hexadecimal string back into its raw value.
 */
public class HexDecoder extends BaseModule {

    public static final String MODULE_NAME = "hexdecode";
    public static final String MODULE_DESCRIPTION = "Converts a hexadecimal string back into its raw value";

    public static final int HEXADECIMAL_RADIX = 16;
    public static final String REGEX_SPLIT_EVERY_2_CHARS = "(?<=\\G..)";

    public HexDecoder() {
        super(MODULE_NAME, MODULE_DESCRIPTION);

    }

    /**
     * Decodes the given hexadecimal string back into its raw value.
     *
     * @param data Data object to be decoded.
     * @return new Data object representing the decoded input.
     */
    @Override
    public Data run(Data data) {
        String inputString = data.toString();
        inputString = inputString.replace(" ", "");

        String decodedString = Arrays
                .stream(inputString.split(REGEX_SPLIT_EVERY_2_CHARS))
                .map(s -> Character.toString((char) Integer.parseInt(s, HEXADECIMAL_RADIX)))
                .collect(Collectors.joining());

        return new Data(decodedString);
    }
}
