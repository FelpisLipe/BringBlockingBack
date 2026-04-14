package com.felpslipe.bbb.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.felpslipe.bbb.BringBlockingBack.client;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends HumanoidRenderState> {

    @Shadow
    public ModelPart rightArm;

    @Inject(method = "setupAnim(Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;)V", at = @At("TAIL"))
    private void setupAnim(T humanoidRenderState, CallbackInfo ci) {
        LivingEntity player = client.player;
        if(player != null && player.getOffhandItem().isEmpty() && player.getMainHandItem().is(ItemTags.SWORDS) && client.options.keyUse.isDown()) {
            this.rightArm.xRot = -0.94F;
            //this.rightArm.yRot = -0.52F;
        }
    }


}
