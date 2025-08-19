package com.moguang.ctnhbio.client;

import com.gregtechceu.gtceu.api.GTValues;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.machine.bioelectricforge.BioelectricForgeBlockEntity;
import com.moguang.ctnhbio.machine.braininavat.BrainModel;
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

    @SubscribeEvent
    public static void onLayerRegister(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BrainModel.LAYER_LOCATION, BrainModel::createBodyLayer);
        //event.registerLayerDefinition(BioReactorModel.LAYER_LOCATION, BioReactorModel::createBodyLayer);
    }

}
