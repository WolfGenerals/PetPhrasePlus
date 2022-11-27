package com.github.wolfgenerals.petphraseplus.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConfigSL {
    public static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("petphraseplus.properties");

    public static void newFile() throws IOException {
        if (!Files.exists(PATH)) {
            new File(PATH.toString()).createNewFile();
        }
    }

    public static void loadConfig () {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ConfigOption.enabled = properties.getProperty("enable", "true").equalsIgnoreCase("true");
        ConfigOption.mark = properties.getProperty("mark", "&");
        ConfigOption.endInner = properties.getProperty("endInner", "");
        ConfigOption.endOuter = properties.getProperty("endOuter", "");
        int i = 0;
        while (properties.getProperty("replace" + i) != null) {
            ConfigOption.replace.add(properties.getProperty("replace" + i));
        }
    }

    public static void saveConfig () throws IOException {
        Properties properties = new Properties();

        properties.setProperty("enable", String.valueOf(ConfigOption.enabled));
        properties.setProperty("mark",ConfigOption.mark);
        properties.setProperty("endInner",ConfigOption.endInner);
        properties.setProperty("endOuter",ConfigOption.endOuter);

        for (int i = 0; i < ConfigOption.replace.size(); i++) {
            properties.setProperty("replace"+i,ConfigOption.replace.get(i));
        }
        properties.store(new FileOutputStream(PATH.toString()),"PetPhrasePlus Config");
    }
}