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
    private static final int MODULE_PARAMETER_INDEX = 0;

    /**
     * List of available modules.
     */
    private static final String[][] MODULE_LIST = {
            {Base64Encoder.MODULE_NAME, Base64Encoder.MODULE_DESCRIPTION},
            {Base64Decoder.MODULE_NAME, Base64Decoder.MODULE_DESCRIPTION},
            {HexEncoder.MODULE_NAME, HexEncoder.MODULE_DESCRIPTION},
            {HexDecoder.MODULE_NAME, HexDecoder.MODULE_DESCRIPTION},
            {BinaryEncoder.MODULE_NAME, BinaryEncoder.MODULE_DESCRIPTION},
            {BinaryDecoder.MODULE_NAME, BinaryDecoder.MODULE_DESCRIPTION},
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
     * Selects module from a provided module name.
     *
     * @param moduleName Name of selected module.
     * @param parameters Parameters for specific commands.
     * @return Selected module.
     * @throws UnknownModuleException If provided module name is not an available module.
     * @throws ModuleException        If module parameters are invalid.
     */
    public Module selectModule(String moduleName, String[] parameters) throws UnknownModuleException, ModuleException {
        assert parameters != null : "parameters should not be null";

        Module module;

        switch (moduleName) {
        case Base64Encoder.MODULE_NAME:
            hasNoParameters(parameters);
            module = prepareBase64Encoder();
            break;
        case Base64Decoder.MODULE_NAME:
            hasNoParameters(parameters);
            module = prepareBase64Decoder();
            break;
        case HexEncoder.MODULE_NAME:
            hasNoParameters(parameters);
            module = prepareHexEncoder();
            break;
        case HexDecoder.MODULE_NAME:
            hasNoParameters(parameters);
            module = prepareHexDecoder();
            break;
        case BinaryEncoder.MODULE_NAME:
            hasNoParameters(parameters);
            module = prepareBinaryEncoder();
            break;
        case BinaryDecoder.MODULE_NAME:
            hasNoParameters(parameters);
            module = prepareBinaryDecoder();
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
     * Throws ModuleException if the module parameters are empty.
     *
     * @param parameters Parameters for specific commands.
     * @throws ModuleException If the String array of module parameters is not empty.
     */
    private void hasNoParameters(String[] parameters) throws ModuleException {
        if (parameters.length > 0) {
            throw new ModuleException(ErrorMessages.TOO_MANY_MODULE_PARAMETERS);
        }
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
     * Prepares and returns the BinaryEncoder.
     *
     * @return The BinaryEncoder object.
     */
    private Module prepareBinaryEncoder() {
        return new BinaryEncoder();
    }

    /**
     * Prepares and returns the BinaryDecoder.
     *
     * @return The BinaryDecoder object.
     */
    private Module prepareBinaryDecoder() {
        return new BinaryDecoder();
    }

    /**
     * Prepares and returns the RotEncoder with specified rotation.
     *
     * @param parameters String array of module parameters.
     * @return The RotEncoder object.
     * @throws ModuleException If the number of parameters are incorrect or invalid.
     */
    private Module prepareRotEncoder(String[] parameters) throws ModuleException {
        if (parameters.length == 0) {
            throw new ModuleException(ErrorMessages.MISSING_MODULE_PARAMETERS);
        } else if (parameters.length > 1) {
            throw new ModuleException(ErrorMessages.TOO_MANY_MODULE_PARAMETERS);
        }

        // First parameter is the rotation offset
        try {
            return new RotEncoder(Integer.parseInt(parameters[MODULE_PARAMETER_INDEX]));
        } catch (NumberFormatException err) {
            throw new ModuleException(ErrorMessages.INVALID_MODULE_PARAMETERS);
        }
    }
}
