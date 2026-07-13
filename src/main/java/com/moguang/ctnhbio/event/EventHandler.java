package com.moguang.ctnhbio.event;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import com.moguang.ctnhbio.data.recipe.VanillaRecipeProvider;
import com.moguang.ctnhbio.registry.*;

public class EventHandler {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        var registries = event.getLookupProvider();

        // generator.addProvider(event.includeServer(), new VanillaRecipes(packOutput));
        generator.addProvider(event.includeServer(), new VanillaRecipeProvider(packOutput));
    }
}
