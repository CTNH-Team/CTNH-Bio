package com.moguang.ctnhbio.api.recipe.ingredient.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moguang.ctnhbio.CTNHBio;
import com.mojang.serialization.Codec;
import dev.shadowsoffire.hostilenetworks.Hostile;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModelIngredient extends StrictNBTIngredient {
    public static Codec<ModelIngredient> CODEC = ExtraCodecs.JSON
            .xmap(ModelIngredient::fromJson, ModelIngredient::toJson);
    public static ResourceLocation TYPE = CTNHBio.id("model");
    public static ModelIngredient DEFAULT = of(0, getModelId(EntityType.PIG));
    final int requiredData;
    final ResourceLocation modelID;
    protected ModelIngredient(ItemStack modelStack){
        super(modelStack);
        this.requiredData = DataModelItem.getData(modelStack);
        this.modelID = DataModelItem.getStoredModel(modelStack).getId();
    }
    protected ModelIngredient(int requiredData, ResourceLocation modelID) {
        super(getModelStack(modelID, requiredData));
        this.requiredData = requiredData;
        this.modelID = modelID;
    }

    @Override
    public boolean test(@Nullable ItemStack target) {
        if(getItems().length == 0) return false;
        if(target == null) return true;
        final ItemStack requirement = getItems()[0];
        final int requiredData = DataModelItem.getData(requirement);
        final int targetData = DataModelItem.getData(target);
        return targetData >= requiredData &&
                requirement.getTagElement("data_model").equals(target.getTagElement("data_model"));
    }

    public static ResourceLocation getModelId(ResourceLocation type) {
        return type.getNamespace().equals("minecraft")?
                ResourceLocation.fromNamespaceAndPath("hostilenetworks",type.getPath()) :
                ResourceLocation.fromNamespaceAndPath("hostilenetworks","%s/%s".formatted(type.getNamespace(),type.getPath()));
    }

    public static ResourceLocation getModelId(EntityType<?> type) {
        ResourceLocation t = ForgeRegistries.ENTITY_TYPES.getKey(type);
        return getModelId(t);
    }

    public static ItemStack getModelStack(ResourceLocation modelId, int data) {
        var ret = new ItemStack(Hostile.Items.DATA_MODEL.get());
        DataModelItem.setStoredModel(ret,modelId);
        DataModelItem.setData(ret,data);
        return ret;
    }

    public ModelIngredient copy(){
        return new ModelIngredient(requiredData, modelID);
    }
    public static ModelIngredient of(@NotNull ItemStack modelStack) {
        return new ModelIngredient(modelStack);
    }

    public static ModelIngredient of(int requiredData, ResourceLocation modelID) {
        return new ModelIngredient(requiredData, modelID);
    }
    public static ModelIngredient of(ResourceLocation modelID) {
        return new ModelIngredient(0, modelID);
    }
    public static ModelIngredient of(ModelTier requiredTier, ResourceLocation modelID) {
        return new ModelIngredient(requiredTier.data().requiredData(), modelID);
    }
    public static ModelIngredient of(int requiredData, EntityType<?> type) {
        return of(requiredData, getModelId(type));
    }
    public static ModelIngredient of(EntityType<?> type) {
        return of(0, getModelId(type));
    }
    public static ModelIngredient of(ModelTier requiredTier, EntityType<?> type) {
        return of(requiredTier, getModelId(type));
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("model", modelID.toString());
        jsonObject.addProperty("data", requiredData);
        return jsonObject;
    }
    public static ModelIngredient fromJson(JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        ResourceLocation modelID = ResourceLocation.tryParse(jsonObject.get("model").getAsString());
        int requiredData = jsonObject.get("data").getAsInt();
        return new ModelIngredient(requiredData, modelID);
    }
}
