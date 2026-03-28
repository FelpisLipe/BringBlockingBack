package com.felpslipe.bbb;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BringBlockingBack implements ClientModInitializer {
	public static final String MOD_ID = "bring-blocking-back";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("Bringing blocking back..");
	}
}