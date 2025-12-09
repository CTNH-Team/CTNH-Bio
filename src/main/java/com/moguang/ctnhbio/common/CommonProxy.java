package com.moguang.ctnhbio.common;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.MapIngredientTypeManager;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.item.ItemStackMapIngredient;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.item.StrictNBTItemStackMapIngredient;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.capability.forge.CBCapabilities;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.moguang.ctnhbio.api.recipe.content.SerializerModelIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityProperties;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelMapIngredient;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperators;
import com.moguang.ctnhbio.data.CBDatagen;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;
import com.moguang.ctnhbio.data.recipe.CBRecipeCategories;
import com.moguang.ctnhbio.integration.jade.LivingMachineStatusProvider;
import com.moguang.ctnhbio.registry.CBCreativeModeTabs;
import com.moguang.ctnhbio.registry.CBEntities;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBSerums;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import com.moguang.ctnhbio.registry.*;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tech.vixhentx.mcmod.ctnhlib.jade.JadePriorityManager;

@SuppressWarnings("removal")
public class CommonProxy {
    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext
                .get().getModEventBus();
        CBSerums.SERUMS.register(modEventBus);
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CraftingHelper.register(ModelIngredient.TYPE, ModelIngredient.SERIALIZER);

        });

    }
    public static void init() {
        CBEntities.init();

        //Object unused = CBToolType.BONING_KNIFE;

        CBCreativeModeTabs.init();
        CBDatagen.init();
        //CBRecipeCategories.init();
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
                "living_machine_status")
        ;


    }

    @SubscribeEvent
    public void modConstruct(FMLConstructModEvent event) {
        // this is done to delay initialization of content to be after KJS has set up.

    }

    @SubscribeEvent
    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        CBCapabilities.register(event);
    }
}
