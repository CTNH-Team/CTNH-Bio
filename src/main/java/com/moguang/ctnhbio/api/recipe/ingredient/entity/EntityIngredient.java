package com.moguang.ctnhbio.api.recipe.ingredient.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.IBaseEntityProperty;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityPropertyDetector;
import com.mojang.serialization.Codec;
import lombok.AllArgsConstructor;
import lombok.Builder;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

@Builder
@AllArgsConstructor
public class EntityIngredient implements Predicate<Entity> {

    public static final EntityIngredient EMPTY = new EntityIngredient(new Value[0]);
    public static final Codec<EntityIngredient> CODEC = ExtraCodecs.JSON
            .xmap(EntityIngredient::fromJson, EntityIngredient::toJson);

    //Contents
    @NotNull
    public Value[] values;

    @Builder.Default
    public int count = 1;

    @Nullable
    @Builder.Default
    public CompoundTag nbt = null;

    /*Constructors*/
    public EntityIngredient(@NotNull Value value){
        this(new Value[]{value},1,null);
    }
    public EntityIngredient(@NotNull Value value,int count){
        this(new Value[]{value},count,null);
    }
    public EntityIngredient(@NotNull Value value,int count,CompoundTag nbt){
        this(new Value[]{value},count,nbt);
    }

    public EntityIngredient(@NotNull Value[] values){
        this(values,1,null);
    }
    public EntityIngredient(@NotNull Value[] values,int count){
        this(values,count,null);
    }

    public EntityIngredient(List<Value> values){
        this(values.toArray(Value[]::new));
    }
    public EntityIngredient(List<Value> values,int count){
        this(values.toArray(Value[]::new),count,null);
    }
    public EntityIngredient(List<Value> values,int count,CompoundTag nbt){
        this(values.toArray(Value[]::new),count,nbt);
    }

    //from value
    public static EntityIngredient of(@Nullable Value value){
         return value == null ? EMPTY: new EntityIngredient(value);
    }
    public static EntityIngredient of(@Nullable Value value,int count){
        var ret = of(value);
         ret.count = count;
         return ret;
    }
    public static EntityIngredient of(@Nullable Value value,int count,CompoundTag nbt){
        var ret = of(value,count);
         ret.nbt = nbt;
         return ret;
    }
    //from entity
    public static EntityIngredient of(Entity entity,int count) {
        CompoundTag nbt = new CompoundTag();
        entity.saveWithoutId(nbt);
        return new EntityIngredient(new TypeValue(entity.getType()),count,nbt);
    }
    public static EntityIngredient of(Entity entity){
        return of(entity,1);
    }
    //from entity type
    public static EntityIngredient of(EntityType<?> type){
         return new EntityIngredient(new TypeValue(type));
    }
    public static EntityIngredient of(EntityType<?> type,int count){
        var ret = of(type);
        ret.count = count;
        return ret;
    }
    public static EntityIngredient of(EntityType<?> type,int count,CompoundTag nbt){
        var ret = of(type,count);
        ret.nbt = nbt;
        return ret;
    }
    //from tag
    public static EntityIngredient of(TagKey<EntityType<?>> tag){
        return new EntityIngredient(new TagValue(tag));
    }
    public static EntityIngredient of(TagKey<EntityType<?>> tag,int count){
        var ret = of(tag);
        ret.count = count;
        return ret;
    }
    public static EntityIngredient of(TagKey<EntityType<?>> tag,int count,CompoundTag nbt){
        var ret = of(tag,count);
        ret.nbt = nbt;
        return ret;
    }
    //from id
    public static EntityIngredient of(String id){
        if(id.startsWith("#")){
            ResourceLocation tag = new ResourceLocation(id.substring(1));
            TagKey<EntityType<?>> tagKey = TagKey.create(Registries.ENTITY_TYPE, tag);
            return new EntityIngredient(new TagValue(tagKey));
        }else{
            ResourceLocation type = new ResourceLocation(id);
            EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(type);
            if(entityType==null){
                throw new JsonSyntaxException("Unknown entity type '"+ type +"'");
            }
            return new EntityIngredient(new TypeValue(entityType));
        }
    }
    public static EntityIngredient of(String id,int count){
        var ret = of(id);
        ret.count = count;
        return ret;
    }
    public static EntityIngredient of(String id,int count,CompoundTag nbt){
        var ret = of(id,count);
        ret.nbt = nbt;
        return ret;
    }


    @Override
    public boolean test(@Nullable Entity entity) {
        return entity!=null && values.length!=0 &&
                Arrays.stream(values).anyMatch(v -> v.test(entity.getType())) &&
                    ( nbt==null || EntityPropertyDetector.test(nbt,entity));
    }

    public EntityIngredient copy(){
        return new EntityIngredient(values,count,nbt==null?null:nbt.copy());
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(values);
        result = 31 * result + Integer.hashCode(count);
        result = 31 * result + Objects.hashCode(nbt);
        return result;
    }

    public interface Value extends Predicate<EntityType<?>> {
        Collection<EntityType<?>> getEntityTypes();
        JsonObject serialize();
    }

    public record TagValue(TagKey<EntityType<?>> tag) implements Value{

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
        public boolean equals(Object obj) {
            return obj == this || obj instanceof Value v && v.getEntityTypes().equals(getEntityTypes());
        }

        @Override
        public boolean test(EntityType<?> entityType) {
            return entityType.getTags().toList().contains(tag);
        }
    }
    public record TypeValue(EntityType<?> type) implements Value{

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
        public boolean test(EntityType<?> entityType) {
            return entityType == type;
        }
    }

    //Serialization
    public static Value valueFromJson(JsonObject json){
        if (json.has("entityType") && json.has("tag")) {
            throw new JsonSyntaxException("Expected either 'entityType' or 'tag', not both");
        }
        if (json.has("entityType")) {
            ResourceLocation type = new ResourceLocation(GsonHelper.getAsString(json, "entityType"));
            EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(type);
            if (entityType == null) {
                throw new JsonSyntaxException("Unknown entity type '" + type + "'");
            }
            return new TypeValue(entityType);
        }
        if (json.has("tag")) {
            ResourceLocation tag = new ResourceLocation(GsonHelper.getAsString(json, "tag"));
            TagKey<EntityType<?>> tagKey = TagKey.create(Registries.ENTITY_TYPE, tag);
            return new TagValue(tagKey);
        }
        throw new JsonSyntaxException("Expected either 'entityType' or 'tag'");
    }
    public static EntityIngredient fromJson(@Nullable JsonElement json){
        return fromJson(json,true);
    }
    public static EntityIngredient fromJson(@Nullable JsonElement json,boolean allowEmpty){
        if(json == null || json.isJsonNull()){
            throw new JsonSyntaxException("Expected entity ingredient to be non-null, but was null");
        }

        JsonObject jsonObject = GsonHelper.convertToJsonObject(json, "ingredient");

        int count = GsonHelper.getAsInt(jsonObject, "count", 1);
        CompoundTag nbt = jsonObject.has("nbt") ? CraftingHelper.getNBT(jsonObject.get("nbt")) : null;

        if (GsonHelper.isObjectNode(jsonObject, "value")) {
            Value value = valueFromJson(jsonObject.get("value").getAsJsonObject());
            return new EntityIngredient(value,count,nbt);
        } else if (GsonHelper.isArrayNode(jsonObject, "value")){
            JsonArray jsonArray = GsonHelper.getAsJsonArray(jsonObject, "value");
            if (jsonArray.isEmpty() && !allowEmpty){
                throw new JsonSyntaxException("Entity ingredient array cannot be empty");
            }
            List<Value> values = new ArrayList<>();
            for (JsonElement element : jsonArray) {
                values.add(valueFromJson(element.getAsJsonObject()));
            }
            return new EntityIngredient(values,count,nbt);
        } else if(GsonHelper.isStringValue(jsonObject, "value")){
            String value = GsonHelper.getAsString(jsonObject, "value");
            return of(value);
        } else {
            throw new JsonSyntaxException("Expected either 'value' or 'values'");
        }
    }
    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("count", count);
        if(nbt!=null){
            jsonObject.addProperty("nbt", nbt.getAsString());
        }

        JsonArray jsonArray = new JsonArray();
        for (Value value : values) {
            jsonArray.add(value.serialize());
        }
        jsonObject.add("value", jsonArray);
        return jsonObject;
    }

    //Utils
    public boolean isEmpty(){
        return values==null || values.length==0;
    }
    public Entity createEntity(@NotNull Level level){
        assert values!=null && values.length>0;
        EntityType<?> type = values[0].getEntityTypes().iterator().next();
        var ret = type.create(level);
        assert ret!=null;
        if(nbt!=null) ret.load(nbt);
        return ret;
    }
}
