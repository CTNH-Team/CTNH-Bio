package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;

import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.client.Renderer.InvisibleRenderer;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.machine.braininavat.BrainInAVat;
import net.minecraft.resources.ResourceLocation;

import java.util.Locale;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBMachines {
    static {
        REGISTRATE.creativeModeTab(() -> CBCreativeModeTabs.ITEM);
    }
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
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> InvisibleRenderer.INSTANCE)
                    .register();
        }
    }

    public static final MachineDefinition[] BIOELECTRIC_FORGE = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, EV)) {
            BIOELECTRIC_FORGE[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_bioeclectric_forge",
                            MachineDefinition::createDefinition,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> InvisibleRenderer.INSTANCE)
                    .register();
        }
    }

    public static final MachineDefinition[] DECOMPOSER = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, EV)) {
            DECOMPOSER[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_decomposer",
                            MachineDefinition::createDefinition,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                    //.editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> InvisibleRenderer.INSTANCE)
                    .register();
        }
    }

    public static final MachineDefinition[] DIGESTER = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, EV)) {
            DIGESTER[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_digester",
                            MachineDefinition::createDefinition,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                    //.editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .renderer(() -> InvisibleRenderer.INSTANCE)
                    .register();
        }
    }

    public static final MachineDefinition[] BIOREACTOR = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, EV)) {
            BIOREACTOR[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_bioreactor",
                            MachineDefinition::createDefinition,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.BIOREACTOR_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                    //.editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
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
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
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
