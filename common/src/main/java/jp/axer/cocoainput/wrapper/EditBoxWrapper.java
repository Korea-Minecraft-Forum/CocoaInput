
package jp.axer.cocoainput.wrapper;

import jp.axer.cocoainput.CocoaInput;
import jp.axer.cocoainput.plugin.IMEOperator;
import jp.axer.cocoainput.plugin.IMEReceiver;
import jp.axer.cocoainput.util.ModLogger;
import jp.axer.cocoainput.util.Rect;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public final class EditBoxWrapper extends IMEReceiver {

    private final EditBox owner;
    private final IMEOperator myIME;

    public EditBoxWrapper(EditBox field) {
        ModLogger.debug("EditBox init: " + field.hashCode());
        owner = field;
        myIME = CocoaInput.getController().generateIMEOperator(this);
    }

    public void setCanLoseFocus(boolean newParam) {
        if (!newParam) setFocused(true);
    }

    public void setFocused(boolean newParam) {
        owner.setFormatter(((abc, def) -> Component.literal(abc).getVisualOrderText()));
        myIME.setFocused(newParam);
    }

    @Override
    protected void setText(String text) {
        owner.value = text;
    }

    @Override
    protected String getText() {
        return owner.value;
    }

    public boolean isCursorVisible() {
        return cursorVisible;
    }

    @Override
    protected int getCursorPos() {
        return owner.getCursorPosition();
    }

    @Override
    protected void setCursorPos(int cursorPos) {
        owner.moveCursorTo(cursorPos, false);
    }

    @Override
    protected void setSelectionPos(int selectionPos) {
        owner.setHighlightPos(selectionPos);
    }

    @Override
    public Rect getRect() {
        return new Rect( // {x,y}
            (owner.font.width(owner.getValue().substring(0, originalCursorPosition)) + (owner.bordered ? owner.getX() + 4 : owner.getX())),
            (owner.font.lineHeight + (owner.bordered ? owner.getY() + (owner.getHeight() - 8) / 2 : owner.getY())),
            owner.getWidth(),
            owner.getHeight()
        );
    }

    @Override
    protected void notifyParent(String text) {
        owner.onValueChange(text);
    }
}
