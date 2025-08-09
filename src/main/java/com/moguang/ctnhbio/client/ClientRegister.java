package com.moguang.ctnhbio.client;

import com.gregtechceu.gtceu.api.GTValues;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.machine.bioelectricforge.BioelectricForgeBlockEntity;
import com.moguang.ctnhbio.machine.bioelectricforge.BioelectricForgeBlockEntityRenderer;
import com.moguang.ctnhbio.machine.bioelectricforge.BioelectricForgeEntityModel;
import com.moguang.ctnhbio.machine.bioreactor.BioReactorBlockEntity;
import com.moguang.ctnhbio.machine.bioreactor.BioReactorBlockEntityRenderer;
import com.moguang.ctnhbio.machine.braininavat.BrainModel;
import com.moguang.ctnhbio.machine.digester.DigesterBlockEntity;
import com.moguang.ctnhbio.machine.digester.DigesterBlockEntityRenderer;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshBlockEntity;
import com.moguang.ctnhbio.machine.greatflesh.GreatFleshRenderer;
import com.moguang.ctnhbio.registry.CBMachines;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.gregtechceu.gtceu.api.GTValues.EV;
import static com.gregtechceu.gtceu.api.GTValues.LV;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegister {
    @Deprecated
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (int tier : GTValues.tiersBetween(LV, EV)){
            event.registerBlockEntityRenderer((BlockEntityType<BioelectricForgeBlockEntity>)CBMachines.BIOELECTRIC_FORGE[tier].getBlockEntityType(), BioelectricForgeBlockEntityRenderer::new);
            event.registerBlockEntityRenderer((BlockEntityType<BioReactorBlockEntity>)CBMachines.BIOREACTOR[tier].getBlockEntityType(), BioReactorBlockEntityRenderer::new);
            //event.registerBlockEntityRenderer((BlockEntityType<DigesterBlockEntity>)DIGESTER[tier].getBlockEntityType(), DigesterBlockEntityRenderer::new);
        }
    }

    @SubscribeEvent
    public static void onLayerRegister(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BrainModel.LAYER_LOCATION, BrainModel::createBodyLayer);
        //event.registerLayerDefinition(BioReactorModel.LAYER_LOCATION, BioReactorModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
        event.register(BioelectricForgeEntityModel.MODEL);
    }
}
