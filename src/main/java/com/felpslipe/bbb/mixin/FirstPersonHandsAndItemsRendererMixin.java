package com.felpslipe.bbb.mixin;

import com.felpslipe.bbb.config.ConfigHelper;
import com.felpslipe.bbb.misc.Utils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.FirstPersonHandsAndItemsRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.felpslipe.bbb.BringBlockingBack.client;

@Mixin(FirstPersonHandsAndItemsRenderer.class)
public abstract class FirstPersonHandsAndItemsRendererMixin {

    @Shadow
    protected abstract void swingArm(float animation, PoseStack poseStack, int invert, HumanoidArm arm);

    @Shadow
    protected abstract void applyItemArmAttackTransform(PoseStack poseStack, HumanoidArm arm, float attackValue);

    @Inject(method = "submitArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/item/ItemStackRenderState;submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;III)V"))
    private void renderItem(PlayerRenderState playerState, FirstPersonHandsAndItemsRenderState state, float partialTicks, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, CallbackInfo ci) {
        LivingEntity player = client.player;
        if (hand == InteractionHand.MAIN_HAND && player != null && !itemStack.isEmpty() && player.getOffhandItem().isEmpty() && ConfigHelper.canBlock(player.getMainHandItem()) && client.options.keyUse.isDown()) {
            Utils.firstPersonSwordBlock(poseStack);
        }
    }
    
    @Redirect(method = "submitArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/FirstPersonHandsAndItemsRenderer;swingArm(FLcom/mojang/blaze3d/vertex/PoseStack;ILnet/minecraft/world/entity/HumanoidArm;)V"))
    private void submitArmWithItem(FirstPersonHandsAndItemsRenderer instance, float animation, PoseStack poseStack, int invert, HumanoidArm arm) {
        LivingEntity player = client.player;
        if(player != null) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (player.getOffhandItem().isEmpty() && ConfigHelper.canBlock(mainHandItem) && client.options.keyUse.isDown()) {
                applyItemArmAttackTransform(poseStack, arm, animation);
            } else {
                swingArm(animation, poseStack, invert, arm);
            }
        }
    }
}
