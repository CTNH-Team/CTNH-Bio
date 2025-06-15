package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;

import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.moguang.ctnhbio.client.Renderer.InvisibleRenderer;
import com.moguang.ctnhbio.common.machine.BasicLivingMachineBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBMachines {
    public static void init() {}
    public static final MachineDefinition[] BASIC_LIVING_MACHINE = registerTieredMachines("basic_living_machine",
            (holder, tier) -> new BasicLivingMachineBlock(holder,tier,(tiers) -> tiers * 32000),
            (tier,builder) -> builder
                    //.langValue("%s Digital Well of Suffer".formatted(VNF[tier]))
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE)
                    //.editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id("digital_well_of_suffer"),CTNHRecipeTypes.DIGITAL_WELL_OF_SUFFER))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> InvisibleRenderer.INSTANCE)

                    //.recipeModifier(DigitalWosMachine::recipeModifier)
                    //.workableTieredHullRenderer(GTCEu.id("block/machines/digital_well_of_suffer"))
                    .register(),
            GTValues.tiersBetween(LV,UV));
    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

}
