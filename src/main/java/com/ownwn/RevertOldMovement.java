package com.ownwn;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;

public class RevertOldMovement implements ModInitializer {
    public static final String MOD_ID = "revert-old-movement";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // true is default mc behavior, but feels worse
    public static boolean shouldCancelSprint = true;

    private final File configFile = FabricLoader.getInstance().getConfigDir().resolve("revert-old-movement.json").toFile();
    private final String configKey = "Cancel Sprint on attack";

    @Override
    public void onInitialize() {
        JsonObject config = getConfigJson();
        if (config == null) {
            LOGGER.warn("Failed to read json from config file at {}", configFile.getPath());
            return;
        }

        try {
            shouldCancelSprint = config.get(configKey).getAsBoolean();
        } catch (Exception e) {
            LOGGER.warn("Failed to read json from config file at {}", configFile.getPath());
        }
    }

    public JsonObject getConfigJson() {
        if (!configFile.exists()) {
            createDefaultConfig();
        }

        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(configFile));
            return gson.fromJson(reader, JsonObject.class);
        } catch (FileNotFoundException ignored) {
        }
        return null;
    }

    public void createDefaultConfig() {
        try {
            if (!configFile.createNewFile()) {
                throw new IOException();
            }
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(configFile);

            gson.toJson(Map.of(configKey, true), writer);
            writer.close();


        } catch (IOException | JsonIOException e) {
            LOGGER.warn("Failed to create config file at {}", configFile.getPath());
        }
    }
}