package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;

import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.moguang.ctnhbio.api.block.TransparentMetaMachineBlock;
import com.moguang.ctnhbio.client.Renderer.InvisibleRenderer;
import com.moguang.ctnhbio.common.machine.BasicLivingMachineBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBMachines {
    public static void init() {}
    public static final MachineDefinition[] BASIC_LIVING_MACHINE = registerTransparentTieredMachines("basic_living_machine",
            (holder, tier) -> new BasicLivingMachineBlock(holder,tier,(tiers) -> tiers * 32000),
            (tier,builder) -> builder
                    //.langValue("%s Digital Well of Suffer".formatted(VNF[tier]))
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE)
                    .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE))
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
                            TransparentMetaMachineBlock::new,
                            MetaMachineItem::new,
                            MetaMachineBlockEntity::createBlockEntity)
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

}
