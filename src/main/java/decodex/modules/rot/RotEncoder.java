package decodex.modules.rot;

import decodex.data.Data;
import decodex.modules.Module;

/**
 * The RotEncoder class handles the rotation of the alphabets in the input string.
 */
public class RotEncoder extends Module {

    public static final String MODULE_NAME = "rotencode";
    public static final String MODULE_DESCRIPTION = "Rotates the alphabets in the input string by a specified amount";

    private static final char LOWERCASE_A = 'a';
    private static final char UPPERCASE_A = 'A';
    private static final int ALPHABETS_COUNT = 26;

    private final int rotateOffset;

    public RotEncoder(int rotateOffset) {
        super(MODULE_NAME, MODULE_DESCRIPTION);
        this.rotateOffset = rotateOffset;
    }

    /**
     * Applies the rotation to the given Data object.
     *
     * @param data The given Data object.
     * @return A Data object with its alphabetical characters rotated.
     */
    @Override
    public Data run(Data data) {
        StringBuilder encodedStringBuilder = new StringBuilder();

        for (byte b : data.getRawBytes()) {
            char updatedChar = (char) b;
            if (Character.isLowerCase((char) b)) {
                updatedChar = rotateLowercaseAlphabet((char) b, rotateOffset);

            } else if (Character.isUpperCase((char) b)) {
                updatedChar = rotateUppercaseAlphabet((char) b, rotateOffset);
            }
            encodedStringBuilder.append(updatedChar);
        }

        String encodedString = encodedStringBuilder.toString();
        return new Data(encodedString);
    }

    /**
     * Rotates the given lowercase alphabet.
     *
     * @param c            The given lowercase alphabet.
     * @param rotateOffset Number of positions to rotate the alphabet by.
     * @return The updated alphabet.
     */
    private char rotateLowercaseAlphabet(char c, int rotateOffset) {
        return (char) (Math.floorMod(c - LOWERCASE_A + rotateOffset, ALPHABETS_COUNT) + LOWERCASE_A);
    }

    /**
     * Rotates the given uppercase alphabet.
     *
     * @param c            The given uppercase alphabet.
     * @param rotateOffset Number of positions to rotate the alphabet by.
     * @return The updated alphabet.
     */
    private char rotateUppercaseAlphabet(char c, int rotateOffset) {
        return (char) (Math.floorMod(c - UPPERCASE_A + rotateOffset, ALPHABETS_COUNT) + UPPERCASE_A);
    }
}
