package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.CTNHBio;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CBMobEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECT = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CTNHBio.MODID);
//    public static final RegistryObject<MobEffect> DUMMY = MOB_EFFECT.register("dummy", new MobEffect())
}
