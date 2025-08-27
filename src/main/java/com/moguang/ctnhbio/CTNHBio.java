package com.moguang.ctnhbio;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import com.moguang.ctnhbio.client.ClientProxy;
import com.moguang.ctnhbio.common.CommonProxy;
import com.moguang.ctnhbio.data.recipe.VanillaRecipeProvider;
import com.moguang.ctnhbio.event.EventHandler;
import com.moguang.ctnhbio.registry.CBRegistrate;
import com.moguang.ctnhbio.registry.CBSerums;
import com.mojang.logging.LogUtils;
import com.simibubi.create.infrastructure.data.CreateDatagen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
        modEventBus.addGenericListener(GTRecipeCategory.class, EventHandler::onRecipeCategoryRegister);
        modEventBus.addGenericListener(SoundEntry.class, EventHandler::onSoundRegister);
        //modEventBus.addGenericListener(ChanceLogic.class,EventHandler::registerChanceLogic);

        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);


    }

    public static ResourceLocation id(String name) {return ResourceLocation.tryParse(MODID + ":" + name); }
    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(VanillaRecipeProvider::buildBrewingRecipes);
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
