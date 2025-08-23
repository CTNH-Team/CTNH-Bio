package com.moguang.ctnhbio.registry;

import com.github.elenterius.biomancy.BiomancyMod;
import com.gregtechceu.gtceu.api.sound.SoundEntry;

import static com.gregtechceu.gtceu.common.registry.GTRegistration.REGISTRATE;

public class CBSoundEntries {
    public static final SoundEntry EAT = REGISTRATE.sound(BiomancyMod.createRL("block.decomposer.eat")).build();
    public static final SoundEntry DIGESTER_CRAFTING = REGISTRATE.sound(BiomancyMod.createRL("block.digester.crafting")).build();
    public static final SoundEntry DECOMPOSER_CRAFTING = REGISTRATE.sound(BiomancyMod.createRL("block.decomposer.crafting")).build();
    public static void init() {

    }
}
