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
                "biofactory:mixing/nutrients_fluid_from_paste",
                "biofactory:compacting/nutrient_bar_from_fluid",
                "biomancy:crafting/decomposer",
                "biomancy:crafting/primordial_cradle",
                "biomancy:crafting/primordial_core",
                "biomancy:crafting/despoil_sickle",
                "biomancy:crafting/bio_forge"
        };
        for(var recipe: recipes) {
            registry.accept(ResourceLocation.tryParse(recipe));
        }
        DataFilterPack.removeData("alexsmobs", "^loot_tables/biomancy");
    }


}
