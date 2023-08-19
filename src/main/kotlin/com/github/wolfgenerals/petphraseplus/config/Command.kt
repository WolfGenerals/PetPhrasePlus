package com.github.wolfgenerals.petphraseplus.config

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient

fun registerCommand() {
    var needOpen = false
    ClientCommandRegistrationCallback.EVENT.register(ClientCommandRegistrationCallback { dispatcher, _ ->
        dispatcher.register(
            ClientCommandManager
                .literal("petphraseplus")
                .executes {
                    needOpen = true
                    1
                }
        )
    })
    ClientTickEvents.END_CLIENT_TICK.register {
        if (!needOpen) return@register
        openScreen(it)
        needOpen = false
    }
}

