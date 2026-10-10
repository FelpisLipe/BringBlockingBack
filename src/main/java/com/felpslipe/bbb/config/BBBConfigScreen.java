package com.felpslipe.bbb.config;

import com.mojang.blaze3d.Blaze3D;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
public class BBBConfigScreen extends Screen {
    private final Screen parent;
    public BBBConfigScreen(Screen parent) {
        super(Component.empty());
        this.parent = parent;
    }

    @Override
    protected void init() {
        Blaze3D.openUri(ConfigManager.CONFIG_PATH.toUri());
        minecraft.setScreenAndShow(parent);
    }
}
