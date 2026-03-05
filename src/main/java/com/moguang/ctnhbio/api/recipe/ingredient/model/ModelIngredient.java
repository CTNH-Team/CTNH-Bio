package com.moguang.ctnhbio.api.recipe.ingredient.model;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moguang.ctnhbio.CTNHBio;
import com.mojang.serialization.Codec;
import dev.shadowsoffire.hostilenetworks.Hostile;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class ModelIngredient extends Ingredient {

    public static Codec<ModelIngredient> CODEC = ExtraCodecs.JSON
            .xmap(ModelIngredient::fromJson, ModelIngredient::toJson);
    public static Value dummyDataModelIngredient = new Ingredient.ItemValue(
            new ItemStack(Hostile.Items.DATA_MODEL.get()));
    public static ResourceLocation TYPE = CTNHBio.id("model");
    public static ModelIngredient DEFAULT = of(0, getModelId(EntityType.PIG));
    final int requiredData;
    final ResourceLocation modelID;
    @Getter
    final ItemStack model;

    protected ModelIngredient(ItemStack modelStack) {
        super(Stream.of(dummyDataModelIngredient));
        model = modelStack;
        this.requiredData = DataModelItem.getData(modelStack);
        this.modelID = DataModelItem.getStoredModel(modelStack).getId();
    }

    protected ModelIngredient(int requiredData, ResourceLocation modelID) {
        super(Stream.of(dummyDataModelIngredient));
        model = ModelIngredient.getModelStack(modelID, requiredData);
        this.requiredData = requiredData;
        this.modelID = modelID;
    }

    @Override
    public boolean test(@Nullable ItemStack target) {
        if (getItems().length == 0) return false;
        if (target == null) return true;
        // final ItemStack requirement = getItems()[0];
        // final int requiredData = DataModelItem.getData(requirement);
        final int targetData = DataModelItem.getData(target);
        var targetID = DataModelItem.getStoredModel(target).getId();// target.getTagElement("data_model");

        return targetData >= requiredData &&
                modelID.equals(targetID);
    }

    public boolean check(ItemStack target) {
        if (getItems().length == 0) return false;
        if (target == null) return true;
        // final ItemStack requirement = getItems()[0];
        // final int requiredData = DataModelItem.getData(requirement);
        final int targetData = DataModelItem.getData(target);
        var targetID = DataModelItem.getStoredModel(target).getId();// target.getTagElement("data_model");

        return targetData >= requiredData &&
                modelID.equals(targetID);
    }

    public static ResourceLocation getModelId(ResourceLocation type) {
        return type.getNamespace().equals("minecraft") ?
                ResourceLocation.fromNamespaceAndPath("hostilenetworks", type.getPath()) :
                ResourceLocation.fromNamespaceAndPath("hostilenetworks",
                        "%s/%s".formatted(type.getNamespace(), type.getPath()));
    }

    public static ResourceLocation getModelId(EntityType<?> type) {
        ResourceLocation t = ForgeRegistries.ENTITY_TYPES.getKey(type);
        return getModelId(t);
    }

    public static ItemStack getModelStack(ResourceLocation modelId, int data) {
        var ret = new ItemStack(Hostile.Items.DATA_MODEL.get());
        DataModelItem.setStoredModel(ret, modelId);
        DataModelItem.setData(ret, data);
        return ret;
    }

    public ModelIngredient copy() {
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

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return SERIALIZER;
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", TYPE.toString());
        jsonObject.addProperty("model", modelID.toString());
        jsonObject.addProperty("data", requiredData);
        return jsonObject;
    }

    public static ModelIngredient fromJson(JsonElement json) {
        return SERIALIZER.parse(json.getAsJsonObject());
    }

    public static final IIngredientSerializer<ModelIngredient> SERIALIZER = new IIngredientSerializer<ModelIngredient>() {

        @Override
        @NotNull
        public ModelIngredient parse(FriendlyByteBuf buffer) {
            // 从网络数据包解析
            ResourceLocation modelID = buffer.readResourceLocation();
            int requiredData = buffer.readVarInt();
            return ModelIngredient.of(requiredData, modelID);
        }

        @Override
        @NotNull
        public ModelIngredient parse(JsonObject json) {
            // 从 JSON 解析
            ResourceLocation modelID = ResourceLocation.tryParse(json.get("model").getAsString());
            int requiredData = json.get("data").getAsInt();
            return ModelIngredient.of(requiredData, modelID);
        }

        @Override
        public void write(FriendlyByteBuf buffer, ModelIngredient ingredient) {
            // 写入网络数据包
            buffer.writeResourceLocation(ingredient.modelID);
            buffer.writeVarInt(ingredient.requiredData);
        }
    };
}
