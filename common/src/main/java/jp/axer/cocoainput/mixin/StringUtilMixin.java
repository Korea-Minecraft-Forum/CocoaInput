package jp.axer.cocoainput.mixin;

import net.minecraft.util.StringUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StringUtil.class)
public final class StringUtilMixin {

    @Inject(at = @At("RETURN"), method = "isAllowedChatCharacter", cancellable = true)
    private static void isAllowedChatCharacter(final char c, final CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(c >= ' ' && c != 127);
    }
}
