package com.felpslipe.bbb;

import com.felpslipe.bbb.config.ConfigManager;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
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
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommands.literal("bbb")
					.executes(context -> {
						context.getSource().sendFeedback(Component.literal("§2BBB commands:"));
						context.getSource().sendFeedback(Component.literal(" reload - reloads config"));
						context.getSource().sendFeedback(Component.literal(" open - opens the config file"));
						return 1;
					})
					.then(ClientCommands.literal("reload")
							.executes(context -> {
								ConfigManager.load();
								return 1;
							}))
					.then(ClientCommands.literal("open")
							.executes(context -> {
									Util.getPlatform().openUri(ConfigManager.CONFIG_PATH.toUri());
								return 1;
							}))
			);
		});
	}
}