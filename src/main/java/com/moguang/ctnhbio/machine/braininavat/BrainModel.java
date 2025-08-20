package com.moguang.ctnhbio.machine.braininavat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class BrainModel<T extends Brain> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this BioReactorModel's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryBuild("ctnhbio", "brainmodel"), "main");
	private final ModelPart brain;

	public BrainModel(ModelPart root) {
		this.brain = root.getChild("brain");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition brain = partdefinition.addOrReplaceChild("brain", CubeListBuilder.create().texOffs(8, 43).addBox(-1.5F, -1.75F, -2.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(10, 45).addBox(-1.5F, 0.25F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0F, -1F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Brain entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		brain.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}