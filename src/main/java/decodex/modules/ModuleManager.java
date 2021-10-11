package decodex.modules;

import decodex.Decodex;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.base64.Base64Decoder;
import decodex.modules.base64.Base64Encoder;
import decodex.modules.hex.HexDecoder;
import decodex.modules.hex.HexEncoder;
import java.util.logging.Logger;

/**
 * ModuleManager maintains the list of available modules and returns selected modules.
 */
public class ModuleManager {

    private Logger logger = Decodex.logger;

    /**
     * Array of available modules.
     */
    private final Module[] modules = {new Base64Encoder(), new Base64Decoder(), new HexEncoder(), new HexDecoder()};

    public ModuleManager() {
        // Log available modules
        for (Module module : modules) {
            logger.fine(String.format("Loaded %s", module.getName()));
        }
    }

    public Module[] getModules() {
        return modules;
    }

    /**
     * Select module from a provided module name.
     *
     * @param moduleName Name of selected module.
     * @return Selected module.
     * @throws UnknownModuleException Not a module name of an available module.
     */
    public Module selectModule(String moduleName) throws UnknownModuleException {
        for (Module module : modules) {
            if (module.getName().equals(moduleName)) {
                logger.fine(String.format("Selected %s", module.getName()));
                return module;
            }
        }

        throw new UnknownModuleException(moduleName);
    }
}
