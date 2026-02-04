package com.moguang.ctnhbio.data.recipe;

import net.minecraft.resources.ResourceLocation;
import tech.vixhentx.mcmod.ctnhlib.data.DataFilterPack;

import java.util.function.Consumer;

public class RecipeRemoval {
    public static void init(Consumer<ResourceLocation> registry) {
        biomancyRemovals(registry);

    }

    public static void biomancyRemovals(Consumer<ResourceLocation> registry){
        var recipes = new String[]{
                "biomancy:crafting/primordial_cradle",
                "biomancy:crafting/primordial_core",
                "biomancy:crafting/decomposer",
                "biomancy:crafting/despoil_sickle",
                "biomancy:crafting/bio_forge",
                "biofactory:mixing/nutrients_fluid_from_paste",
                "biofactory:compacting/nutrient_bar_from_fluid",
        };
        for(var recipe: recipes) {
            DataFilterPack.removeRecipe(recipe);

        }
        DataFilterPack.removeRecipeType("biomancy:bio_forging");
        DataFilterPack.removeRecipeType("biomancy:bio_brewing");
        DataFilterPack.removeRecipeType("biomancy:digesting");

        DataFilterPack.removeData("alexsmobs", "^loot_tables/biomancy");
    }


}
