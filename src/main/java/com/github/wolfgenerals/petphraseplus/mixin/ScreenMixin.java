package com.github.wolfgenerals.petphraseplus.mixin;


import com.github.wolfgenerals.petphraseplus.ModifierKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Shadow
    protected MinecraftClient client;

    @Inject(method = "sendMessage(Ljava/lang/String;Z)V", at = @At("HEAD"), cancellable = true)
    public void onSendMessage(String message, boolean toHud, CallbackInfo ci) {
        if (toHud) {
            this.client.inGameHud.getChatHud().addToMessageHistory(message);
        }
        if (ModifierKt.needModify(message)) message = ModifierKt.modifyMessage(message);
        this.client.player.sendChatMessage(message);
        ci.cancel();
    }
}

