package com.moguang.ctnhbio.client.Renderer;

import com.moguang.ctnhbio.api.CBValues;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ColorableMachineBlockEntityRenderer extends GeoBlockRenderer<LivingMetaMachineBlockEntity> {
    private final boolean translucent;

    public ColorableMachineBlockEntityRenderer(GeoModel<?> model) {
        super((GeoModel<LivingMetaMachineBlockEntity>)model);
        this.translucent = false;
    }


    public ColorableMachineBlockEntityRenderer(GeoModel<?> model, boolean translucent) {
        super((GeoModel<LivingMetaMachineBlockEntity>)model);
        this.translucent = translucent;
    }

    @Override
    public RenderType getRenderType(LivingMetaMachineBlockEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        if(translucent) return RenderType.entityTranslucent(getTextureLocation(animatable));
        else return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void renderRecursively(PoseStack poseStack, LivingMetaMachineBlockEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.getName().startsWith("colorable")) {
            if(animatable.getMetaMachine() instanceof BasicLivingMachine livingMachine)
            {
                Color vc =  CBValues.VoltageColor.values()[livingMachine.getTier()].LIGHT;
                red = vc.getRedFloat();
                green = vc.getGreenFloat();
                blue = vc.getBlueFloat();
            }
        }
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
