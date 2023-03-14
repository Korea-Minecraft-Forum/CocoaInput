package jp.axer.cocoainput.wrapper;

import java.util.List;
import java.util.Optional;
import jp.axer.cocoainput.CocoaInput;
import jp.axer.cocoainput.plugin.IMEOperator;
import jp.axer.cocoainput.plugin.IMEReceiver;
import jp.axer.cocoainput.util.ModLogger;
import jp.axer.cocoainput.util.Rect;
import jp.axer.cocoainput.util.WrapperUtil;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.StringSplitter;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;

public final class BookEditScreenWrapper extends IMEReceiver {

	private final BookEditScreen owner;
    private final IMEOperator myIME;

    public BookEditScreenWrapper(BookEditScreen field) {
        ModLogger.log("BookEditScreen init: " + field.hashCode());
        owner = field;
        myIME = CocoaInput.getController().generateIMEOperator(this);
        myIME.setFocused(true);
    }

	@Override
    protected void setText(String text) {
        if (owner.isSigning) {
            owner.title = text;
        } else {
            owner.setCurrentPageText(text);
        }
    }

	@Override
    protected String getText() {
        if (owner.isSigning) {
            return owner.title;
        } else {
            return owner.getCurrentPageText();
        }
    }

	@Override
    protected void setCursorInvisible() {
		// TODO
        owner.frameTick = 6;
    }

	@Override
    protected int getCursorPos() {
        if (owner.isSigning) {
            return owner.titleEdit.getCursorPos();
        } else {
            return owner.pageEdit.getCursorPos();
        }
    }

	@Override
    protected void setCursorPos(int cursorPos) {
        if (owner.isSigning) {
            owner.titleEdit.setCursorPos(cursorPos, true);
        } else {
            owner.pageEdit.setCursorPos(cursorPos, true);
        }
    }

	@Override
    protected void setSelectionPos(int selectionPos) {
        if (owner.isSigning) {
            owner.titleEdit.setSelectionRange(selectionPos, selectionPos);
        } else {
            owner.pageEdit.setSelectionRange(selectionPos, selectionPos);
        }
    }

    @Override
    public Rect getRect() {
        Font fontRendererObj = null;
        try {
            fontRendererObj = WrapperUtil.makeFont(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (owner.isSigning) {
            return new Rect(
				(fontRendererObj.width(owner.title.substring(0, originalCursorPosition)) / 2 + ((owner.width - 192) / 2) + 36 + (116 - 0) / 2),
				(50 + fontRendererObj.lineHeight),
				0,
				0
            );
        } else {
            StringSplitter manager = fontRendererObj.getSplitter();
            List<FormattedText> lines = manager.splitLines(owner.getCurrentPageText(), 116, Style.EMPTY);
            final String[] lastLine = new String[1];
            FormattedText.ContentConsumer acceptor = new FormattedText.ContentConsumer() {
                @Override
                public Optional accept(String p_accept_1_) {
                    lastLine[0] = p_accept_1_;
                    return Optional.empty();
                }
            };
            lines.get(lines.size() - 1).visit(acceptor);

            return new Rect(
				(((owner.width - 192) / 2) + 36 + fontRendererObj.width(lastLine[0])),
				(34 + lines.size() * fontRendererObj.lineHeight),
				0,
				0
            );
        }
    }

    public int renewCursorCounter() {
        return owner.frameTick + (cursorVisible ? 1 : 0);
    }
}
