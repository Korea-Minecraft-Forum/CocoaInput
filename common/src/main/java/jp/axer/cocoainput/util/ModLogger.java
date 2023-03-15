package jp.axer.cocoainput.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ModLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger("CocoaInput:Java");
    public static boolean debugMode = true;

    public static void log(String msg, Object... data) {
        LOGGER.info(msg, data);
    }

    public static void error(String msg, Object... data) {
        LOGGER.error(msg, data);
    }

    public static void debug(String msg, Object... data) {
        if (debugMode) {
            LOGGER.debug(msg, data);
        }
    }
}
