package com.moguang.ctnhbio.event;

import com.github.elenterius.biomancy.init.ModEnchantments;
import com.google.gson.Gson;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.data.DimensionMarker;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.MaterialProperties;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.moguang.ctnhbio.registry.*;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.yanny.ali.registries.LootCategories;
import net.minecraft.core.BlockPos;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import static com.moguang.ctnhbio.registry.CBMaterialItems.CB_TOOL_ITEMS;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {
    public static void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        CBMachines.init();
        CBMultiblockMachines.init();

//        CTNHItems.init();
//        CTNHBlocks.init();
//        CTNHMultiblockMachines.init();
    }

    public static void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        CBRecipeTypes.init();
    }

    @SubscribeEvent
    public static void registerRecipeConditions(GTCEuAPI.RegisterEvent<ResourceLocation, RecipeConditionType> event) {
        CBRecipeConditions.init();
    }
    public static void registerChanceLogic(GTCEuAPI.RegisterEvent<ResourceLocation, ChanceLogic> event){
//        CTNHChanceLogic.init();
    }
    //public static void onRe

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event) {
        for (ItemProviderEntry<IGTTool> entry : CB_TOOL_ITEMS.column(CBToolType.BONING_KNIFE).values()) {
            ItemStack stack = new ItemStack(entry.get());
            stack.enchant(ModEnchantments.DESPOIL.get(), 3);
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(RegisterEvent event) {
//        event.register(ForgeRegistries.ITEMS.getRegistryKey(), helper -> {
//            CBItems.init();
//        });
    }




    @SubscribeEvent
    public static void registerMaterials(MaterialEvent event) {
//        MaterialProperties.addBaseType(CTNHPropertyKeys.NUCLEAR);
//        CTNHMaterials.init();
    }
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        var registries = event.getLookupProvider();
    }
}
