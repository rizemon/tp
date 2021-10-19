package decodex.modules;

import java.util.logging.Logger;

import decodex.Decodex;
import decodex.data.exception.ModuleException;
import decodex.data.exception.UnknownModuleException;
import decodex.modules.base64.Base64Decoder;
import decodex.modules.base64.Base64Encoder;
import decodex.modules.binary.BinaryDecoder;
import decodex.modules.binary.BinaryEncoder;
import decodex.modules.hex.HexDecoder;
import decodex.modules.hex.HexEncoder;
import decodex.modules.rot.RotEncoder;
import decodex.ui.messages.ErrorMessages;

/**
 * ModuleManager maintains the list of available modules and returns selected modules.
 */
public class ModuleManager {

    private final Logger logger = Decodex.logger;

    private static final int MODULE_NAME_INDEX = 0;

    /**
     * Available modules.
     */
    private static final String[][] MODULE_LIST = {
            {Base64Encoder.MODULE_NAME, Base64Encoder.MODULE_DESCRIPTION},
            {HexEncoder.MODULE_NAME, HexDecoder.MODULE_DESCRIPTION},
            {BinaryEncoder.MODULE_NAME, BinaryDecoder.MODULE_DESCRIPTION},
            {RotEncoder.MODULE_NAME, RotEncoder.MODULE_DESCRIPTION}
    };

    public ModuleManager() {
        // Log available modules
        for (String[] module : MODULE_LIST) {
            logger.fine(String.format("Loaded %s", module[MODULE_NAME_INDEX]));
        }
    }

    public String[][] getModules() {
        return MODULE_LIST;
    }

    /**
     * Select module from a provided module name.
     *
     * @param moduleName Name of selected module.
     * @param parameters Parameters for specific commands.
     * @return Selected module.
     * @throws UnknownModuleException If provided module name is not an available module.
     * @throws ModuleException If module parameters are invalid.
     */
    public Module selectModule(String moduleName, String[] parameters) throws UnknownModuleException, ModuleException {
        Module module;

        switch (moduleName) {
        case Base64Encoder.MODULE_NAME:
            module = prepareBase64Encoder();
            break;
        case Base64Decoder.MODULE_NAME:
            module = prepareBase64Decoder();
            break;
        case HexEncoder.MODULE_NAME:
            module = prepareHexEncoder();
            break;
        case HexDecoder.MODULE_NAME:
            module = prepareHexDecoder();
            break;
        case RotEncoder.MODULE_NAME:
            module = prepareRotEncoder(parameters);
            break;
        default:
            throw new UnknownModuleException(moduleName);
        }

        logger.fine(String.format("Selected %s", module.getName()));
        return module;
    }

    /**
     * Prepares and returns the Base64Encoder.
     *
     * @return The Base64Encoder object.
     */
    private Module prepareBase64Encoder() {
        return new Base64Encoder();
    }

    /**
     * Prepares and returns the Base64Decoder.
     *
     * @return The Base64Decoder object.
     */
    private Module prepareBase64Decoder() {
        return new Base64Decoder();
    }

    /**
     * Prepares and returns the HexEncoder.
     *
     * @return The HexEncoder object.
     */
    private Module prepareHexEncoder() {
        return new HexEncoder();
    }

    /**
     * Prepares and returns the HexDecoder.
     *
     * @return The HexDecoder object.
     */
    private Module prepareHexDecoder() {
        return new HexDecoder();
    }

    /**
     * Prepares and returns the RotEncoder with specified rotation.
     *
     * @param parameters String array of module parameters.
     * @return The RotEncoder object.
     * @throws ModuleException If the number of parameters are incorrect or invalid.
     */
    private Module prepareRotEncoder(String[] parameters) throws ModuleException {
        if (parameters == null) {
            throw new ModuleException(ErrorMessages.MISSING_MODULE_PARAMETERS);
        }

        if (parameters.length > 1) {
            throw new ModuleException(ErrorMessages.TOO_MANY_MODULE_PARAMETERS);
        }

        // First parameter is the rotation offset
        try {
            return new RotEncoder(Integer.parseInt(parameters[0]));
        } catch (NumberFormatException e) {
            throw new ModuleException(ErrorMessages.INVALID_MODULE_PARAMETERS);
        }
    }
}
