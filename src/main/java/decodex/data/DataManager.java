package decodex.data;

import java.util.Arrays;

import decodex.data.exception.DataManagerException;

/**
 * The DataManager class manages the current state of the data.
 */
public class DataManager {

    /* Data provided by the user */
    private Data originalData;

    /* Data to perform the encoding/decoding operations on */
    private Data currentData;

    public DataManager() {
        originalData = null;
        currentData = null;
    }

    public Data getOriginalData() throws DataManagerException {
        if (originalData == null) {
            throw new DataManagerException("No data found");
        }
        return originalData;
    }

    public Data getCurrentData() throws DataManagerException {
        if (currentData == null) {
            throw new DataManagerException("No data found");
        }
        return currentData;
    }

    public void setOriginalData(Data originalData) {
        this.originalData = originalData;
        resetToOriginalData();
    }

    public void setCurrentData(Data currentData) throws DataManagerException {
        if (originalData == null) {
            throw new DataManagerException("No data found");
        }
        this.currentData = currentData;
    }

    /**
     * Undo all modifications done to the data.
     */
    public void resetToOriginalData() {
        currentData = originalData;
        assert Arrays.equals(currentData.getRawBytes(), originalData.getRawBytes()) : "Current data is same as "
                + "original data";
    }
}
