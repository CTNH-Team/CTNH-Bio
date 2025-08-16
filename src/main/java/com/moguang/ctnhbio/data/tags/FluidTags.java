package com.moguang.ctnhbio.data.tags;

import com.github.elenterius.biofactory.init.ModFluids;
import com.moguang.ctnhbio.registry.CBTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class FluidTags {
    public static void init(RegistrateTagsProvider<Fluid> provider) {
        create(provider, CBTags.NUTRIENT_FLUIDS, ModFluids.NUTRIENTS_FLUID.get());
    }

    public static void create(RegistrateTagsProvider<Fluid> provider, TagKey<Fluid> tagKey, Fluid... rls) {
        var builder = provider.addTag(tagKey);
        for (Fluid fluid : rls) {
            builder.addOptional(Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)));
        }
    }
}
