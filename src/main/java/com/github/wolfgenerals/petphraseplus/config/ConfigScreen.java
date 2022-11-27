package com.github.wolfgenerals.petphraseplus.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

import java.io.IOException;
import java.util.ArrayList;

public class ConfigScreen {
    public static Screen configScreen (Screen parent){
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TranslatableText("petphraseplus.config.title"));

        builder.setSavingRunnable(()->{
            try {
                ConfigSL.saveConfig();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("petphraseplus.config.category"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder
                .startBooleanToggle(new TranslatableText("petphraseplus.config.enable"),ConfigOption.enabled)
                .setDefaultValue(true)
                .setTooltip(new TranslatableText("petphraseplus.config.enable.tooltip"))
                .setSaveConsumer(newValue -> ConfigOption.enabled = newValue)
                .build()
                );
        general.addEntry(entryBuilder
                .startStrField(new TranslatableText("petphraseplus.config.mark"), ConfigOption.mark)
                .setDefaultValue("")
                .setTooltip(new TranslatableText("petphraseplus.config.mark.tooltip1"))
                .setTooltip(new TranslatableText("petphraseplus.config.mark.tooltip2"))
                .setSaveConsumer(newValue -> ConfigOption.mark = newValue)
                .build()
        );
        general.addEntry(entryBuilder
                .startStrList(new TranslatableText("petphraseplus.config.replace"),ConfigOption.replace)
                .setDefaultValue(new ArrayList<>())
                .setTooltip(new TranslatableText("petphraseplus.config.replace.tooltip"))
                .setSaveConsumer(newValue -> ConfigOption.replace = newValue)
                .build()
        );
        general.addEntry(entryBuilder
                .startStrField(new TranslatableText("petphraseplus.config.endInner"),ConfigOption.endInner)
                .setDefaultValue("")
                .setTooltip(new TranslatableText("petphraseplus.config.endInner.tooltip"))
                .setSaveConsumer(newValue -> ConfigOption.endInner = newValue)
                .build()
        );
        general.addEntry(entryBuilder
                .startStrField(new TranslatableText("petphraseplus.config.endOuter"),ConfigOption.endOuter)
                .setDefaultValue("")
                .setTooltip(new TranslatableText("petphraseplus.config.endOuter.tooltip"))
                .setSaveConsumer(newValue -> ConfigOption.endOuter = newValue)
                .build()
        );
      return builder.build();

    }


}