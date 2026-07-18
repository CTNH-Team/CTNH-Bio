package com.moguang.ctnhbio.common;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import com.gregtechceu.gtceu.common.unification.material.MaterialRegistryManager;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityProperties;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperators;
import com.moguang.ctnhbio.data.CBDatagen;
import com.moguang.ctnhbio.data.recipe.CBRecipeCategories;
import com.moguang.ctnhbio.integration.jade.LivingMachineStatusProvider;
import com.moguang.ctnhbio.registry.*;
import com.moguang.ctnhbio.registry.CBCreativeModeTabs;
import com.moguang.ctnhbio.registry.CBEntities;
import com.moguang.ctnhbio.registry.CBSerums;
import tech.vixhentx.mcmod.ctnhlib.jade.JadePriorityManager;

import java.util.ArrayList;

import static com.github.elenterius.biomancy.init.ModRecipes.DECOMPOSING_RECIPE_TYPE;

@SuppressWarnings("removal")
public class CommonProxy {

    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext
                .get().getModEventBus();
        modEventBus.register(this);
        CBSerums.SERUMS.register(modEventBus);

        modEventBus.addGenericListener(MachineDefinition.class, CommonProxy::registerMachines);
        modEventBus.addGenericListener(GTRecipeType.class, CommonProxy::registerRecipeTypes);
        modEventBus.addGenericListener(RecipeConditionType.class, CommonProxy::registerRecipeConditions);
        modEventBus.addGenericListener(GTRecipeCategory.class, CommonProxy::onRecipeCategoryRegister);
        modEventBus.addGenericListener(SoundEntry.class, CommonProxy::onSoundRegister);
    }

    public static void init() {
        CBEntities.init();

        // Object unused = CBToolType.BONING_KNIFE;

        CBCreativeModeTabs.init();
        CBDatagen.init();
        // CBRecipeCategories.init();
        CTNHBio.REGISTRATE.registerRegistrate();

        PropertyOperators.init();
        EntityProperties.init();

        JadePriorityManager.registerBlockData(
                new LivingMachineStatusProvider(),
                BlockEntity.class,
                900,
                "living_machine_status");

        JadePriorityManager.registerBlockComponent(
                new LivingMachineStatusProvider(),
                Block.class,
                900,
                "living_machine_status");
    }

    public static void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        CBMachines.init();
        CBMultiblocks.init();
    }

    public static void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        CBRecipeTypes.init();
    }

    public static void registerRecipeConditions(GTCEuAPI.RegisterEvent<ResourceLocation, RecipeConditionType> event) {
        CBRecipeConditions.init();
    }

    public static void onRecipeCategoryRegister(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeCategory> event) {
        CBRecipeCategories.init();
    }

    public static void onSoundRegister(GTCEuAPI.RegisterEvent<ResourceLocation, SoundEntry> event) {
        CBSoundEntries.init();
    }

    @SubscribeEvent
    public void registerMaterial(MaterialRegistryEvent event) {
        MaterialRegistryManager.getInstance().createRegistry(CTNHBio.MODID);
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CBRecipeTypes.DECOMPOSER_RECIPES.getProxyRecipes().put(DECOMPOSING_RECIPE_TYPE.get(), new ArrayList<>());
        });
    }

    @SubscribeEvent
    public void registerMaterials(MaterialEvent event) {
        CBMaterials.init();
    }
}
