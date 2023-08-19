package com.github.wolfgenerals.petphraseplus

import com.github.wolfgenerals.petphraseplus.config.*
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Environment(EnvType.CLIENT)
fun init() {
    configFile.readConfig()?.also { config = it }
    registerCommand()
}
