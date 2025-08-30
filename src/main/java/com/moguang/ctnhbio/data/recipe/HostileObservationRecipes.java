package com.moguang.ctnhbio.data.recipe;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import dev.shadowsoffire.hostilenetworks.Hostile;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient.getModelId;
import static com.moguang.ctnhbio.registry.CBRecipeTypes.HOSTILE_OBSERVATION;

public class HostileObservationRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        registerForEntityType(EntityType.COW, provider);
    }
    static ResourceLocation getRecipeId(EntityType<?> type) {
        ResourceLocation t = ForgeRegistries.ENTITY_TYPES.getKey(type);
        return CTNHBio.id("observation_%s_%s".formatted(t.getNamespace(),t.getPath()));
    }
    public static void registerForEntityType(EntityType<?> type, Consumer<FinishedRecipe> provider){
        ResourceLocation recipeId = getRecipeId(type);

        CBRecipeBuilder.of(recipeId, HOSTILE_OBSERVATION)
                .inputEntity(type)
                .inputModel(ModelIngredient.of(ModelTier.SUPERIOR,type))
                .outputModel(ModelIngredient.of(ModelTier.SELF_AWARE,type))
//                .durationIsTotalCWU(true)
                .CWUt(64)
                .duration(ModelTier.SELF_AWARE.data().requiredData() * 64)
                .save(provider);
    }
}
