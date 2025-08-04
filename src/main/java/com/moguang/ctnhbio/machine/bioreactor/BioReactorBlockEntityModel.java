package com.moguang.ctnhbio.machine.bioreactor;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.CBValues;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BioReactorBlockEntityModel extends GeoModel<BioReactorBlockEntity> {

	public static final ResourceLocation MODEL = CTNHBio.id("geo/entity/bio_reactor_2.geo.json");
	protected static final ResourceLocation TEXTURE = CTNHBio.id("textures/entity/bioreactor.png");

	@Override
	public ResourceLocation getModelResource(BioReactorBlockEntity bioReactorBlockEntity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureResource(BioReactorBlockEntity bioReactorBlockEntity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationResource(BioReactorBlockEntity bioReactorBlockEntity) {
		return CBValues.EMPTY_ANIMATION;
	}
}