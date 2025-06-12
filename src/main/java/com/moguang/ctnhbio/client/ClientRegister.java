package com.moguang.ctnhbio.client;

import com.lowdragmc.lowdraglib.client.renderer.ATESRRendererProvider;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.client.Renderer.BasicMobRenderer;
import com.moguang.ctnhbio.common.entity.BasicMobEntity;
import com.moguang.ctnhbio.registry.CBEntities;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegister {
    @Deprecated
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

    }
    @Deprecated
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(CBEntities.BASIC_MOB.get(),
                BasicMobRenderer::new
        );
    }
}
