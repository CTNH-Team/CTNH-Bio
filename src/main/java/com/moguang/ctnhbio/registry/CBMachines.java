package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.block.LivingMultiMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.item.LivingMetaMachineItem;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.api.recipe.CBRecipeModifier;
import com.moguang.ctnhbio.client.Renderer.ColorableMachineBlockEntityRenderer;
import com.moguang.ctnhbio.client.Renderer.ColorableMachineItemRenderer;
import com.moguang.ctnhbio.client.model.*;
import com.moguang.ctnhbio.machine.braininavat.BrainInAVatMachine;
import com.moguang.ctnhbio.machine.bioelectricforge.BioelectricForgeMachineBlock;

import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Domain;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.Suffix;

import java.util.Locale;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;
import static com.moguang.ctnhbio.registry.CBMachines.brain_in_a_vat.tooltip;

@Suffix("machine")
public class CBMachines {
    public static final MachineDefinition[] BIOELECTRIC_FORGE = new MachineDefinition[GTValues.TIER_COUNT];
    public static final MachineDefinition[] DECOMPOSER = new MachineDefinition[GTValues.TIER_COUNT];
    public static final MachineDefinition[] DIGESTER = new MachineDefinition[GTValues.TIER_COUNT];
    public static final MachineDefinition[] BIOREACTOR = new MachineDefinition[GTValues.TIER_COUNT];
    public static final MachineDefinition[] BRAIN_IN_A_VAT = new MachineDefinition[GTValues.TIER_COUNT];

    @CN("反应器")
    //@EN("reactor")
    static Lang bioreactor_tooltip;

    static {
        REGISTRATE.creativeModeTab(() -> CBCreativeModeTabs.ITEM);
    }

    public static void init() {
        registerBioelectricForge();
        registerDecomposer();
        registerDigester();
        registerBioreactor();
        registerBrainInAVat();
    }

    private static void registerBioelectricForge() {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            BIOELECTRIC_FORGE[tier] = REGISTRATE
                    .livingMachine(tier,
                            "bioelectric_forge",
                            BasicLivingMachine::new,
                            (p, d) -> new LivingMetaMachineBlock(p, d) {
                                @Override
                                public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                                    return Shapes.box(0, 0, 0, 1, 1.5, 1);
                                }
                            },
                            CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES,
                            false)
                    .register();
        }
    }

    private static void registerDecomposer() {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            DECOMPOSER[tier] = REGISTRATE
                    .livingMachine(tier,
                            "decomposer",
                            BasicLivingMachine::new,
                            LivingMetaMachineBlock::new,
                            CBRecipeTypes.DECOMPOSER_RECIPES,
                            true)
                    .register();
        }
    }

    private static void registerDigester() {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            DIGESTER[tier] = REGISTRATE
                    .livingMachine(tier,
                            "digester",
                            BasicLivingMachine::new,
                            LivingMetaMachineBlock::new,
                            CBRecipeTypes.DIGEST_RECIPES,
                            false)
                    .recipeModifiers(
                            CBRecipeModifier::digesterRecipeModifier,
                            GTRecipeModifiers.OC_NON_PERFECT,
                            CBRecipeModifier::batchMode)
                    .register();
        }
    }

    private static void registerBioreactor() {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            BIOREACTOR[tier] = REGISTRATE
                    .livingMachine(tier,
                            "bioreactor",
                            BasicLivingMachine::new,
                            LivingMetaMachineBlock::new,
                            CBRecipeTypes.BIO_REACTOR_RECIPES,
                            true)
                    .tooltips(bioreactor_tooltip.translate())
                    .register();
        }
    }

    static class brain_in_a_vat{
        @CN({
                "§3自动化思考",
                "§r电量和营养充足时,提供%d算力",
                "§r超频可提供双倍算力，但会对大脑造成不可逆损伤"
        })
        @EN({
                "1",
                "2",
                "3"
        })
        static Lang[] tooltip;
    }


    private static void registerBrainInAVat() {
        for (int tier : GTValues.tiersBetween(HV, LuV)) {
            BRAIN_IN_A_VAT[tier] = REGISTRATE
                    .livingMachine(tier,
                            "brain_in_a_vat",
                            BrainInAVatMachine::new,
                            LivingMetaMachineBlock::new,
                            (type, pos, state) ->
                                    new LivingMetaMachineBlockEntity<>(type, pos, state, CBEntities.BRAIN_IN_A_VAT_BRAIN.get())
                                            .setEntityOffset(0.5, 0.6, 0.5),
                            CBRecipeTypes.BRAIN_IN_A_VAT_RECIPES,
                            true)
                    .editableUI(null)
                    .tooltips(
                            tooltip[0].translate(),
                            tooltip[1].translate(tier >= GTValues.HV ? 1 << (tier - GTValues.HV) : 0),
                            tooltip[2].translate()
                    )
                    .tooltips(Component.translatable("ctnhbio.machine." + VN[tier].toLowerCase(Locale.ROOT) + "_brain_in_a_vat.tooltip.0").withStyle(ChatFormatting.GRAY))
                    .register();
        }
    }
}