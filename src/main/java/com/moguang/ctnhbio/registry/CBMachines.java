package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.models.GTMachineModels;
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
import com.moguang.ctnhbio.machine.multiblock.part.ParabioticBridgePartMachine;
import com.moguang.ctnhbio.utils.CBMachineNames.*;

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
import com.moguang.ctnhbio.machine.multiblock.part.NeuralModelAccessorMachine;
import java.util.Locale;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.OVERLAY_ITEM_HATCH;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createOverlayCasingMachineModel;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;
import static com.moguang.ctnhbio.registry.CBMachines.brain_in_a_vat.story;
import static com.moguang.ctnhbio.registry.CBMachines.brain_in_a_vat.tooltip;
import static com.moguang.ctnhbio.utils.CBMachineNames.*;

@Suffix("machine")
public class CBMachines {
    public static final MachineDefinition[] BIOELECTRIC_FORGE = new MachineDefinition[GTValues.TIER_COUNT];
    public static final MachineDefinition[] DECOMPOSER = new MachineDefinition[GTValues.TIER_COUNT];
    public static final MachineDefinition[] DIGESTER = new MachineDefinition[GTValues.TIER_COUNT];
    public static final MachineDefinition[] BIOREACTOR = new MachineDefinition[GTValues.TIER_COUNT];
    public static final MachineDefinition[] BRAIN_IN_A_VAT = new MachineDefinition[GTValues.TIER_COUNT];
    public static MachineDefinition NEURAL_MODEL_ACCESSOR;

    public static MachineDefinition PARABIOTIC_BRIDGE;
//    @CN("反应器")
//    //@EN("reactor")
//    static Lang bioreactor_tooltip;

    static {
        REGISTRATE.creativeModeTab(() -> CBCreativeModeTabs.ITEM);
    }

    public static void init() {
        registerBioelectricForge();
        registerDecomposer();
        registerDigester();
        registerBioreactor();
        registerBrainInAVat();
        NEURAL_MODEL_ACCESSOR = REGISTRATE.machine("neural_model_accessor", NeuralModelAccessorMachine::new)
                .cnLangValue("数据模型接口")
                .langValue("Neural Model Accessor")
                .tier(LuV)
                .rotationState(RotationState.NON_Y_AXIS)
                .abilities(CBPartAbility.NEURAL_MODEL_ACCESSOR)
                .modelProperty(GTMachineModelProperties.IS_FORMED, false)
                .workableCasingModel(CTNHBio.id("block/casings/neural_cooling_conduit"),
                        GTCEu.id("block/multiblock/central_monitor"))
                .register();

        PARABIOTIC_BRIDGE = REGISTRATE
                .machine("parabiotic_bridge", ParabioticBridgePartMachine::new)
                .cnLangValue("联体桥")
                .langValue("Parabiotic Bridge")
                .tier(ZPM)
                .rotationState(RotationState.NON_Y_AXIS)
                .abilities(PartAbility.IMPORT_ITEMS, PartAbility.EXPORT_ITEMS)
                .model(createOverlayCasingMachineModel(CTNHBio.id("block/casings/primal_flesh_casing"),
                        CTNHBio.id("block/item_passthrough_hatch")
                        ))
                //.colorOverlayTieredHullModel(GTCEu.id("block/overlay/machine/overlay_pipe_in_emissive"), null, GTCEu.id("block/overlay/machine/" + OVERLAY_ITEM_HATCH))
                .register()
        ;
    }

    private static void registerBioelectricForge() {

        for (int tier : GTValues.tiersBetween(LV, IV)) {
            String id = "bioelectric_forge";
            BIOELECTRIC_FORGE[tier] = REGISTRATE
                    .livingMachine(tier,
                            id,
                            BasicLivingMachine::new,
                            (p, d) -> new LivingMetaMachineBlock(p, d) {
                                @Override
                                public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
                                    return Shapes.box(0, 0, 0, 1, 1.5, 1);
                                }
                            },
                            CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES,
                            false)
                    .cnLangValue(getCNName(id, tier))
                    .langValue(getENName(id, tier))
                    .register();
        }
    }

    private static void registerDecomposer() {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            String id  = "decomposer";
            DECOMPOSER[tier] = REGISTRATE
                    .livingMachine(tier,
                            id,
                            BasicLivingMachine::new,
                            LivingMetaMachineBlock::new,
                            CBRecipeTypes.DECOMPOSER_RECIPES,
                            true)
                    .cnLangValue(getCNName(id, tier))
                    .langValue(getENName(id, tier))
                    .register();
        }
    }

    private static void registerDigester() {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            String id = "digester";
            DIGESTER[tier] = REGISTRATE
                    .livingMachine(tier,
                            id,
                            BasicLivingMachine::new,
                            LivingMetaMachineBlock::new,
                            CBRecipeTypes.DIGEST_RECIPES,
                            false)
                    .cnLangValue(getCNName(id, tier))
                    .langValue(getENName(id, tier))
                    .recipeModifiers(
                            CBRecipeModifier::digesterRecipeModifier,
                            GTRecipeModifiers.OC_NON_PERFECT,
                            CBRecipeModifier::batchMode)
                    .register();
        }
    }

    private static void registerBioreactor() {
        for (int tier : GTValues.tiersBetween(LV, IV)) {
            String id = "bioreactor";
            BIOREACTOR[tier] = REGISTRATE
                    .livingMachine(tier,
                            id,
                            BasicLivingMachine::new,
                            LivingMetaMachineBlock::new,
                            CBRecipeTypes.BIO_REACTOR_RECIPES,
                            true)
                    .cnLangValue(getCNName(id, tier))
                    .langValue(getENName(id, tier))
                    .register();
        }
    }
    @Suffix("brain_in_a_vat")
    static class brain_in_a_vat{
        @CN({
                "§3自动化思考",
                "§r电量和营养充足时,提供%d算力",
                "§r超频可提供双倍算力，但会对大脑造成不可逆损伤"
        })
        @EN({
                "§3Automated Thinking",
                "§rProvides %d compute power when power and nutrition are sufficient",
                "§rOverclocking provides double compute power, but causes irreversible brain damage"
        })
        static Lang[] tooltip;

        @CN({
                "它觉得自己是一名出色的格雷员工",
                "它正在优化铂系金属处理产线",
                "它喜欢熬夜玩CTNH，这样不太好",
                "它又开始自我怀疑了，重启一下吧"
        })
        @EN({
                "It believes it's a top-notch GregTech employee",
                "It's busy streamlining the platinum-group metal processing line",
                "It loves staying up late playing CTNH — not the healthiest habit",
                "It's doubting itself again... time for a reboot"
        })
        static Lang[] story;
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
                    .tooltips(story[tier - 3].translate().withStyle(ChatFormatting.GRAY))
                    .register();
        }
    }


}