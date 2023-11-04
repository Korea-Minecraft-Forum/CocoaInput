package jp.axer.cocoainput.arch.darwin;

import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import jp.axer.cocoainput.util.ModLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CallbackFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger("CocoaInput:ObjC");

    // used to interact Objective-C code
    interface Func_insertText extends Callback {
        void invoke(String str, int position, int length);
    }

    interface Func_setMarkedText extends Callback {
        void invoke(String str, int position1, int length1, int position2, int length2);
    }

    interface Func_firstRectForCharacterRange extends Callback {
        void invoke(Pointer pointer);
    }

    // used to provide Objective-C with logging way
    public static Callback Func_log = new Callback() {
        public void invoke(String msg) {
            LOGGER.info(msg);
        }
    };
    public static Callback Func_error = new Callback() {
        public void invoke(String msg) {
            LOGGER.error(msg);
        }
    };
    public static Callback Func_debug = new Callback() {
        public void invoke(String msg) {
            if (ModLogger.debugMode) {
                LOGGER.debug(msg);
            }
        }
    };
}
