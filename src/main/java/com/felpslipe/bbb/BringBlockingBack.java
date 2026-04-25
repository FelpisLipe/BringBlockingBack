package com.felpslipe.bbb;

import com.felpslipe.bbb.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BringBlockingBack implements ClientModInitializer {
	public static final String MOD_ID = "bring-blocking-back";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static Minecraft client;
	@Override
	public void onInitializeClient() {
		client = Minecraft.getInstance();
		LOGGER.info("[BBB] Bringing blocking back..");
		ConfigManager.load();
	}
}