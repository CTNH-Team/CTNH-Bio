package com.moguang.ctnhbio.machine.bioelectricforge;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BioelectricForgeBlockEntityRenderer extends GeoBlockRenderer<BioelectricForgeBlockEntity> {


    public BioelectricForgeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new BioelectricForgeEntityModel());
    }

}
