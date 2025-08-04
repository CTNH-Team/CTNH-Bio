package com.moguang.ctnhbio.machine.bioreactor;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.CBValues;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.event.RenderTooltipEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import static com.moguang.ctnhbio.api.CBValues.VoltageColor;


public class BioReactorBlockEntityRenderer extends GeoBlockRenderer<BioReactorBlockEntity> {
    public BioReactorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new BioReactorBlockEntityModel());
    }

    @Override
    public RenderType getRenderType(BioReactorBlockEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));

    }

    @Override
    public void renderRecursively(PoseStack poseStack, BioReactorBlockEntity animatable, GeoBone bone, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("colorable")) {
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
//    public int getPackedOverlay(BioReactorBlockEntity animatable, float u, float partialTick) {
//
//        return OverlayTexture.pack(OverlayTexture.u(u),
//                OverlayTexture.v(entity.hurtTime > 0 || entity.deathTime > 0));
//    }
}
