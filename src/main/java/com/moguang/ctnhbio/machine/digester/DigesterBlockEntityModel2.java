package com.moguang.ctnhbio.machine.digester;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.CBValues;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DigesterBlockEntityModel2 extends GeoModel<LivingMetaMachineBlockEntity> {

	public static final ResourceLocation MODEL = CTNHBio.id("geo/entity/digester.geo.json");
	protected static final ResourceLocation TEXTURE = CTNHBio.id("textures/entity/digester.png");

	@Override
	public ResourceLocation getModelResource(LivingMetaMachineBlockEntity LivingMetaMachineBlockEntity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureResource(LivingMetaMachineBlockEntity LivingMetaMachineBlockEntity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationResource(LivingMetaMachineBlockEntity LivingMetaMachineBlockEntity) {
		return CBValues.EMPTY_ANIMATION;
	}
}