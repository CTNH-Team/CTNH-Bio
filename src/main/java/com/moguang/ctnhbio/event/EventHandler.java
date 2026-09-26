package com.moguang.ctnhbio.event;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.VanillaRecipeProvider;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        // generator.addProvider(event.includeServer(), new VanillaRecipes(packOutput));
        generator.addProvider(event.includeServer(), new VanillaRecipeProvider(packOutput));
    }
}
