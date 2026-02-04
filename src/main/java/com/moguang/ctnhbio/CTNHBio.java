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
import tech.vixhentx.mcmod.ctnhlib.langprovider.LangProcessor;

@SuppressWarnings("removal")
@Mod(CTNHBio.MODID)
public class CTNHBio
{
    public static final String MODID = "ctnhbio";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final CBRegistrate REGISTRATE = CBRegistrate.create();

    public CTNHBio()
    {
        LangProcessor langProcessor = new LangProcessor(REGISTRATE);
        langProcessor.processAll();
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }

    public static ResourceLocation id(String name) {return ResourceLocation.tryParse(MODID + ":" + name); }

}
