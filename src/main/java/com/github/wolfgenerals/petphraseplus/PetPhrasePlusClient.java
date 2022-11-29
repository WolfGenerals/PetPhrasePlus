package com.github.wolfgenerals.petphraseplus;

import com.github.wolfgenerals.petphraseplus.config.Command;
import com.github.wolfgenerals.petphraseplus.config.ConfigSL;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.io.IOException;

@Environment(EnvType.CLIENT)
public class PetPhrasePlusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        try {
            ConfigSL.newFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ConfigSL.loadConfig();
        Command.register();
        ClientTickEvents.END_CLIENT_TICK.register(Command::openScreen);
    }


}