package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.blockentity.MetaMachineBlockEntity;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;

import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.client.Renderer.InvisibleRenderer;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.machine.braininavat.Brain;
import com.moguang.ctnhbio.machine.braininavat.BrainInAVat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBMachines {
    public static void init() {}

    public static final MachineDefinition[] BASIC_LIVING_MACHINE = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, UV)) {
            BASIC_LIVING_MACHINE[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_basic_living_machine",
                            MachineDefinition::createDefinition,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> InvisibleRenderer.INSTANCE)
                    .register();
        }
    }

    public static final MachineDefinition[] BRAIN_IN_A_VAT = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(HV, LuV)) {
            BRAIN_IN_A_VAT[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_brain_in_a_vat",
                            MachineDefinition::createDefinition,
                            //holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000),
                            holder -> new BrainInAVat(holder, tier, (tiers) -> tiers * 32000),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) ->
                                    LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.BRAIN_IN_A_VAT_BRAIN.get())
                                            .setEntityOffset(0.5, 0.6, 0.5)
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE)
                    .blockProp(prop -> prop
                            .noOcclusion()
                            .lightLevel(state ->0)
                    )
                    .rotationState(RotationState.NON_Y_AXIS)
                    .modelRenderer(() -> new ResourceLocation("ctnhbio", "block/vat"))
                    .register();
        }
    }

}
