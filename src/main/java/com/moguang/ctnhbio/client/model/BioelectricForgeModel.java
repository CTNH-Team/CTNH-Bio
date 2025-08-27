package com.moguang.ctnhbio.client.model;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.CBValues;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class BioelectricForgeModel extends GeoModel<GeoAnimatable> {

	public static final ResourceLocation MODEL = CTNHBio.id("geo/entity/bioelectric_forge.geo.json");
	protected static final ResourceLocation TEXTURE = CTNHBio.id("textures/entity/bioelectric_forge.png");

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