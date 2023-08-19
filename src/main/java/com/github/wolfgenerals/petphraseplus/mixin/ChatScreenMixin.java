package com.github.wolfgenerals.petphraseplus.mixin;


import com.github.wolfgenerals.petphraseplus.ModifierKt;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {

    @Unique
    protected MinecraftClient client = MinecraftClient.getInstance();


    @Inject(method = "sendMessage(Ljava/lang/String;Z)Z", at = @At("HEAD"), cancellable = true)
    public void sendMessage(String chatText, boolean addToHistory, CallbackInfoReturnable<Boolean> cir) {
        if (ModifierKt.needModify(chatText)) {
            if (addToHistory) this.client.inGameHud.getChatHud().addToMessageHistory(chatText);
            this.client.player.networkHandler.sendChatMessage(ModifierKt.modifyMessage(chatText));
            cir.setReturnValue(true);
        }
    }


    //Mojang原本的方法
    @SuppressWarnings("All")
    public boolean sendMessage(String chatText, boolean addToHistory) {
        chatText = this.normalize(chatText);
        if (chatText.isEmpty()) {
            return true;
        } else {
            if (addToHistory) {
                this.client.inGameHud.getChatHud().addToMessageHistory(chatText);
            }

            if (chatText.startsWith("/")) {
                this.client.player.networkHandler.sendChatCommand(chatText.substring(1));
            } else {
                this.client.player.networkHandler.sendChatMessage(chatText);
            }

            return true;
        }
    }

    @Shadow
    public abstract String normalize(String chatText);
}

