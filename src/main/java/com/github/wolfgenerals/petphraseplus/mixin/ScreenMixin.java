package com.github.wolfgenerals.petphraseplus.mixin;


import com.github.wolfgenerals.petphraseplus.Util;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Screen.class)
public abstract class ScreenMixin  {
    @Shadow
    protected MinecraftClient client;



    @Inject(method = "sendMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    public void onSendMessage(String message, CallbackInfo ci) {
        this.client.inGameHud.getChatHud().addToMessageHistory(message);
        assert this.client.player != null;
        this.client.player.sendChatMessage(Util.petphraseplus(message));
        ci.cancel();
 }
}

