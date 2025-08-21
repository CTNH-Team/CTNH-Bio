package com.moguang.ctnhbio.integration.xei.entry.entity;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityPropertyDetector;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityPropertyValue;
import lombok.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import java.util.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityEntryList {
    @Getter
    public final List<EntityType<?>> entries;
    public final List<TagKey<EntityType<?>>> tags;
    public final List<EntityType<?>> types;
    public final CompoundTag nbt;

    //constructor from ingredient
    public EntityEntryList(EntityIngredient ingredient, boolean exactlyNBT){
        tags = new ArrayList<>();
        types = new ArrayList<>();
        nbt = ingredient.nbt == null || exactlyNBT ? ingredient.nbt : ingredient.getNormalizedNBT();

        //build types & tags
        Arrays.stream(ingredient.values)
                .forEach(v->v.appendEntryList(this));

        //build entries
        entries = new ArrayList<>();
        Arrays.stream(ingredient.values)
                .map(EntityIngredient.Value::getEntityTypes)
                .forEach(entries::addAll);
    }

    //Value compact
    public void add(EntityType<?> type){
        types.add(type);
    }
    public void add(TagKey<EntityType<?>> tag){
        tags.add(tag);
    }

    //Text
    public List<Component> buildTooltip(){
        List<Component> tooltip = new ArrayList<>();

        //tags
        if(!tags.isEmpty()){
            List<String> slist = tags.stream()
                    .map(TagKey::location)
                    .map(rl-> "#"+rl)
                    .toList();
            if(slist.size() == 1){
                tooltip.add(Component.translatable("jei.tooltip.recipe.tag",slist.get(0)));
            }
            else{
                tooltip.add(Component.translatable("jei.tooltip.recipe.tag",""));
                slist.stream()
                        .map(Component::literal)
                        .forEach(tooltip::add);
            }
        }
        //types
        if(!types.isEmpty()){
            List<Component> slist = types.stream()
                    .map(EntityType::getDescription)
                    .toList();
            if(slist.size() == 1){
                tooltip.add(Component.translatable("jei.tooltip.recipe.entitytype",slist.get(0)));
            }
            else {
                tooltip.add(Component.translatable("jei.tooltip.recipe.entitytype",""));
                tooltip.addAll(slist);
            }
        }
        //nbt
        if(nbt!= null){
            var plist = new EntityPropertyDetector(nbt).properties;
            if(!plist.isEmpty()){
                tooltip.add(Component.translatable("ctnhbio.tooltip.entity.requirement"));
                plist.stream()
                        .map(EntityPropertyValue::getDescription)
                        .forEach(tooltip::add);
            }
        }
        return tooltip;
    }

    //Utils
    public EntityEntryList copy(){
        return new EntityEntryList(entries,tags,types,nbt);
    }

}
