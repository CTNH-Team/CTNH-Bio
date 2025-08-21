package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;

import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.item.LivingMetaMachineItem;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.api.recipe.CBRecipeModifier;
import com.moguang.ctnhbio.client.Renderer.ColorableMachineBlockEntityRenderer;
import com.moguang.ctnhbio.client.Renderer.ColorableMachineItemRenderer;
import com.moguang.ctnhbio.client.Renderer.LivingMetaMachineBlockEntityRenderer;
import com.moguang.ctnhbio.client.model.BioReactorModel;
import com.moguang.ctnhbio.client.model.BioelectricForgeModel;
import com.moguang.ctnhbio.client.model.DigesterModel;
import com.moguang.ctnhbio.client.model.VatModel;
import com.moguang.ctnhbio.machine.bioelectricforge.BioelectricForgeBlockEntity;
import com.moguang.ctnhbio.machine.bioreactor.BioReactorBlockEntity;
import com.moguang.ctnhbio.machine.braininavat.BrainInAVatMachine;
import com.moguang.ctnhbio.machine.bioelectricforge.BioelectricForgeMachineBlock;

import com.moguang.ctnhbio.machine.digester.DigesterMachine;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.Locale;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBMachines {
    static {
        REGISTRATE.creativeModeTab(() -> CBCreativeModeTabs.ITEM);
    }
    public static void init() {}

    //public static final MachineDefinition[] BASIC_LIVING_MACHINE = new MachineDefinition[GTValues.TIER_COUNT];
    static {
//        for (int tier : GTValues.tiersBetween(LV, UV)) {
//            BASIC_LIVING_MACHINE[tier] = REGISTRATE
//                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_basic_living_machine",
//                            MachineDefinition::new,
//                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000, 100),
//                            LivingMetaMachineBlock::new,
//                            MetaMachineItem::new,
//                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
//                    )
//                    .tier(tier)
//                    .recipeType(CBRecipeTypes.BASIC_LIVING_RECIPES)
//                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(GTCEu.id("basic_living_machine"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
//                    .rotationState(RotationState.NON_Y_AXIS)
//                    .onBlockEntityRegister( blockEntityBlockEntityType ->
//                            BlockEntityRenderers.register(blockEntityBlockEntityType, LivingMetaMachineBlockEntityRenderer::new))
//                    .simpleModel(ResourceLocation.tryBuild("minecraft", "block/oak_log"))
//                    .blockProp(p -> p.noOcclusion()
//                            .isViewBlocking((state, level, pos) -> false))
//                    .register();
//        }
    }

    public static final MachineDefinition[] BIOELECTRIC_FORGE = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            BIOELECTRIC_FORGE[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_bioeclectric_forge",
                            MachineDefinition::new,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000, 200),
                            BioelectricForgeMachineBlock::new,
                            (b, p) -> new LivingMetaMachineItem(b, p, () -> new ColorableMachineItemRenderer(new BioelectricForgeModel())),
                            (type, pos, state) -> new BioelectricForgeBlockEntity(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
                    .recipeType(CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(CTNHBio.id("bioelectric_forge"),CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .simpleModel(ResourceLocation.tryBuild("biomancy", "block/flesh"))
                    .onBlockEntityRegister(beType -> {
                        @SuppressWarnings("unchecked")
                        BlockEntityType<LivingMetaMachineBlockEntity> typed = (BlockEntityType<LivingMetaMachineBlockEntity>) (BlockEntityType<?>)beType;
                        BlockEntityRenderers.register(typed, ctx -> new ColorableMachineBlockEntityRenderer(new BioelectricForgeModel(), true));
                    })
                    .register();
        }
    }

    public static final MachineDefinition[] DECOMPOSER = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            DECOMPOSER[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_decomposer",
                            MachineDefinition::new,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000, 200),
                            LivingMetaMachineBlock::new,
                            MetaMachineItem::new,
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
                    .recipeType(CBRecipeTypes.DECOMPOSER_RECIPES)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(CTNHBio.id("decompose"),CBRecipeTypes.DECOMPOSER_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .simpleModel(ResourceLocation.tryBuild("biomancy", "block/flesh"))

                    .register();
        }
    }

    public static final MachineDefinition[] DIGESTER = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            DIGESTER[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_digester",
                            MachineDefinition::new,
                            holder -> new DigesterMachine(holder, tier, (tiers) -> tiers * 32000, 200),
                            LivingMetaMachineBlock::new,
                            (b, p) -> new LivingMetaMachineItem(b, p, () -> new ColorableMachineItemRenderer(new DigesterModel())),
                            (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeModifiers(DigesterMachine::recipeModifier, GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
                    .recipeType(CBRecipeTypes.DIGEST_RECIPES)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(CTNHBio.id("digester"),CBRecipeTypes.DIGEST_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)

                    .onBlockEntityRegister(beType -> {
                        @SuppressWarnings("unchecked")
                        BlockEntityType<LivingMetaMachineBlockEntity> typed = (BlockEntityType<LivingMetaMachineBlockEntity>) (BlockEntityType<?>)beType;
                        BlockEntityRenderers.register(typed, ctx -> new ColorableMachineBlockEntityRenderer(new DigesterModel()));
                    })
                    .simpleModel(ResourceLocation.tryBuild("biomancy", "block/flesh"))
                    .register();
        }
    }

    public static final MachineDefinition[] BIOREACTOR = new MachineDefinition[GTValues.TIER_COUNT];
    static {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            BIOREACTOR[tier] = REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_bioreactor",
                            MachineDefinition::new,
                            holder -> new BasicLivingMachine(holder, tier, (tiers) -> tiers * 32000, 200),
                            LivingMetaMachineBlock::new,
                            (b, p) -> new LivingMetaMachineItem(b, p, () -> new ColorableMachineItemRenderer(new BioReactorModel())),
                            (type, pos, state) -> new BioReactorBlockEntity(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get())
                    )
                    .tier(tier)
                    .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
                    .recipeType(CBRecipeTypes.BIO_REACTOR_RECIPES)
                    .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(CTNHBio.id("bioreactor"),CBRecipeTypes.BIO_REACTOR_RECIPES))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .simpleModel(ResourceLocation.tryBuild("biomancy", "block/flesh"))
                    .onBlockEntityRegister(beType -> {
                        @SuppressWarnings("unchecked")
                        BlockEntityType<BioReactorBlockEntity> typed = (BlockEntityType<BioReactorBlockEntity>) (BlockEntityType<?>)beType;
                        BlockEntityRenderers.register(typed, ctx -> new ColorableMachineBlockEntityRenderer(new BioReactorModel(), true));
                    })
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
                            holder -> new BrainInAVatMachine(holder, tier, (tiers) -> tiers * 32000, 200),
                            LivingMetaMachineBlock::new,
                            (b, p) -> new LivingMetaMachineItem(b, p, () -> new ColorableMachineItemRenderer(new VatModel())),
                            (type, pos, state) ->
                                    LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.BRAIN_IN_A_VAT_BRAIN.get())
                                            .setEntityOffset(0.5, 0.6, 0.5)
                    )

                    .tier(tier)
                    .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
                    .recipeType(CBRecipeTypes.BRAIN_IN_A_VAT_RECIPES)
                    .blockProp(prop -> prop
                            .noOcclusion()
                            .lightLevel(state ->0)
                    )
                    .tooltips(Component.translatable("ctnhbio.machine." + VN[tier].toLowerCase(Locale.ROOT) +"_brain_in_a_vat.tooltip.0").withStyle(ChatFormatting.GRAY))
                    .rotationState(RotationState.NON_Y_AXIS)
                    .simpleModel(ResourceLocation.tryBuild("minecraft", "block/air"))
                    .onBlockEntityRegister(beType -> {
                        @SuppressWarnings("unchecked")
                        BlockEntityType<BioReactorBlockEntity> typed = (BlockEntityType<BioReactorBlockEntity>) (BlockEntityType<?>)beType;
                        BlockEntityRenderers.register(typed, ctx -> new ColorableMachineBlockEntityRenderer(new VatModel(), true));
                    })
                    .register();
        }
    }

}
