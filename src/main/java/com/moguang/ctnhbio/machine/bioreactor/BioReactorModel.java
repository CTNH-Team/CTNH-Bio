package com.moguang.ctnhbio.machine.bioreactor;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BioReactorModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this BioReactorModel's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("ctnhbio", "bioreactormodel"), "main");
	private final ModelPart main;
	private final ModelPart bone;
	private final ModelPart bone2;

	public BioReactorModel(ModelPart root) {
		this.main = root.getChild("main");
		this.bone = this.main.getChild("bone");
		this.bone2 = this.main.getChild("bone2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 37).addBox(-7.0F, -4.0F, -7.0F, 14.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(56, 34).addBox(6.25F, -15.0F, 6.25F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(64, 0).addBox(-8.25F, -9.0F, -8.25F, 2.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(64, 11).addBox(-8.0F, -10.0F, -8.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(56, 51).addBox(3.25F, -22.0F, 3.25F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(64, 43).addBox(3.25F, -20.0F, 3.25F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -2.0F, -61.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 34).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.25F, -14.75F, 7.25F, 0.3655F, 0.147F, -0.3655F));

		PartDefinition bone2 = main.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -2.0F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition bone = main.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 18).addBox(-7.0F, -6.35F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
				.texOffs(0, 55).addBox(-6.5F, -2.25F, -6.5F, 13.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition cube_r2 = bone.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 18).addBox(-2.0F, -2.0F, -3.0F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -7.0F, -4.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition cube_r3 = bone.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(52, 55).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -7.0F, 3.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//this.main.yRot = (float) Math.PI;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}