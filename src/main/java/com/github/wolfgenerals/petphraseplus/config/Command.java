package com.github.wolfgenerals.petphraseplus.config;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

@Environment(EnvType.CLIENT)
public class Command {
    private static boolean needOpen = false;
    public static void register (CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("petphraseplus").executes(context -> {
            needOpen = true;
//            MinecraftClient.getInstance().setScreen(ConfigScreen.configScreen(MinecraftClient.getInstance().currentScreen));
            return 1;
        }
        ));
    }
    public static void openScreen (MinecraftClient client) {
        if (needOpen) {
            MinecraftClient.getInstance().setScreen(ConfigScreen.configScreen(client.currentScreen));
            needOpen = false;
        }
    }
}
