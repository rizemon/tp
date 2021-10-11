package decodex.modules;

import decodex.data.Data;
import decodex.data.exception.ModuleException;

/**
 * BaseModule serves as a foundation for other modules to be built upon.
 */
public abstract class BaseModule {

    protected String name;
    protected String description;

    public BaseModule(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Performs data processing on the provided Data object. Abstract method to be implemented by other modules.
     *
     * @return Data object containing new processed data.
     */
    public abstract Data run(Data data) throws ModuleException;
}
