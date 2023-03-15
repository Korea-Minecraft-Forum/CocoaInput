package jp.axer.cocoainput.arch.win;

import com.sun.jna.Callback;
import org.slf4j.LoggerFactory;

public final class Logger {

    private static final org.slf4j.Logger LOGGER_JAVA = LoggerFactory.getLogger("CocoaInputWin:Java");
    private static final org.slf4j.Logger LOGGER_NATIVE = LoggerFactory.getLogger("CocoaInputWin:Clang");

    public static void log(String msg, Object... data) {
        LOGGER_JAVA.info(msg, data);
    }

    public static void error(String msg, Object... data) {
        LOGGER_JAVA.error(msg, data);
    }

    public static void debug(String msg, Object... data) {
        LOGGER_JAVA.debug(msg, data);
    }

    public static Callback clangLog = new Callback() {
        public void invoke(String msg) {
            LOGGER_JAVA.info(msg);
        }
    };
    public static Callback clangError = new Callback() {
        public void invoke(String msg) {
            LOGGER_JAVA.error(msg);
        }
    };
    public static Callback clangDebug = new Callback() {
        public void invoke(String msg) {
            LOGGER_JAVA.debug(msg);
        }
    };
}
