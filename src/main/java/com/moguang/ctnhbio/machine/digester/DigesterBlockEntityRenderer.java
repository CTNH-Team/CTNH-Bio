package com.moguang.ctnhbio.machine.digester;

import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import static com.moguang.ctnhbio.api.CBValues.VoltageColor;


public class DigesterBlockEntityRenderer extends GeoBlockRenderer<LivingMetaMachineBlockEntity> {
    public DigesterBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DigesterBlockEntityModel2());
    }

    @Override
    public void renderRecursively(PoseStack poseStack, LivingMetaMachineBlockEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.getName().startsWith("colorable")) {
            if(animatable.getMetaMachine() instanceof BasicLivingMachine livingMachine)
            {
                Color vc =  VoltageColor.values()[livingMachine.getTier()].LIGHT;
                red = vc.getRedFloat();
                green = vc.getGreenFloat();
                blue = vc.getBlueFloat();
            }
        }
        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    //    @Override
//    public int getPackedOverlay(LivingMetaMachineBlockEntity animatable, float u, float partialTick) {
//
//        return OverlayTexture.pack(OverlayTexture.u(u),
//                OverlayTexture.v(entity.hurtTime > 0 || entity.deathTime > 0));
//    }
}
