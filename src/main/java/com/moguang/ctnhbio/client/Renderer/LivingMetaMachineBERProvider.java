package com.moguang.ctnhbio.client.Renderer;

import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;

import com.moguang.ctnhbio.client.model.CBModels;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LivingMetaMachineBERProvider {

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderer(BlockEntityType<BlockEntity> beType, String name, boolean transparent){
        var typed = (BlockEntityType<LivingMetaMachineBlockEntity>) (BlockEntityType<?>)beType;
        BlockEntityRenderers.register(typed, ctx -> new ColorableMachineBlockEntityRenderer(CBModels.MODELS.get(name), transparent));
    }
}

