package com.felpslipe.bbb.config;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ConfigHelper {
    public static boolean canBlock(ItemStack itemStack) {
        for(String blockable : ConfigManager.config.blockables) {
            if(blockable.startsWith("#")) {
                ResourceLocation id = ResourceLocation.parse(blockable.substring(1));
                TagKey<Item> tag = TagKey.create(Registries.ITEM, id);

                if(itemStack.is(tag)) return true;
            }
            else {
                ResourceLocation id = ResourceLocation.parse(blockable);
                Item item = BuiltInRegistries.ITEM.getValue(id);
                if(itemStack.is(item)) return true;
            }
        }
        return false;
    }
}
