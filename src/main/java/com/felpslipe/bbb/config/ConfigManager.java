package com.felpslipe.bbb.config;

import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.felpslipe.bbb.BringBlockingBack.LOGGER;
import static com.felpslipe.bbb.BringBlockingBack.client;

public class ConfigManager {
    public static final Gson GSON = new Gson();
    public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("bringblockingback.json");
    public static Config config = new Config();

    public static void load() {
        if(!Files.exists(CONFIG_PATH)) {
            try {
                Files.createDirectories(CONFIG_PATH.getParent());
                try(Writer writer = new FileWriter(CONFIG_PATH.toFile())) {
                    GSON.toJson(config, writer);

                }
            }
            catch(Exception e) {
                LOGGER.error("[BBB] Failed to write config", e);
            }
        }
        try(Reader reader = new FileReader(CONFIG_PATH.toFile())) {
            config = GSON.fromJson(reader, Config.class);
            LOGGER.info("[BBB] Config loaded successfully");
            if(client.player != null) {
                client.player.sendSystemMessage(Component.literal("[BBB] Config Reloaded"));
            }
        }
        catch(Exception e) {
            LOGGER.error("[BBB] Failed to load config", e);
        }

    }
}
