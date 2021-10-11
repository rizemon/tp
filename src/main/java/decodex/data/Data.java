package decodex.data;

import java.util.Arrays;

/**
 * The Data class manages the data that is to be encoded or decoded.
 */
public class Data {

    private byte[] rawBytes;

    public Data(byte[] rawBytes) {
        this.rawBytes = rawBytes;
        assert this.rawBytes.length > 0 : "Data is non-empty";
    }

    public Data(String data) {
        rawBytes = data.getBytes();
        assert this.rawBytes.length > 0 : "Data is non-empty";
    }

    public byte[] getRawBytes() {
        return rawBytes;
    }

    @Override
    public String toString() {
        return new String(rawBytes);
    }
}
