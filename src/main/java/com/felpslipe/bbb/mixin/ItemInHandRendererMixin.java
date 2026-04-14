package com.felpslipe.bbb.mixin;

import com.felpslipe.bbb.misc.Utils;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.felpslipe.bbb.BringBlockingBack.client;

@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {

    @Shadow
    protected abstract void swingArm(float swingProgress, PoseStack matrices, int armX, HumanoidArm arm);

    @Shadow
    protected abstract void applyItemArmAttackTransform(PoseStack matrices, HumanoidArm arm, float swingProgress);

    @Inject(method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext; Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector; I)V", at = @At("HEAD"))
    private void renderItem(LivingEntity livingEntity, ItemStack itemStack, ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, CallbackInfo ci) {
        if(!itemStack.isEmpty() && livingEntity.getOffhandItem().isEmpty() && livingEntity.getMainHandItem().is(ItemTags.SWORDS) && client.options.keyUse.isDown()) {
            Utils.firstPersonSwordBlock(poseStack);
        }

    }
    
    @Redirect(method = "renderArmWithItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemInHandRenderer;swingArm(FLcom/mojang/blaze3d/vertex/PoseStack;ILnet/minecraft/world/entity/HumanoidArm;)V"))
    private void renderArmWithItem(ItemInHandRenderer instance, float f, PoseStack poseStack, int i, HumanoidArm humanoidArm) {
        LivingEntity player = client.player;
        if(player != null && player.getOffhandItem().isEmpty() && player.getMainHandItem().is(ItemTags.SWORDS) && client.options.keyUse.isDown()) {
            applyItemArmAttackTransform(poseStack, humanoidArm, f);
        }
        else {
            swingArm(f, poseStack, i, humanoidArm);
        }
    }
}
