package com.moguang.ctnhbio.event;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.moguang.ctnhbio.data.recipe.VanillaRecipeProvider;
import com.moguang.ctnhbio.data.recipe.CBRecipeCategories;
import com.moguang.ctnhbio.registry.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

import java.util.ArrayList;

import static com.github.elenterius.biomancy.init.ModRecipes.DECOMPOSING_RECIPE_TYPE;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    public static void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        CBMachines.init();
        CBMultiblocks.init();
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
    public static void registerMaterial(MaterialRegistryEvent event) {
//        MaterialRegistryManager.getInstance().createRegistry(CTNHBio.MODID);
    }


    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CraftingHelper.register(ModelIngredient.TYPE, ModelIngredient.SERIALIZER);
            CBRecipeTypes.DECOMPOSER_RECIPES.getProxyRecipes().put(DECOMPOSING_RECIPE_TYPE.get(), new ArrayList<>());
        });

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
    }
}
