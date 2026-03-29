package com.felpslipe.bbb.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

public class Utils {
    public static void firstPersonSwordBlock(PoseStack stack) {
        stack.translate(-0.15f, 0.16f, 0.15f);
        stack.mulPose(Axis.YP.rotationDegrees(-18.0f));
        stack.mulPose(Axis.ZP.rotationDegrees(82.0f));
        stack.mulPose(Axis.YP.rotationDegrees(112.0f));
    }

    public static void swordBlockThirdPerson(PoseStack stack) {
        stack.translate(-0.675F, 0f, 0f);
        stack.mulPose(Axis.YP.rotationDegrees(-90.0F));
    }
}
