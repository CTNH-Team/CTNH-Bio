package com.moguang.ctnhbio.client.Renderer;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature;
import com.gregtechceu.gtceu.client.model.machine.IMachineRendererModel;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRender;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderType;
import com.lowdragmc.lowdraglib.client.renderer.IRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.block.state.BlockState;

public class InvisibleRenderer extends DynamicRender<IMachineFeature, InvisibleRenderer> {
    public static final InvisibleRenderer INSTANCE = new InvisibleRenderer();


    @Override
    public DynamicRenderType<IMachineFeature, InvisibleRenderer> getType() {
        return null;
    }

    @Override
    public MachineDefinition getDefinition() {
        return this.getDefinition();
    }

    @Override
    public void render(IMachineFeature feature, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {

    }
}

