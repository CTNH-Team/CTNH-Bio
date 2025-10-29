package com.moguang.ctnhbio.api.recipe.ingredient.model;

import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;
import dev.shadowsoffire.hostilenetworks.data.DataModel;
import dev.shadowsoffire.hostilenetworks.data.DataModelRegistry;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModelMapIngredient extends AbstractMapIngredient {

    protected ModelTier tier;
    protected ResourceLocation modelID;
    @Override
    protected int hash() {
        return modelID.hashCode() * 31;
    }

    public ModelMapIngredient(ModelTier tier, ResourceLocation modelID){
        this.tier = tier;
        this.modelID = modelID;
    }

    @NotNull
    public static List<AbstractMapIngredient> from(ModelIngredient ingredient) {
        List<AbstractMapIngredient> ingredients = new ObjectArrayList<>();
        ModelTier modelTier = ModelTier.getByData(DataModelRegistry.INSTANCE.getForEntity(EntityType.COW),
                ingredient.requiredData);
        ingredients.add(new ModelMapIngredient(modelTier, ingredient.modelID));
        return ingredients;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ModelMapIngredient modelMapIngredient){
            return tier == modelMapIngredient.tier &&
                    modelID.equals(modelMapIngredient.modelID);
        }
        return false;
    }

    @Override
    public String toString() {
        return "ModelMapIngredient{" + "modelID=" + modelID.toString() +",tier="+ tier.name + "}";
    }
}
