package com.github.wolfgenerals.petphraseplus;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class PetPhrasePlusClient implements ClientModInitializer {

    @Override
    public  void onInitializeClient() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
    }
}