package com.felpslipe.bbb.integration;

import com.felpslipe.bbb.config.BBBConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class BBBModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return BBBConfigScreen::new;
    }
}
