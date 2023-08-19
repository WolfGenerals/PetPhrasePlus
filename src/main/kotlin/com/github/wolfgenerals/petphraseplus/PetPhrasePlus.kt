package com.github.wolfgenerals.petphraseplus

import com.github.wolfgenerals.petphraseplus.config.*
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient

@Environment(EnvType.CLIENT)
fun init() {
    configFile.readConfig()?.also { config = it }

    registerCommand()
}
