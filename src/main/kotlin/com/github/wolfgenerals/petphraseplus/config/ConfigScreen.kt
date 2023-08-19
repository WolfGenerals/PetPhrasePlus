package com.github.wolfgenerals.petphraseplus.config

import com.github.wolfgenerals.petphraseplus.i18n
import com.github.wolfgenerals.petphraseplus.replace
import me.shedaniel.clothconfig2.api.ConfigBuilder
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import java.io.IOException

fun openScreen(client: MinecraftClient) {
        client.setScreen(configScreen(client.currentScreen))
}

fun configScreen(parent: Screen?): Screen {
    var (enabled, start, endInner, endOuter, replace) = config
    val builder = ConfigBuilder
        .create()
        .setParentScreen(parent)
        .setTitle("petphraseplus.config.title".i18n)
        .setSavingRunnable {
            config = Config(enabled, start, endInner, endOuter, replace)
            config.write(configFile)
        }
    val general = builder.getOrCreateCategory("petphraseplus.config.category".i18n)
    val entryBuilder = builder.entryBuilder()
    general.addEntry(entryBuilder
        .startBooleanToggle("petphraseplus.config.enable".i18n, enabled)
        .setDefaultValue(true)
        .setTooltip("petphraseplus.config.enable.tooltip".i18n)
        .setSaveConsumer { newValue: Boolean? -> enabled = newValue!! }
        .build()
    )
    general.addEntry(entryBuilder
        .startStrField("petphraseplus.config.start".i18n, start)
        .setDefaultValue("")
        .setTooltip("petphraseplus.config.start.tooltip".i18n)
        .setSaveConsumer { newValue: String? -> start = newValue!! }
        .build()
    )
    general.addEntry(entryBuilder
        .startStrField("petphraseplus.config.endInner".i18n, endInner)
        .setDefaultValue("")
        .setTooltip("petphraseplus.config.endInner.tooltip".i18n)
        .setSaveConsumer { newValue: String? -> endInner = newValue!! }
        .build()
    )
    general.addEntry(entryBuilder
        .startStrField("petphraseplus.config.endOuter".i18n, endOuter)
        .setDefaultValue("")
        .setTooltip("petphraseplus.config.endOuter.tooltip".i18n)
        .setSaveConsumer { newValue: String? -> endOuter = newValue!! }
        .build()
    )
    general.addEntry(entryBuilder
        .startStrList("petphraseplus.config.replace".i18n, replace.map { it.toString() })
        .setDefaultValue(ArrayList())
        .setTooltip("petphraseplus.config.replace.tooltip".i18n)
        .setSaveConsumer { newValue: List<String> -> replace = newValue.mapNotNull { it.replace } }
        .build()
    )
    return builder.build()
}
