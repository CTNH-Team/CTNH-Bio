package com.moguang.ctnhbio.registry;

import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.serum.AgeingSerum;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.common.serum.PrimordialSerum;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CBSerums {
    public static final DeferredRegister<Serum> SERUMS = DeferredRegister.create(CTNHBio.id("serum"), CTNHBio.MODID);
    public static final Supplier<IForgeRegistry<Serum>> REGISTRY = SERUMS.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<PrimordialSerum> PRIMORDIAL_SERUM = SERUMS.register("primordial_serum", () -> new PrimordialSerum(0xac4a5a));
}
