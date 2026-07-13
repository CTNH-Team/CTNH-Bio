package com.moguang.ctnhbio.integration.emi;

import net.minecraft.world.item.Item;

import com.mojang.logging.LogUtils;
import com.yanny.ali.configuration.GameplayLootCategory;
import dev.emi.emi.api.*;
import dev.emi.emi.api.stack.EmiStack;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.Supplier;

import static com.github.elenterius.biomancy.init.ModItems.*;

@EmiEntrypoint
public class CTNHBioEmiPlugin implements EmiPlugin {

    private static final Logger LOGGER = LogUtils.getLogger();

    public List<GameplayLootCategory> gameplayCategories;

    @Override
    public void register(EmiRegistry registry) {}

    @Override
    public void initialize(EmiInitRegistry registry) {
        List<Supplier<? extends Item>> disabled = List.of(
                BIO_FORGE,
                BIO_LAB,
                DECOMPOSER,
                DIGESTER,

                STONE_POWDER,
                MINERAL_FRAGMENT,
                GEM_FRAGMENTS,

                REGENERATIVE_FLUID,
                WITHERING_OOZE,
                HORMONE_SECRETION,
                TOXIN_EXTRACT,
                BILE,
                VOLATILE_FLUID,
                VIAL,
                ORGANIC_COMPOUND,
                UNSTABLE_COMPOUND,
                GENETIC_COMPOUND,
                EXOTIC_COMPOUND,
                HEALING_ADDITIVE,
                DECAYING_ADDITIVE,
                REJUVENATION_SERUM,
                AGEING_SERUM,
                ENLARGEMENT_SERUM,
                SHRINKING_SERUM,
                BREEDING_STIMULANT,
                ABSORPTION_BOOST,
                CLEANSING_SERUM,
                INSOMNIA_CURE,
                FRENZY_SERUM

        );
        for (var item : disabled) {
            registry.disableStack(EmiStack.of(item.get()));
        }
    }
}
