package decodex.modules;

import decodex.Decodex;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.base64.Base64Decoder;
import decodex.modules.base64.Base64Encoder;
import decodex.modules.hex.HexDecoder;
import decodex.modules.hex.HexEncoder;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ModuleManager maintains the list of available modules and returns selected modules.
 */
public class ModuleManager {

    private Logger logger = Decodex.logger;

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
                logger.fine(String.format("Selected %s", module.getName()));
                return module;
            }
        }

        throw new UnknownModuleException(moduleName);
    }
}
