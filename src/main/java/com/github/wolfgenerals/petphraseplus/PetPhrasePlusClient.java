package com.github.wolfgenerals.petphraseplus;

import com.github.wolfgenerals.petphraseplus.config.ConfigSL;
import com.github.wolfgenerals.petphraseplus.config.ConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;

import java.io.IOException;

@Environment(EnvType.CLIENT)
public class PetPhrasePlusClient implements ClientModInitializer {

    @Override
    public  void onInitializeClient() {
        try {
            ConfigSL.newFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ConfigSL.loadConfig();
        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("petphraseplus").executes(context -> {
                    context.getSource().getClient().setScreen(ConfigScreen.configScreen(context.getSource().getClient().currentScreen));
                    return 1;
                        }
                )
        );
    }


}