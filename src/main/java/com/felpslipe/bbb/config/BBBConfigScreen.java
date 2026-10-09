package com.felpslipe.bbb.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
public class BBBConfigScreen extends Screen {
    private final Screen parent;
    public BBBConfigScreen(Screen parent) {
        super(Component.empty());
        this.parent = parent;
    }

    @Override
    protected void init() {
        Util.getPlatform().openUri(ConfigManager.CONFIG_PATH.toUri());
        minecraft.setScreenAndShow(parent);
    }
}
