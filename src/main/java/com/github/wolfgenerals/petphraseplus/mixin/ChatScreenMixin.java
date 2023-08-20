package com.github.wolfgenerals.petphraseplus.mixin;


import com.github.wolfgenerals.petphraseplus.ModifierKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.ChatPreviewer;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {

    @Unique
    protected final MinecraftClient client = MinecraftClient.getInstance();
    @Shadow
    private ChatPreviewer chatPreviewer;


    @Inject(method = "sendMessage(Ljava/lang/String;Z)Z", at = @At("HEAD"), cancellable = true)
    public void sendMessage(String chatText, boolean addToHistory, CallbackInfoReturnable<Boolean> cir) {
        if (!ModifierKt.needModify(chatText)) return;
        if (addToHistory) this.client.inGameHud.getChatHud().addToMessageHistory(chatText);
        this.client.player.sendChatMessage(ModifierKt.modifyMessage(chatText), Util.map(this.chatPreviewer.tryConsumeResponse(chatText), ChatPreviewer.Response::previewText));
        cir.setReturnValue(true);
    }
}

