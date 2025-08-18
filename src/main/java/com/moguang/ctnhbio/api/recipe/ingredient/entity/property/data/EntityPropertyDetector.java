package com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@ParametersAreNonnullByDefault
public class EntityPropertyDetector implements Predicate<CompoundTag> {
    public final List<EntityPropertyValue<?>> properties;

    public EntityPropertyDetector(CompoundTag nbt){
        this.properties = new ArrayList<>();
        nbt.getAllKeys().stream()
                .map(EntityProperties::get)
                .map(p->EntityPropertyValue.fromNBT(p,nbt))
                .forEach(p->{
                     if(p!= null) properties.add(p);
                });
    }
    @Override
    public boolean test(CompoundTag entityNBT) {
        return properties.stream().allMatch(p -> p.test(entityNBT));
    }

    //Util
    public static boolean test(CompoundTag requirementNBT, Entity entity){
        return new EntityPropertyDetector(requirementNBT).test(entity.saveWithoutId(new CompoundTag()));
    }
}
