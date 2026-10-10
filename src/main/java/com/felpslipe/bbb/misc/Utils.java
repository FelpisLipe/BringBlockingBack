package com.felpslipe.bbb.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.joml.Matrix4f;

public class Utils {
    public static void firstPersonSwordBlock(PoseStack stack) {
        stack.translate(-0.15f, 0.16f, 0.15f);
        stack.mulPose(new Matrix4f().rotation(Axis.YP.rotationDegrees(-18.0f)));
        stack.mulPose(new Matrix4f().rotation(Axis.ZP.rotationDegrees(82.0f)));
        stack.mulPose(new Matrix4f().rotation(Axis.YP.rotationDegrees(112.0f)));
    }

    public static void swordBlockThirdPerson(PoseStack stack) {
        stack.mulPose(new Matrix4f().rotation(Axis.YP.rotationDegrees(-90.0F)));
        stack.mulPose(new Matrix4f().rotation(Axis.ZP.rotationDegrees(-40.0F)));
        stack.mulPose(new Matrix4f().rotation(Axis.XP.rotationDegrees(51.0F)));
        stack.mulPose(new Matrix4f().rotation(Axis.ZP.rotationDegrees(180.0f)));
        stack.mulPose(new Matrix4f().rotation(Axis.XP.rotationDegrees(197.2f)));
        stack.translate(-0.22f, 0.13f, -0.22f);
    }

    public static String getSkyBlockId(ItemStack stack) {
        if (stack != null && !stack.isEmpty()) {
            CustomData data = stack.get(DataComponents.CUSTOM_DATA);
            if (data != null) {
                return data.copyTag().getString("id").orElse("");
            }
        }
        return "";
    }
}
