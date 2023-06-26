package com.github.wolfgenerals.petphraseplus.mixin;


import com.github.wolfgenerals.petphraseplus.Util;
import com.github.wolfgenerals.petphraseplus.config.ConfigOption;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {

    protected MinecraftClient client = MinecraftClient.getInstance();


    @Inject(method = "sendMessage(Ljava/lang/String;Z)Z", at = @At("HEAD"), cancellable = true)
    public void sendMessage(String chatText, boolean addToHistory, CallbackInfoReturnable<Boolean> cir) {
        if (!chatText.isEmpty() && ConfigOption.enabled && chatText.charAt(0) != '/') {
            this.client.inGameHud.getChatHud().addToMessageHistory(chatText);
            assert this.client.player != null;
            this.client.player.networkHandler.sendChatMessage(Util.petphraseplus(chatText));
            cir.setReturnValue(true);
        }
    }
}

