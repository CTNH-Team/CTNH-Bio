package com.moguang.ctnhbio.machine.greatflesh;

import com.github.elenterius.biomancy.BiomancyMod;
import com.moguang.ctnhbio.api.CBValues;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GreatFleshBlockEntityModel extends GeoModel<LivingMetaMachineBlockEntity> {
    protected static final ResourceLocation BASE_TEXTURE = BiomancyMod.createRL("textures/entity/mob/flesh_blob/flesh_blob_neutral.png");


    public ResourceLocation getModelResource(LivingMetaMachineBlockEntity fleshBlob) {
        return BiomancyMod.createRL("geo/entity/mob/flesh_blob.geo.json");
    }

    public ResourceLocation getTextureResource(LivingMetaMachineBlockEntity fleshBlob) {
        return BASE_TEXTURE;
    }

    public ResourceLocation getAnimationResource(LivingMetaMachineBlockEntity fleshBlob) {
        return BiomancyMod.createRL("animations/entity/mob/flesh_blob.animation.json");
    }
}
