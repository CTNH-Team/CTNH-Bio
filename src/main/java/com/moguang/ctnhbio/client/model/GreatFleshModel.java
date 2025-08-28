package com.moguang.ctnhbio.client.model;

import com.github.elenterius.biomancy.BiomancyMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class GreatFleshModel extends GeoModel<GeoAnimatable> {
    protected static final ResourceLocation BASE_TEXTURE = BiomancyMod.createRL("textures/entity/mob/flesh_blob/flesh_blob_neutral.png");


    public ResourceLocation getModelResource(GeoAnimatable animatable) {
        return BiomancyMod.createRL("geo/entity/mob/flesh_blob.geo.json");
    }

    public ResourceLocation getTextureResource(GeoAnimatable animatable) {
        return BASE_TEXTURE;
    }

    public ResourceLocation getAnimationResource(GeoAnimatable animatable) {
        return BiomancyMod.createRL("animations/entity/mob/flesh_blob.animation.json");
    }
}
