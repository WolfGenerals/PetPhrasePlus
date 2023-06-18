package com.github.wolfgenerals.petphraseplus.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.io.IOException;
import java.util.ArrayList;

public class ConfigScreen {
    public static Screen configScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Text.translatable("petphraseplus.config.title"));

        builder.setSavingRunnable(() -> {
            try {
                ConfigSL.saveConfig();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("petphraseplus.config.category"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder
                .startBooleanToggle(Text.translatable("petphraseplus.config.enable"), ConfigOption.enabled)
                .setDefaultValue(true)
                .setTooltip(Text.translatable("petphraseplus.config.enable.tooltip"))
                .setSaveConsumer(newValue -> ConfigOption.enabled = newValue)
                .build()
        );
        general.addEntry(entryBuilder
                .startStrField(Text.translatable("petphraseplus.config.mark"), ConfigOption.mark)
                .setDefaultValue("")
                .setTooltip(Text.translatable("petphraseplus.config.mark.tooltip1"))
                .setTooltip(Text.translatable("petphraseplus.config.mark.tooltip2"))
                .setSaveConsumer(newValue -> ConfigOption.mark = newValue)
                .build()
        );
        general.addEntry(entryBuilder
                .startStrList(Text.translatable("petphraseplus.config.replace"), ConfigOption.replace)
                .setDefaultValue(new ArrayList<>())
                .setTooltip(Text.translatable("petphraseplus.config.replace.tooltip"))
                .setSaveConsumer(newValue -> ConfigOption.replace = newValue)
                .build()
        );
        general.addEntry(entryBuilder
                .startStrField(Text.translatable("petphraseplus.config.endInner"), ConfigOption.endInner)
                .setDefaultValue("")
                .setTooltip(Text.translatable("petphraseplus.config.endInner.tooltip"))
                .setSaveConsumer(newValue -> ConfigOption.endInner = newValue)
                .build()
        );
        general.addEntry(entryBuilder
                .startStrField(Text.translatable("petphraseplus.config.endOuter"), ConfigOption.endOuter)
                .setDefaultValue("")
                .setTooltip(Text.translatable("petphraseplus.config.endOuter.tooltip"))
                .setSaveConsumer(newValue -> ConfigOption.endOuter = newValue)
                .build()
        );
        return builder.build();

    }


}