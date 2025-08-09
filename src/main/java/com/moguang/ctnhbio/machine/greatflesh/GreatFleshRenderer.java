package com.moguang.ctnhbio.machine.greatflesh;

import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GreatFleshRenderer extends GeoBlockRenderer<LivingMetaMachineBlockEntity> {

    public GreatFleshRenderer(BlockEntityRendererProvider.Context context) {
        super(new GreatFleshBlockEntityModel());
    }
}
