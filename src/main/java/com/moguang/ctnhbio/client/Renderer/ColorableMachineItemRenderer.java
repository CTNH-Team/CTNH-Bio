package com.moguang.ctnhbio.client.Renderer;

import com.moguang.ctnhbio.api.CBValues;
import com.moguang.ctnhbio.api.item.LivingMetaMachineItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ColorableMachineItemRenderer extends GeoItemRenderer<LivingMetaMachineItem> {


    public ColorableMachineItemRenderer(GeoModel<?> model) {
        super((GeoModel<LivingMetaMachineItem>)model);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        if(transformType == ItemDisplayContext.GUI)
        {
            poseStack.translate(0.0, -0.5, 0.0);
        }
        super.renderByItem(stack, transformType, poseStack, bufferSource, packedLight, packedOverlay);
        poseStack.popPose();
    }


    @Override
    public void renderRecursively(PoseStack poseStack, LivingMetaMachineItem animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.getName().startsWith("colorable")) {
            Color vc =  CBValues.VoltageColor.values()[animatable.getDefinition().getTier()].LIGHT;
            red = vc.getRedFloat();
            green = vc.getGreenFloat();
            blue = vc.getBlueFloat();
        }
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
