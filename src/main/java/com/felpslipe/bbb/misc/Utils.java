package com.felpslipe.bbb.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.joml.Matrix4f;

import static com.felpslipe.bbb.BringBlockingBack.client;

public class Utils {
    public static void firstPersonSwordBlock(PoseStack stack) {
        final float MULTIPLIER = isMainHandRight() ? 1f: -1f;

        stack.translate(-0.15f * MULTIPLIER, 0.16f, 0.15f);
        stack.mulPose(new Matrix4f().rotation(Axis.YP.rotationDegrees(-18.0f * MULTIPLIER)));
        stack.mulPose(new Matrix4f().rotation(Axis.ZP.rotationDegrees(82.0f * MULTIPLIER)));
        stack.mulPose(new Matrix4f().rotation(Axis.YP.rotationDegrees(112.0f * MULTIPLIER)));
    }

    public static void swordBlockThirdPerson(PoseStack stack) {
        final float MULTIPLIER = isMainHandRight() ? 1f: -1f;

        stack.mulPose(new Matrix4f().rotation(Axis.YP.rotationDegrees(-90.0F * MULTIPLIER)));
        stack.mulPose(new Matrix4f().rotation(Axis.ZP.rotationDegrees(-40.0F * MULTIPLIER)));
        stack.mulPose(new Matrix4f().rotation(Axis.XP.rotationDegrees(51.0F)));
        stack.mulPose(new Matrix4f().rotation(Axis.ZP.rotationDegrees(180.0f * MULTIPLIER)));
        stack.mulPose(new Matrix4f().rotation(Axis.XP.rotationDegrees(197.2f)));
        stack.translate(-0.22f * MULTIPLIER, 0.13f, -0.22f);
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

    public static boolean isMainHandRight() {
        if (client.player == null) return true;
        return client.player.getMainArm() == HumanoidArm.RIGHT;
    }
}
