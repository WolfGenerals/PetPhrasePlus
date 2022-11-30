package com.github.wolfgenerals.petphraseplus.config;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.MinecraftClient;

public class Command {
    private static boolean needOpen = false;

    public static void register() {
        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("petphraseplus").executes(context -> {
                            needOpen = true;
                            return 1;
                        }
                )
        );
    }

    public static void openScreen(MinecraftClient client) {
        if (needOpen) {
            ConfigSL.loadConfig();
            MinecraftClient.getInstance().setScreen(ConfigScreen.configScreen(client.currentScreen));
            needOpen = false;
        }
    }
}
