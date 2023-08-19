package com.github.wolfgenerals.petphraseplus.mixin

import com.github.wolfgenerals.petphraseplus.config.config
import com.github.wolfgenerals.petphraseplus.editMessage
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.ChatScreen
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

@Mixin(ChatScreen::class)
abstract class ChatScreenMixin {
    protected var client = MinecraftClient.getInstance()
    @Inject(method = ["sendMessage(Ljava/lang/String;Z)Z"], at = [At("HEAD")], cancellable = true)
    fun sendMessage(chatText: String, addToHistory: Boolean, cir: CallbackInfoReturnable<Boolean?>) {
        if (!chatText.isEmpty() && config.enabled && chatText[0] != '/') {
            client.inGameHud.chatHud.addToMessageHistory(chatText)
            assert(client.player != null)
            client.player!!.networkHandler.sendChatMessage(editMessage(chatText))
            cir.returnValue = true
        }
    }
}
