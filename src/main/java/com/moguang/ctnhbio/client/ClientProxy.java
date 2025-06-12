package com.moguang.ctnhbio.client;

import com.moguang.ctnhbio.common.CommonProxy;
import com.moguang.ctnhbio.common.entity.BasicMobEntity;
import com.moguang.ctnhbio.registry.CBEntities;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientProxy extends CommonProxy {
    public ClientProxy() {
        super();
        init();
    }

    public static void init() {

    }

    @SubscribeEvent
    public void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {


    }
}
