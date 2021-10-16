package decodex.data;

import decodex.data.exception.DataManagerException;
import decodex.ui.messages.AssertMessages;
import decodex.ui.messages.ErrorMessages;
import java.util.Arrays;

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
            throw new DataManagerException(ErrorMessages.NO_DATA_FOUND);
        }
        return originalData;
    }

    public Data getCurrentData() throws DataManagerException {
        if (currentData == null) {
            throw new DataManagerException(ErrorMessages.NO_DATA_FOUND);
        }
        return currentData;
    }

    public void setOriginalData(Data originalData) {
        this.originalData = originalData;
        resetToOriginalData();
    }

    public void setCurrentData(Data currentData) throws DataManagerException {
        if (originalData == null) {
            throw new DataManagerException(ErrorMessages.NO_DATA_FOUND);
        }
        this.currentData = currentData;
    }

    /**
     * Undo all modifications done to the data.
     */
    public void resetToOriginalData() {
        currentData = originalData;
        assert Arrays.equals(currentData.getRawBytes(),
                originalData.getRawBytes()) : AssertMessages.CURRENT_DATA_SAME_AS_ORIGINAL_DATA;
    }
}
