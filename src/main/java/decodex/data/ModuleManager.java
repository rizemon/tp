package decodex.data;

import decodex.data.exception.UnknownModuleException;
import decodex.modules.Base64Decoder;
import decodex.modules.Base64Encoder;
import decodex.modules.BaseModule;
import decodex.modules.HexDecoder;
import decodex.modules.HexEncoder;

/**
 * ModuleManager maintains the list of available modules and returns selected modules.
 */
public class ModuleManager {

    /**
     * Array of available modules.
     */
    private final BaseModule[] modules = {new Base64Encoder(), new Base64Decoder(), new HexEncoder(), new HexDecoder()};

    public ModuleManager() {
    }

    public BaseModule[] getModules() {
        return modules;
    }

    /**
     * Select module from a provided module name.
     *
     * @param moduleName Name of selected module.
     * @return Selected module.
     * @throws UnknownModuleException Not a module name of an available module.
     */
    public BaseModule selectModule(String moduleName) throws UnknownModuleException {
        for (BaseModule module : modules) {
            if (module.getName().equals(moduleName)) {
                return module;
            }
        }

        throw new UnknownModuleException(moduleName);
    }
}
