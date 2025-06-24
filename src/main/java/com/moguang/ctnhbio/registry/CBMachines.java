package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;

import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.client.Renderer.InvisibleRenderer;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBMachines {
    public static void init() {}
    public static final MachineDefinition[] BASIC_LIVING_MACHINE = registerTransparentTieredMachines("basic_living_machine",
            (holder, tier) -> new BasicLivingMachine(holder,tier,(tiers) -> tiers * 32000),
            (tier,builder) -> builder
                    //.langValue("%s Digital Well of Suffer".formatted(VNF[tier]))
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> InvisibleRenderer.INSTANCE)
                    //.recipeModifier(DigitalWosMachine::recipeModifier)
                    .register(),
            GTValues.tiersBetween(LV,UV));
    public static MachineDefinition[] registerTransparentTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            MachineDefinition::createDefinition,
                            holder -> factory.apply(holder, tier),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            LivingMetaMachineBlockEntity::createLivingBlockEntity)
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

}
