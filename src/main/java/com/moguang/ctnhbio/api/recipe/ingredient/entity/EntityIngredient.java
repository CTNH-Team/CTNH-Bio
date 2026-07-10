package com.moguang.ctnhbio.api.recipe.ingredient.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityPropertyDetector;
import com.moguang.ctnhbio.integration.xei.entry.entity.EntityEntryList;
import com.mojang.serialization.Codec;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public class EntityIngredient implements Predicate<Entity> {

    public static final EntityIngredient EMPTY = new EntityIngredient(new Value[0], 0, null);
    public static final Codec<EntityIngredient> CODEC = ExtraCodecs.JSON
            .xmap(EntityIngredient::fromJson, EntityIngredient::toJson);

    private static final byte TYPE_VALUE = 0;
    private static final byte TYPE_CHANCED = 1;
    private static final byte VALUE_TYPE = 0;
    private static final byte VALUE_TAG = 1;

    // Contents
    @NotNull
    public final Value[] values;

    public final int count;

    @Nullable
    public final CompoundTag nbt;

    private int hashCode;

    public EntityIngredient(@NotNull Value[] values, int count, @Nullable CompoundTag nbt) {
       this.values = values;
       this.count = count;
       this.nbt = nbt;
    }

    public EntityIngredient(@NotNull Value value, int count, CompoundTag nbt) {
        this(new Value[] { value }, count, nbt);
    }

    // from entity
    public static EntityIngredient of(Entity entity, int count) {
        CompoundTag nbt = new CompoundTag();
        entity.saveWithoutId(nbt);
        return new EntityIngredient(new TypeValue(entity.getType()), count, nbt);
    }

    public static EntityIngredient of(Entity entity) {
        return of(entity, 1);
    }

    // from entity type
    public static EntityIngredient of(EntityType<?> type) {
        return of(type, 1);
    }

    public static EntityIngredient of(EntityType<?> type, int count) {
        return of(type, count, null);
    }

    public static EntityIngredient of(EntityType<?> type, int count, CompoundTag nbt) {
        return new EntityIngredient(new TypeValue(type), count, nbt);
    }

    // from tag
    public static EntityIngredient of(TagKey<EntityType<?>> tag) {
        return of(tag, 1);
    }

    public static EntityIngredient of(TagKey<EntityType<?>> tag, int count) {
        return of(tag, count, null);
    }

    public static EntityIngredient of(TagKey<EntityType<?>> tag, int count, CompoundTag nbt) {
        return new EntityIngredient(new TagValue(tag), count, nbt);
    }

    // from id
    public static EntityIngredient of(String id, int count, CompoundTag nbt) {
        if (id.startsWith("#")) {
            ResourceLocation tag = ResourceLocation.tryParse(id.substring(1));
            TagKey<EntityType<?>> tagKey = TagKey.create(Registries.ENTITY_TYPE, tag);
            return of(tagKey, count, nbt);
        } else {
            ResourceLocation type = ResourceLocation.tryParse(id);
            EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(type);
            if (entityType == null) {
                throw new JsonSyntaxException("Unknown entity type '" + type + "'");
            }
            return of(entityType, count, nbt);
        }
    }

    public static EntityIngredient of(String id) {
        return of(id, 1);
    }

    public static EntityIngredient of(String id, int count) {
        return of(id, count, null);
    }

    @Override
    public boolean test(@Nullable Entity entity) {
        if(entity == null || values.length == 0) return false;
        for(var value: values) {
            if(value.test(entity.getType()) && (nbt == null || EntityPropertyDetector.test(nbt, entity))) {
                return true;
            }
        }
        return false;
    }

    public EntityIngredient copy() {
        return copyWithMultiplier(1);
    }

    public EntityIngredient getInner() {
        return this;
    }

    public boolean isChanced() {
        return false;
    }

    public int getChance() {
        return ChancedEntityIngredient.MAX_CHANCE;
    }

    public EntityIngredient copyWithCount(int count) {
        return new EntityIngredient(values, count, nbt == null ? null : nbt.copy());
    }

    public EntityIngredient copyWithMultiplier(int multiplier) {
        return new EntityIngredient(values, count * multiplier, nbt == null ? null : nbt.copy());
    }

    public EntityIngredient copyWithChance(int chance) {
        return new ChancedEntityIngredient(copy(), chance);
    }

    public int hash() {
        return 31 * Arrays.hashCode(values) + Objects.hashCode(nbt);
    }

    @Override
    public int hashCode() {
        if(hashCode == 0) {
            hashCode = hash();
        }
        return hashCode;
    }

    public sealed interface Value extends Predicate<EntityType<?>> permits TagValue, TypeValue {

        Collection<EntityType<?>> getEntityTypes();

        JsonObject serialize();

        // Utils for xei
        void appendEntryList(EntityEntryList list);
    }

    public record TagValue(TagKey<EntityType<?>> tag) implements Value {

        @Override
        public Collection<EntityType<?>> getEntityTypes() {
            return ForgeRegistries.ENTITY_TYPES.tags().getTag(tag)
                    .stream().toList();
        }

        @Override
        public JsonObject serialize() {
            JsonObject json = new JsonObject();
            json.addProperty("tag", tag.location().toString());
            return json;
        }

        @Override
        public void appendEntryList(EntityEntryList list) {
            list.add(tag);
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || obj instanceof Value v && v.getEntityTypes().equals(getEntityTypes());
        }

        @Override
        public boolean test(EntityType<?> entityType) {
            return entityType.getTags().toList().contains(tag);
        }

        @Override
        public int hashCode() {
            return tag.hashCode();
        }
    }

    public record TypeValue(EntityType<?> type) implements Value {

        @Override
        public Collection<EntityType<?>> getEntityTypes() {
            return List.of(type);
        }

        @Override
        public JsonObject serialize() {
            JsonObject json = new JsonObject();
            json.addProperty("entityType", EntityType.getKey(type).toString());
            return json;
        }

        @Override
        public void appendEntryList(EntityEntryList list) {
            list.add(type);
        }

        @Override
        public boolean test(EntityType<?> entityType) {
            return entityType == type;
        }

        @Override
        public int hashCode() {
            return type.hashCode();
        }
    }

    // Serialization
    public static Value valueFromJson(JsonObject json) {
        if (json.has("entityType") && json.has("tag")) {
            throw new JsonSyntaxException("Expected either 'entityType' or 'tag', not both");
        }
        if (json.has("entityType")) {
            ResourceLocation type = ResourceLocation.tryParse(GsonHelper.getAsString(json, "entityType"));
            EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(type);
            if (entityType == null) {
                throw new JsonSyntaxException("Unknown entity type '" + type + "'");
            }
            return new TypeValue(entityType);
        }
        if (json.has("tag")) {
            ResourceLocation tag = ResourceLocation.tryParse(GsonHelper.getAsString(json, "tag"));
            TagKey<EntityType<?>> tagKey = TagKey.create(Registries.ENTITY_TYPE, tag);
            return new TagValue(tagKey);
        }
        throw new JsonSyntaxException("Expected either 'entityType' or 'tag'");
    }

    public static EntityIngredient fromJson(@Nullable JsonElement json) {
        return fromJson(json, true);
    }

    public static EntityIngredient fromJson(@Nullable JsonElement json, boolean allowEmpty) {
        if (json == null || json.isJsonNull()) {
            throw new JsonSyntaxException("Expected entity ingredient to be non-null, but was null");
        }

        JsonObject jsonObject = GsonHelper.convertToJsonObject(json, "ingredient");

        int count = GsonHelper.getAsInt(jsonObject, "count", 1);
        CompoundTag nbt = jsonObject.has("nbt") ? CraftingHelper.getNBT(jsonObject.get("nbt")) : null;

        if (jsonObject.has("chance")) {
            EntityIngredient inner = fromJson(GsonHelper.getAsJsonObject(jsonObject, "ingredient"));
            int chance = GsonHelper.getAsInt(jsonObject, "chance");
            int multiplier = GsonHelper.getAsInt(jsonObject, "multiplier", 1);
            return new ChancedEntityIngredient(inner, chance, multiplier);
        }

        if (GsonHelper.isObjectNode(jsonObject, "value")) {
            Value value = valueFromJson(jsonObject.get("value").getAsJsonObject());
            return new EntityIngredient(value, count, nbt);
        } else if (GsonHelper.isArrayNode(jsonObject, "value")) {
            JsonArray jsonArray = GsonHelper.getAsJsonArray(jsonObject, "value");
            if (jsonArray.isEmpty() && !allowEmpty) {
                throw new JsonSyntaxException("Entity ingredient array cannot be empty");
            }
            List<Value> values = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                values.add(valueFromJson(element.getAsJsonObject()));
            }
            return new EntityIngredient(values.toArray(new Value[]{}), count, nbt);
        } else if (GsonHelper.isStringValue(jsonObject, "value")) {
            String value = GsonHelper.getAsString(jsonObject, "value");
            return of(value);
        } else {
            throw new JsonSyntaxException("Expected either 'value' or 'values'");
        }
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("count", count);
        if (nbt != null) {
            jsonObject.addProperty("nbt", nbt.getAsString());
        }

        if (this instanceof ChancedEntityIngredient ingredient) {
            jsonObject.add("ingredient", ingredient.getInner().toJson());
            jsonObject.addProperty("chance", ingredient.getChance());
            jsonObject.addProperty("multiplier", ingredient.getMultiplier());
            return jsonObject;
        }

        JsonArray jsonArray = new JsonArray();
        for (Value value : values) {
            jsonArray.add(value.serialize());
        }
        jsonObject.add("value", jsonArray);
        return jsonObject;
    }

    public static EntityIngredient fromNetwork(FriendlyByteBuf buf) {
        byte type = buf.readByte();
        return switch (type) {
            case TYPE_VALUE -> readValueIngredient(buf);
            case TYPE_CHANCED -> new ChancedEntityIngredient(fromNetwork(buf), buf.readVarInt(), buf.readVarInt());
            default -> throw new IllegalArgumentException("Unknown EntityIngredient network type: " + type);
        };
    }

    public void toNetwork(FriendlyByteBuf buf) {
        if (this instanceof ChancedEntityIngredient ingredient) {
            buf.writeByte(TYPE_CHANCED);
            ingredient.getInner().toNetwork(buf);
            buf.writeVarInt(ingredient.getChance());
            buf.writeVarInt(ingredient.getMultiplier());
            return;
        }

        buf.writeByte(TYPE_VALUE);
        buf.writeVarInt(count);
        buf.writeNbt(nbt);
        buf.writeVarInt(values.length);
        for (Value value : values) {
            if (value instanceof TypeValue typeValue) {
                buf.writeByte(VALUE_TYPE);
                buf.writeId(BuiltInRegistries.ENTITY_TYPE, typeValue.type);
            } else if (value instanceof TagValue tagValue) {
                buf.writeByte(VALUE_TAG);
                buf.writeResourceLocation(tagValue.tag().location());
            } else {
                throw new IllegalArgumentException("Unsupported EntityIngredient value: " + value.getClass().getName());
            }
        }
    }

    private static EntityIngredient readValueIngredient(FriendlyByteBuf buf) {
        int count = buf.readVarInt();
        CompoundTag nbt = buf.readNbt();
        int size = buf.readVarInt();
        Value[] values = new Value[size];
        for (int i = 0; i < size; i++) {
            byte valueType = buf.readByte();
            values[i] = switch (valueType) {
                case VALUE_TYPE -> new TypeValue(buf.readById(BuiltInRegistries.ENTITY_TYPE));
                case VALUE_TAG -> new TagValue(TagKey.create(Registries.ENTITY_TYPE, buf.readResourceLocation()));
                default -> throw new IllegalArgumentException("Unknown EntityIngredient value network type: " + valueType);
            };
        }
        return new EntityIngredient(values, count, nbt);
    }

    // Utils
    public boolean isEmpty() {
        return values == null || values.length == 0;
    }

    public Entity createEntity(@NotNull Level level) {
        assert values != null && values.length > 0;
        EntityType<?> type = values[0].getEntityTypes().iterator().next();
        var ret = type.create(level);
        assert ret != null;
        if (nbt != null) ret.load(getNormalizedNBT());
        return ret;
    }

    public CompoundTag getNormalizedNBT() {
        assert nbt != null;
        return EntityPropertyDetector.getNormalizedNBT(nbt);
    }
}
