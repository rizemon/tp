package decodex.data;

/**
 * The DataManager class manages the current state of the data.
 */
public class DataManager {

    /* Data provided by the user */
    private final Data originalData;

    /* Data to perform the encoding/decoding operations on */
    private Data currentData;

    public DataManager(Data originalData) {
        this.originalData = originalData;
        currentData = originalData;
    }

    public Data getCurrentData() {
        return currentData;
    }

    public void setCurrentData(Data currentData) {
        this.currentData = currentData;
    }

    /**
     * Undo all modifications done to the data.
     */
    public void resetToOriginalData() {
        currentData = originalData;
    }
}
