package com.moguang.ctnhbio.data.tags;

import com.github.elenterius.biofactory.init.ModFluids;
import com.moguang.ctnhbio.registry.CBTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static com.github.elenterius.biofactory.init.ModFluids.NUTRIENTS_FLUID;
import static com.github.elenterius.biomancy.init.ModFluids.ACID;

public class FluidTags {
    public static void init(RegistrateTagsProvider<Fluid> provider) {
        create(provider, CBTags.NUTRIENT_FLUIDS_TAG, NUTRIENTS_FLUID.get());
        create(provider, CBTags.ACID_FLUIDS_TAG, ACID.get());
    }

    public static void create(RegistrateTagsProvider<Fluid> provider, TagKey<Fluid> tagKey, Fluid... rls) {
        var builder = provider.addTag(tagKey);
        for (Fluid fluid : rls) {
            builder.addOptional(Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)));
        }
    }
}
