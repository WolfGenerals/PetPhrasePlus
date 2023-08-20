package com.github.wolfgenerals.petphraseplus.config

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents

fun registerCommand() {
    var needOpen = false
    ClientCommandManager.DISPATCHER.register(
        ClientCommandManager
            .literal("petphraseplus")
            .executes {
                needOpen = true
                1
            }
    )
    ClientTickEvents.END_CLIENT_TICK.register {
        if (!needOpen) return@register
        openScreen(it)
        needOpen = false
    }
}

