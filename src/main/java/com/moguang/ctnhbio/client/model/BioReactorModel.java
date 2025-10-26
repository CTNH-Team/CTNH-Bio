package com.moguang.ctnhbio.client.model;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.CBValues;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class BioReactorModel extends GeoModel<GeoAnimatable> {

	public static final ResourceLocation MODEL = CTNHBio.id("geo/entity/bio_reactor.geo.json");
	protected static final ResourceLocation TEXTURE = CTNHBio.id("textures/entity/bioreactor.png");

	@Override
	public ResourceLocation getModelResource(GeoAnimatable animatable)
	{
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureResource(GeoAnimatable animatable)
	{
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationResource(GeoAnimatable animatable) {
		return CBValues.EMPTY_ANIMATION;
	}


}