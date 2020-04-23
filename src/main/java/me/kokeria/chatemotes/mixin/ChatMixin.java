package me.kokeria.chatemotes.mixin;

import me.kokeria.chatemotes.EventHandler;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class ChatMixin {
    @Shadow
    protected TextFieldWidget chatField;

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/gui/screen/ChatScreen;keyPressed" + "(III)Z", cancellable = true)
    private void init(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (keyCode == 258) {
            if (EventHandler.runReplacement(chatField) || EventHandler.runAutocomplete(chatField)) {
                callbackInfo.setReturnValue(true);
                callbackInfo.cancel();
            }
        }
    }
}
