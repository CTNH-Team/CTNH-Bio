package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.client.Renderer.LivingMetaMachineBlockEntityRenderer;
import com.moguang.ctnhbio.machine.braininavat.BrainInAVat;
import com.moguang.ctnhbio.machine.bioelectricforge.BioelectricForgeMachineBlock;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

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
                            MachineDefinition::new,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000, 100),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.BIO_REACTOR_RECIPES)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .onBlockEntityRegister( blockEntityBlockEntityType ->
                            BlockEntityRenderers.register(blockEntityBlockEntityType, LivingMetaMachineBlockEntityRenderer::new))
                    .simpleModel(new ResourceLocation("minecraft", "block/oak_log"))
                    .blockProp(p -> p.noOcclusion()
                            .isViewBlocking((state, level, pos) -> false))
                    .register();
        }
    }

    public static final MachineDefinition[] BIOELECTRIC_FORGE = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, EV)) {
            BIOELECTRIC_FORGE[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_bioeclectric_forge",
                            MachineDefinition::new,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000, 200),
                            BioelectricForgeMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .model((dataGenContext, gtBlockstateProvider, machineModelBuilder) ->
                            machineModelBuilder.addModels(machineModelBuilder.partialState(),
                                    ConfiguredModel.builder().modelFile(new ModelFile.UncheckedModelFile(CTNHBio.id("block/bioelectric_forge"))).buildLast()))

                    .register();
        }
    }

    public static final MachineDefinition[] DECOMPOSER = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, EV)) {
            DECOMPOSER[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_decomposer",
                            MachineDefinition::new,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000, 200),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.DECOMPOSER_RECIPES)
                    //.editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .simpleModel(new ResourceLocation("minecraft", "block/oak_log"))
                    .register();
        }
    }

    public static final MachineDefinition[] DIGESTER = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, EV)) {
            DIGESTER[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_digester",
                            MachineDefinition::new,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000, 200),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.DIGEST_RECIPES)
                    //.editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .simpleModel(new ResourceLocation("minecraft", "block/oak_log"))
                    .register();
        }
    }

    public static final MachineDefinition[] BIOREACTOR = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, EV)) {
            BIOREACTOR[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_bioreactor",
                            MachineDefinition::new,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000, 200),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.BIOREACTOR_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeType(CBRecipeTypes.BIO_REACTOR_RECIPES)
                    //.editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .simpleModel(new ResourceLocation("minecraft", "block/oak_log"))
                    .register();
        }
    }

    public static final MachineDefinition[] BRAIN_IN_A_VAT = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(HV, LuV)) {
            BRAIN_IN_A_VAT[tier] = REGISTRATE
                    .machine(VN[tier].toLowerCase(Locale.ROOT) + "_brain_in_a_vat",
                            MachineDefinition::new,
                            //holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000),
                            holder -> new BrainInAVat(holder, tier, (tiers) -> tiers * 32000, 200),
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
                    .model((dataGenContext, gtBlockstateProvider, machineModelBuilder) ->
                            machineModelBuilder.addModels(machineModelBuilder.partialState(),
                                    ConfiguredModel.builder().modelFile(new ModelFile.UncheckedModelFile(CTNHBio.id("block/vat"))).buildLast()))
                    .register();
        }
    }

}
