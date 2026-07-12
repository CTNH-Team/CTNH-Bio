package com.moguang.ctnhbio.api.recipe.ingredient.model;

import com.gregtechceu.gtceu.api.recipe.ingredient.IChancedIngredient;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import dev.shadowsoffire.hostilenetworks.Hostile;
import dev.shadowsoffire.hostilenetworks.data.DataModel;
import dev.shadowsoffire.hostilenetworks.data.DataModelRegistry;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import lombok.Getter;

import java.util.Objects;

@Getter
public final class ModelIngredient implements IChancedIngredient {

    public static final Codec<ModelIngredient> CODEC = ExtraCodecs.JSON.xmap(ModelIngredient::fromJson,
            ModelIngredient::toJson);

    private final EntityType<?> type;
    private DataModel model;
    private final ModelTier tier;
    private final int chance;

    private ItemStack itemStack;

    public ModelIngredient(EntityType<?> type, ModelTier tier, int chance) {
        this.type = type;
        this.tier = tier;
        this.chance = Mth.clamp(chance, 0, MAX_CHANCE);
    }

    private DataModel getModel() {
        if (model == null) {
            model = Objects.requireNonNull(DataModelRegistry.INSTANCE.getForEntity(type));
        }
        return model;
    }

    public boolean isChanced() {
        return chance < MAX_CHANCE;
    }

    public ModelIngredient copy() {
        return new ModelIngredient(type, tier, chance);
    }

    public ModelIngredient copyWithChance(int chance) {
        return new ModelIngredient(type, tier, chance);
    }

    public boolean test(ItemStack stack) {
        if (!(stack.getItem() instanceof DataModelItem)) return false;
        var storedModel = DataModelItem.getStoredModel(stack).getOptional().orElse(null);
        return storedModel == getModel() &&
                ModelTier.getByData(storedModel, DataModelItem.getData(stack)).ordinal() >= tier.ordinal();
    }

    public boolean testHigher(ItemStack stack) {
        if (!(stack.getItem() instanceof DataModelItem)) return false;
        var storedModel = DataModelItem.getStoredModel(stack).getOptional().orElse(null);
        return storedModel == getModel() &&
                tier.ordinal() > ModelTier.getByData(storedModel, DataModelItem.getData(stack)).ordinal();
    }

    public ItemStack getItem() {
        if (itemStack == null) {
            ItemStack stack = new ItemStack(Hostile.Items.DATA_MODEL.get());
            DataModelItem.setStoredModel(stack, getModel());
            DataModelItem.setData(stack, tier.data().requiredData());
            itemStack = stack;
        }
        return itemStack;
    }

    public ItemStack toStack() {
        return IChancedIngredient.rollSuccesses(1, chance) == 1 ? getItem() : ItemStack.EMPTY;
    }

    // public static ModelIngredient of(ItemStack stack) {
    // var storedModel = DataModelItem.getStoredModel(stack);
    // return new ModelIngredient(storedModel.get(), ModelTier.getByData(storedModel, DataModelItem.getData(stack)),
    // MAX_CHANCE);
    // }

    // public static ModelIngredient of(ModelTier tier, DataModel model) {
    // return new ModelIngredient(model, tier, MAX_CHANCE);
    // }

    public static ModelIngredient of(ModelTier tier, EntityType<?> type) {
        return new ModelIngredient(type, tier, IChancedIngredient.MAX_CHANCE);
    }

    public static ModelIngredient of(ModelTier tier, EntityType<?> type, int chance) {
        return new ModelIngredient(type, tier, chance);
    }

    public static ModelIngredient fromNetwork(FriendlyByteBuf buffer) {
        EntityType<?> type = buffer.readById(BuiltInRegistries.ENTITY_TYPE);
        ModelTier tier = ModelTier.values()[buffer.readByte()];
        return new ModelIngredient(type, tier, buffer.readVarInt());
    }

    public void toNetwork(FriendlyByteBuf buffer) {
        buffer.writeId(BuiltInRegistries.ENTITY_TYPE, type);
        buffer.writeByte(tier.ordinal());
        buffer.writeVarInt(chance);
    }

    public static ModelIngredient fromJson(JsonElement json) {
        JsonObject object = GsonHelper.convertToJsonObject(json, "model ingredient");
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(ResourceLocation.parse(
                GsonHelper.getAsString(object, "entity")));
        ModelTier tier = ModelTier.valueOf(GsonHelper.getAsString(object, "tier").toUpperCase());
        return new ModelIngredient(type, tier, GsonHelper.getAsInt(object, "chance", MAX_CHANCE));
    }

    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("entity", ForgeRegistries.ENTITY_TYPES.getKey(type).toString());
        object.addProperty("tier", tier.getSerializedName());
        object.addProperty("chance", chance);
        return object;
    }
}
