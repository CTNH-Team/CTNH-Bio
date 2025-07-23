package com.moguang.ctnhbio.machine.greatflesh;

import com.github.elenterius.biomancy.BiomancyMod;
import com.github.elenterius.biomancy.client.render.entity.mob.fleshblob.FleshBlobModel;
import com.github.elenterius.biomancy.entity.mob.PrimordialFleshkin;
import com.github.elenterius.biomancy.entity.mob.fleshblob.EaterFleshBlob;
import com.github.elenterius.biomancy.entity.mob.fleshblob.PrimordialHangryEaterFleshBlob;
import com.github.elenterius.biomancy.init.client.ModRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import java.util.Locale;

public class GreatFleshModel<T extends GreatFleshEntity> extends GeoModel<T> {
    protected static final ResourceLocation BASE_TEXTURE = BiomancyMod.createRL("textures/entity/mob/flesh_blob/primordial_flesh_blob_neutral.png");
    protected static final ResourceLocation HUNGRY_TEXTURE = BiomancyMod.createRL("textures/entity/mob/flesh_blob/primordial_flesh_blob_hostile.png");

    public GreatFleshModel() {
    }

    public ResourceLocation getModelResource(T fleshBlob) {
        return BiomancyMod.createRL("geo/entity/mob/flesh_blob.geo.json");
    }

    public ResourceLocation getTextureResource(T fleshBlob) {
        return BASE_TEXTURE;
    }

    public ResourceLocation getAnimationResource(T fleshBlob) {
        return BiomancyMod.createRL("animations/entity/mob/flesh_blob.animation.json");
    }

    public RenderType getRenderType(T fleshBlob, ResourceLocation texture) {
            return super.getRenderType(fleshBlob, texture);
    }
}
