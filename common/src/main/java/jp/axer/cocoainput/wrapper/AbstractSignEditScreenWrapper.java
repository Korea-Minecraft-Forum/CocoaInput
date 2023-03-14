package jp.axer.cocoainput.wrapper;

import jp.axer.cocoainput.CocoaInput;
import jp.axer.cocoainput.plugin.IMEOperator;
import jp.axer.cocoainput.plugin.IMEReceiver;
import jp.axer.cocoainput.util.ModLogger;
import jp.axer.cocoainput.util.Rect;
import jp.axer.cocoainput.util.WrapperUtil;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.network.chat.Component;

public final class AbstractSignEditScreenWrapper extends IMEReceiver {

    private final AbstractSignEditScreen owner;
    private final IMEOperator myIME;

    public AbstractSignEditScreenWrapper(AbstractSignEditScreen field) {
        ModLogger.debug("AbstractSignEditScreen init: " + field.hashCode());
        owner = field;
        myIME = CocoaInput.getController().generateIMEOperator(this);
        myIME.setFocused(true);
    }

    @Override
    protected void setText(String text) {
        owner.sign.setMessage(owner.line, Component.literal(text));
        String[] util = owner.messages;
        util[owner.line] = text;
    }

    @Override
    protected String getText() {
        return owner.sign.getMessage(owner.line, false).getString();
    }

    @Override
    protected void setCursorInvisible() {
        // TODO
        owner.frame = 6;
    }

    @Override
    protected int getCursorPos() {
        return owner.signField.getCursorPos();
    }

    @Override
    protected void setCursorPos(int cursorPos) {
        owner.signField.setCursorPos(cursorPos, true);
    }

    @Override
    protected void setSelectionPos(int selectionPos) {
        owner.signField.setSelectionRange(selectionPos, selectionPos);
    }

    @Override
    public Rect getRect() {
        Font fontRendererObj = null;
        try {
            fontRendererObj = WrapperUtil.makeFont(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        float y = 91 + (owner.line - 1) * (10);
        if (!(owner.sign.getBlockState().getBlock() instanceof StandingSignBlock)) {
            y += 30;
        }
        return new Rect(
            owner.width / 2 + fontRendererObj.width(owner.sign.getMessage(owner.line, false).toString().substring(0, originalCursorPosition)) / 2,
            // owner.width / 2 + fontRendererObj.width(owner.sign.getMessage(owner.line,false).getString()) / 2,
            y,
            0,
            0
        );
    }

    public int renewCursorCounter() {
        return owner.frame + (cursorVisible ? 1 : 0);
    }
}
