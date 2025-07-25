package com.moguang.ctnhbio.machine.greatflesh;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GreatFleshRenderer extends GeoEntityRenderer<GreatFleshEntity> {
    public GreatFleshRenderer(EntityRendererProvider.Context context) {
        super(context, new GreatFleshModel<>());
    }
}
