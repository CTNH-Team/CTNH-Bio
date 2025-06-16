package com.moguang.ctnhbio.client.Renderer;

import com.lowdragmc.lowdraglib.client.renderer.IRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.block.state.BlockState;

public class InvisibleRenderer implements IRenderer {
    public static final InvisibleRenderer INSTANCE = new InvisibleRenderer();


    @Override
    public boolean isGui3d() {
        return false;
    }


}

