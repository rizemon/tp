package decodex.data;

import decodex.ui.messages.AssertMessages;

/**
 * The Data class manages the data that is to be encoded or decoded.
 */
public class Data {

    private byte[] rawBytes;

    public Data(byte[] rawBytes) {
        this.rawBytes = rawBytes;
    }

    public Data(String data) {
        assert data != null : AssertMessages.DATA_NOT_NULL;
        rawBytes = data.getBytes();
    }

    public byte[] getRawBytes() {
        return rawBytes;
    }

    @Override
    public String toString() {
        return new String(rawBytes);
    }
}
