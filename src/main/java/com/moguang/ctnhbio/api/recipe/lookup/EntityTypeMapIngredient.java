package com.moguang.ctnhbio.api.recipe.lookup;

import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class EntityTypeMapIngredient extends AbstractMapIngredient {

    private final EntityType<?> type;

    public EntityTypeMapIngredient(EntityType<?> type) {
        this.type = type;
    }

    @NotNull
    public static List<AbstractMapIngredient> from(EntityIngredient ingredient) {
        List<AbstractMapIngredient> ingredients = new ObjectArrayList<>();
        for (EntityIngredient.Value value : ingredient.values) {
            if (value instanceof EntityIngredient.TypeValue typeValue) {
                ingredients.add(new EntityTypeMapIngredient(typeValue.type()));
            }
        }
        return ingredients;
    }

    @NotNull
    public static List<AbstractMapIngredient> from(Entity entity) {
        return Collections.singletonList(new EntityTypeMapIngredient(entity.getType()));
    }

    @Override
    protected int hash() {
        return type.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && type == ((EntityTypeMapIngredient) obj).type;
    }

    @Override
    public String toString() {
        return "EntityTypeMapIngredient{type=" + EntityType.getKey(type) + "}";
    }
}

