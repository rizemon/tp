package decodex.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DataManagerTest {

    @Test
    public void resetCurrentData_noUpdates_originalData() {
        Data originalData = new Data("hi");
        DataManager testManager = new DataManager(originalData);

        testManager.resetToOriginalData();

        assertTrue(testManager.getCurrentData() == originalData);
    }

    @Test
    public void resetCurrentData_updateWithNewData_originalData() {
        Data originalData = new Data("hi");
        Data newData = new Data("bye");
        DataManager testManager = new DataManager(originalData);
        testManager.setCurrentData(newData);

        testManager.resetToOriginalData();

        assertTrue(testManager.getCurrentData() == originalData);
    }
}
