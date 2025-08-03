package com.moguang.ctnhbio.machine.greatflesh;

import com.github.elenterius.biomancy.BiomancyMod;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GreatFleshBlockEntityModel extends GeoModel<GreatFleshBlockEntity> {
    protected static final ResourceLocation BASE_TEXTURE = BiomancyMod.createRL("textures/entity/mob/flesh_blob/flesh_blob_neutral.png");


    public ResourceLocation getModelResource(GreatFleshBlockEntity fleshBlob) {
        return BiomancyMod.createRL("geo/entity/mob/flesh_blob.geo.json");
    }

    public ResourceLocation getTextureResource(GreatFleshBlockEntity fleshBlob) {
        return BASE_TEXTURE;
    }

    public ResourceLocation getAnimationResource(GreatFleshBlockEntity fleshBlob) {
        return BiomancyMod.createRL("animations/entity/mob/flesh_blob.animation.json");
    }
}
