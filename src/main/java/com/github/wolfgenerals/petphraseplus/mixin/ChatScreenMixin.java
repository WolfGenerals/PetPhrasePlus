package com.github.wolfgenerals.petphraseplus.mixin;


import com.github.wolfgenerals.petphraseplus.ModifierKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {

    @Unique
    protected final MinecraftClient client = MinecraftClient.getInstance();


    @Inject(method = "sendMessage(Ljava/lang/String;Z)Z", at = @At("HEAD"), cancellable = true)
    public void sendMessage(String chatText, boolean addToHistory, CallbackInfoReturnable<Boolean> cir) {
        if (ModifierKt.needModify(chatText)) {
            if (addToHistory) this.client.inGameHud.getChatHud().addToMessageHistory(chatText);
            this.client.player.networkHandler.sendChatMessage(ModifierKt.modifyMessage(chatText));
            cir.setReturnValue(true);
        }
    }
}

