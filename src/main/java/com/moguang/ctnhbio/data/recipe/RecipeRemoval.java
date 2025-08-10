package com.moguang.ctnhbio.data.recipe;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class RecipeRemoval {
    public static void init(Consumer<ResourceLocation> registry) {
        biomancyRemovals(registry);
    }

    public static void biomancyRemovals(Consumer<ResourceLocation> registry){
        var recipes = new String[]{
                "minecraft:tnt"
        };


        registry.accept(new ResourceLocation("biomancy:crafting/primordial_core"));
        registry.accept(new ResourceLocation("biomancy:bio_brewing"));
    }


}
