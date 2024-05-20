package jp.axer.cocoainput.mixin;

import net.minecraft.util.StringUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(StringUtil.class)
public final class SharedConstantsMixin {

    //@ModifyConstant(method="isAllowedChatCharacter",constant=@Constant(intValue=167))
    //@Inject(at=@At("HEAD"),method="isAllowedChatCharacter")
    @Overwrite
    public static boolean isAllowedChatCharacter(char p) {
        return p >= ' ' && p != 127;
    }
}
