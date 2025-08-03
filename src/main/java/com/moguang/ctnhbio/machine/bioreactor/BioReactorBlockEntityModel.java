package com.moguang.ctnhbio.machine.bioreactor;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.github.elenterius.biomancy.BiomancyMod;
import com.moguang.ctnhbio.CTNHBio;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib.model.GeoModel;

public class BioReactorBlockEntityModel extends GeoModel<BioReactorBlockEntity> {

	public static final ResourceLocation MODEL = CTNHBio.id("geo/entity/bio_reactor.geo.json");
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
		return BiomancyMod.createRL("animations/entity/mob/flesh_blob.animation.json");
	}
}