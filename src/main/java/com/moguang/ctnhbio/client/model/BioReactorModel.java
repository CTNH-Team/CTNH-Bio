package com.moguang.ctnhbio.client.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import com.moguang.ctnhbio.CTNHBio;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class BioReactorModel extends GeoModel<GeoAnimatable> {

    public static final ResourceLocation MODEL = CTNHBio.id("geo/entity/bio_reactor.geo.json");
    protected static final ResourceLocation TEXTURE = CTNHBio.id("textures/entity/bioreactor.png");
    protected static final ResourceLocation ANIMATION = CTNHBio.id("animations/entity/bio_reactor.animation.json");

    @Override
    public ResourceLocation getModelResource(GeoAnimatable animatable) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(GeoAnimatable animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(GeoAnimatable animatable) {
        return ANIMATION;
    }

    @Override
    public RenderType getRenderType(GeoAnimatable animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(texture);
    }
}
