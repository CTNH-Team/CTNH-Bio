package com.moguang.ctnhbio.machine.bioelectricforge;

import com.github.elenterius.biomancy.BiomancyMod;
import com.moguang.ctnhbio.CTNHBio;
import net.minecraft.resources.ResourceLocation;

import software.bernie.geckolib.model.GeoModel;


public class BioelectricForgeEntityModel extends GeoModel<BioelectricForgeBlockEntity> {

    public static final ResourceLocation MODEL = CTNHBio.id("geo/entity/bioelectric_forge.geo.json");
    protected static final ResourceLocation TEXTURE = CTNHBio.id("textures/entity/bioelectric_forge.png");


    @Override
    public ResourceLocation getModelResource(BioelectricForgeBlockEntity bioelectricForgeBlockEntity) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(BioelectricForgeBlockEntity bioelectricForgeBlockEntity) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(BioelectricForgeBlockEntity bioelectricForgeBlockEntity) {
        return BiomancyMod.createRL("animations/entity/mob/flesh_blob.animation.json");
    }

}
