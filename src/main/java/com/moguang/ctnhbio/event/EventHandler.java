package com.moguang.ctnhbio.event;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.loot.CBLootTableProvider;
import com.moguang.ctnhbio.data.recipe.VanillaRecipeProvider;
import com.moguang.ctnhbio.data.recipe.CBRecipeCategories;
import com.moguang.ctnhbio.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    public static void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        CBMachines.init();
        CBMultiblockMachines.init();
    }

    public static void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        CBRecipeTypes.init();
    }

    @SubscribeEvent
    public static void registerRecipeConditions(GTCEuAPI.RegisterEvent<ResourceLocation, RecipeConditionType> event) {
        CBRecipeConditions.init();
    }

    //@SubscribeEvent
    public static void onRecipeCategoryRegister(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeCategory> event) {
        CBRecipeCategories.init();
    }

    public static void onSoundRegister(GTCEuAPI.RegisterEvent<ResourceLocation, SoundEntry> event) {
        CBSoundEntries.init();
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
//        for (ItemProviderEntry<IGTTool> entry : CB_TOOL_ITEMS.column(CBToolType.BONING_KNIFE).values()) {
//            ItemStack stack = new ItemStack(entry.get());
//            stack.enchant(ModEnchantments.DESPOIL.get(), 3);
//        }
    }




    @SubscribeEvent
    public static void registerMaterials(MaterialEvent event) {
        CBMaterials.init();
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        var registries = event.getLookupProvider();

        //generator.addProvider(event.includeServer(), new VanillaRecipes(packOutput));
        generator.addProvider(event.includeServer(), new VanillaRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), new CBLootTableProvider(packOutput));
    }
}
