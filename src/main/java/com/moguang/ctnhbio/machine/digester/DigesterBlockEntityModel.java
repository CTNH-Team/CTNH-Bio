package com.moguang.ctnhbio.machine.digester;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.CBValues;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DigesterBlockEntityModel extends GeoModel<DigesterBlockEntity> {

	public static final ResourceLocation MODEL = CTNHBio.id("geo/entity/digester.geo.json");
	protected static final ResourceLocation TEXTURE = CTNHBio.id("textures/entity/digester.png");

	@Override
	public ResourceLocation getModelResource(DigesterBlockEntity DigesterBlockEntity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureResource(DigesterBlockEntity DigesterBlockEntity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationResource(DigesterBlockEntity DigesterBlockEntity) {
		return CBValues.EMPTY_ANIMATION;
	}
}