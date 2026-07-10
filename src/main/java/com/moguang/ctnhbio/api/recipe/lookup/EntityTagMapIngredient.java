package com.moguang.ctnhbio.api.recipe.lookup;

import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class EntityTagMapIngredient extends AbstractMapIngredient {

    private final TagKey<EntityType<?>> tag;

    public EntityTagMapIngredient(TagKey<EntityType<?>> tag) {
        this.tag = tag;
    }

    @NotNull
    public static List<AbstractMapIngredient> from(EntityIngredient ingredient) {
        List<AbstractMapIngredient> ingredients = new ObjectArrayList<>();
        for (EntityIngredient.Value value : ingredient.values) {
            if (value instanceof EntityIngredient.TagValue tagValue) {
                ingredients.add(new EntityTagMapIngredient(tagValue.tag()));
            }
        }
        return ingredients;
    }

    @NotNull
    public static List<AbstractMapIngredient> from(Entity entity) {
        List<AbstractMapIngredient> ingredients = new ObjectArrayList<>();
        entity.getType().getTags().forEach(tag -> ingredients.add(new EntityTagMapIngredient(tag)));
        return ingredients;
    }

    @Override
    protected int hash() {
        return tag.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && tag == ((EntityTagMapIngredient) obj).tag;
    }

    @Override
    public String toString() {
        return "EntityTagMapIngredient{tag=" + tag.location() + "}";
    }
}
