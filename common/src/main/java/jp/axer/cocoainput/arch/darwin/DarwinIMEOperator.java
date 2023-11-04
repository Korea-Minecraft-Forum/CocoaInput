package jp.axer.cocoainput.arch.darwin;

import com.mojang.blaze3d.platform.Window;
import com.sun.jna.Pointer;
import jp.axer.cocoainput.CocoaInput;
import jp.axer.cocoainput.arch.darwin.CallbackFunction.Func_firstRectForCharacterRange;
import jp.axer.cocoainput.arch.darwin.CallbackFunction.Func_insertText;
import jp.axer.cocoainput.arch.darwin.CallbackFunction.Func_setMarkedText;
import jp.axer.cocoainput.plugin.IMEOperator;
import jp.axer.cocoainput.plugin.IMEReceiver;
import jp.axer.cocoainput.util.ModLogger;
import jp.axer.cocoainput.util.Rect;
import net.minecraft.client.Minecraft;
import java.util.UUID;

public class DarwinIMEOperator implements IMEOperator {

    IMEReceiver owner;
    String uuid;
    Func_insertText insertText_p;
    Func_setMarkedText setMarkedText_p;
    Func_firstRectForCharacterRange firstRectForCharacterRange_p;
    boolean isFocused = false;

    public DarwinIMEOperator(IMEReceiver field) {
        this.owner = field;
        uuid = UUID.randomUUID().toString();

        insertText_p = new Func_insertText() {
            @Override
            public void invoke(String str, int position, int length) {
                ModLogger.debug("Textfield " + uuid + " received inserted text.");
                owner.insertText(str, position, length);
            }
        };

        setMarkedText_p = new Func_setMarkedText() {
            @Override
            public void invoke(String str, int position1, int length1, int position2, int length2) {
                ModLogger.debug("MarkedText changed at " + uuid + ".");
                owner.setMarkedText(str, position1, length1, position2, length2);
            }
        };

        firstRectForCharacterRange_p = new Func_firstRectForCharacterRange() {
            @Override
            public void invoke(Pointer pointer) {
                ModLogger.debug("Called to determine where to draw.");
                Rect point = owner.getRect();
                float[] buff;
                if (point == null) {
                    buff = new float[]{0, 0, 0, 0};
                } else {
                    buff = new float[]{point.getX(), point.getY(), point.getWidth(), point.getHeight()};
                }
                Window window = Minecraft.getInstance().getWindow();
                double factor = CocoaInput.getScreenScaledFactor();
                buff[0] *= factor;
                buff[1] *= factor;
                buff[2] *= factor;
                buff[3] *= factor;

                buff[0] += window.getX();
                buff[1] += window.getY();

                pointer.write(0, buff, 0, 4);
            }
        };

        ModLogger.log("IMEOperator addInstance: " + uuid);
        Handle.INSTANCE.addInstance(uuid, insertText_p, setMarkedText_p, firstRectForCharacterRange_p);
    }

    public void discardMarkedText() {
        Handle.INSTANCE.discardMarkedText(uuid);
    }

    public void removeInstance() {
        Handle.INSTANCE.removeInstance(uuid);
    }

    public void setFocused(boolean yn) {
        if (yn != isFocused) {
            ModLogger.log("IMEOperator.setFocused: " + (yn ? "true" : "false"));
            Handle.INSTANCE.setIfReceiveEvent(uuid, yn ? 1 : 0);
            isFocused = yn;
        }
    }
}
