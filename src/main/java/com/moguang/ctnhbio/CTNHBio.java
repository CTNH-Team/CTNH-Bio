package com.moguang.ctnhbio;

import com.github.elenterius.biomancy.init.ModEnchantments;
import com.gregtechceu.gtceu.api.data.DimensionMarker;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.moguang.ctnhbio.client.ClientProxy;
import com.moguang.ctnhbio.common.CommonProxy;
import com.moguang.ctnhbio.event.EventHandler;
import com.moguang.ctnhbio.registry.CBRegistrate;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@SuppressWarnings("removal")
@Mod(CTNHBio.MODID)
public class CTNHBio
{
    public static final String MODID = "ctnhbio";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final CBRegistrate REGISTRATE = CBRegistrate.create();



    public CTNHBio()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::onRegisterEntityRenderers);

        modEventBus.addGenericListener(MachineDefinition.class, EventHandler::registerMachines);
        modEventBus.addGenericListener(GTRecipeType.class, EventHandler::registerRecipeTypes);
        modEventBus.addGenericListener(RecipeConditionType.class, EventHandler::registerRecipeConditions);
        modEventBus.addGenericListener(ChanceLogic.class,EventHandler::registerChanceLogic);
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    }

    public static ResourceLocation id(String name) {return new ResourceLocation(MODID, name); }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
//            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    private void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {

    }


}
