package decodex.data;

import java.util.Arrays;

/**
 * The Data class manages the data that is to be encoded or decoded.
 */
public class Data {

    private byte[] rawBytes;

    public Data(byte[] rawBytes) {
        this.rawBytes = rawBytes;
        assert !Arrays.equals(this.rawBytes, new byte[0]);
    }

    public Data(String data) {
        rawBytes = data.getBytes();
        assert !Arrays.equals(this.rawBytes, new byte[0]);
    }

    public byte[] getRawBytes() {
        return rawBytes;
    }

    @Override
    public String toString() {
        return new String(rawBytes);
    }
}
