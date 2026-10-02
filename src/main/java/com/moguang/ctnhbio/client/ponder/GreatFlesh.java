package com.moguang.ctnhbio.client.ponder;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.data.materials.CommonMaterials;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMultiblocks;
import tech.vixhentx.mcmod.ctnhlib.client.ponder.ui.MachineUI;

public final class GreatFlesh {

    /**
     * MV 输入总成：一块方块上同时有 6 格物品与 3 个储罐，这条配方的物品和流体一起从这里进。
     *
     * <p>
     * 用总成而不是物品总线 + 流体仓：同样占一块方块，面板也只占一块。等级按需求挑——
     * LV 的 4 格 / 2 罐装不下这条配方的 6 份物品栈与 3 种化合物。
     */
    private static final MachineUI INPUT_HATCH_UI = MachineUI.of(GTMachines.DUAL_IMPORT_HATCH[GTValues.MV])
            .scale(0.6f);

    private GreatFlesh() {}

    public static void growth(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHBioPonderSceneBuilder scene = new CTNHBioPonderSceneBuilder(builder);
        scene.title("great_flesh_growth", "How the Great Flesh grows", "巨型肉块如何生长",
                "Great Flesh Growth", "巨型肉块生长");
        scene.init5x5(util);
        scene.idle(40);

        var controllerPos = util.grid().at(2, 2, 1);
        var controller = util.select().position(2, 2, 1);

        scene.world().showSection(controller, Direction.DOWN);
        scene.showText(140,
                "Place the Great Flesh controller. It begins as a living core rather than a finished multiblock.",
                "先放置巨型肉块主方块；它会从一个活体核心开始，而不是直接生成完整多方块。")
                .pointAt(util.vector().blockSurface(controllerPos, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(170);

        scene.overlay().showControls(
                util.vector().blockSurface(controllerPos, Direction.NORTH),
                Pointing.LEFT, 70)
                .rightClick()
                .withItem(Items.ROTTEN_FLESH.getDefaultInstance());
        scene.showText(140,
                "Feed any edible item to add nutrients. The machine uses its nutrient reserve while it grows.",
                "给主方块喂食可食用物品以补充营养；生长会消耗营养储备。")
                .pointAt(util.vector().blockSurface(controllerPos, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(170);

        scene.showText(150,
                "Growth is gradual: the machine places one flesh block at a time, consuming 1 nutrient for each block.",
                "生长是逐格进行的：每放置一块血肉就消耗 1 点营养。")
                .pointAt(util.vector().blockSurface(controllerPos, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(170);

        int[][] growthOrder = {
                { 1, 1, 1 }, { 2, 1, 1 }, { 3, 1, 1 },
                { 1, 1, 2 }, { 2, 1, 2 }, { 3, 1, 2 },
                { 1, 1, 3 }, { 2, 1, 3 }, { 3, 1, 3 },
                { 1, 2, 1 }, { 2, 2, 1 }, { 3, 2, 1 },
                { 1, 2, 2 }, { 2, 2, 2 }, { 3, 2, 2 },
                { 1, 2, 3 }, { 2, 2, 3 }, { 3, 2, 3 },
                { 1, 3, 1 }, { 2, 3, 1 }, { 3, 3, 1 },
                { 1, 3, 2 }, { 2, 3, 2 }, { 3, 3, 2 },
                { 1, 3, 3 }, { 2, 3, 3 }, { 3, 3, 3 }
        };
        for (int[] pos : growthOrder) {
            if (pos[0] == 2 && pos[1] == 2 && pos[2] == 1) continue;
            scene.world().showSection(util.select().position(pos[0], pos[1], pos[2]), Direction.DOWN);
            scene.idle(20);
        }

        scene.showText(160,
                "When every flesh block is present, the 3x3x3 structure is formed and the Great Flesh can accept a differentiation recipe.",
                "全部血肉方块长出后，3×3×3 结构才成型，巨型肉块才能接受分化配方。")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 2, 2), Direction.NORTH))
                .attachKeyFrame();
        scene.idle(190);
        scene.markAsFinished();
    }

    public static void differentiation(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHBioPonderSceneBuilder scene = new CTNHBioPonderSceneBuilder(builder);
        scene.title("great_flesh_differentiation", "How to differentiate the Great Flesh", "如何进行巨型肉块分化",
                "Great Flesh Differentiation", "巨型肉块分化");
        scene.init5x5(util);
        scene.idle(40);

        var controllerPos = util.grid().at(2, 2, 1);
        var controller = util.select().position(2, 2, 1);
        var flesh = util.select().fromTo(1, 1, 1, 3, 3, 3).substract(controller);
        var inputHatchPos = util.grid().at(1, 1, 1);
        var energyInputPos = util.grid().at(3, 1, 1);

        scene.world().showSection(controller, Direction.DOWN);
        scene.world().showSection(flesh, Direction.DOWN);
        scene.showText(150,
                "Start only after the Great Flesh has finished growing and its 3x3x3 structure is complete.",
                "必须等巨型肉块完成生长、3×3×3 结构成型后，再开始分化。")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 2, 2), Direction.NORTH))
                .attachKeyFrame();
        scene.idle(180);

        scene.showText(180,
                "Provide the remaining item, fluid, energy, and nutrient inputs required by the selected recipe. The machine selects the corresponding Great Flesh differentiation recipe based on the input item.",
                "提供选定配方其余所需的物品、流体、电力和营养。机器会根据输入物品选择对应的巨型肉块分化配方。")
                .pointAt(util.vector().centerOf(inputHatchPos))
                .attachKeyFrame();

        // 物品与流体都要：直接用 MV 输入总成——一块方块 6 格物品 + 3 个储罐，
        // 正好装下这条配方的 6 份物品栈与 3 种化合物。
        scene.world().setBlock(inputHatchPos,
                GTMachines.DUAL_IMPORT_HATCH[GTValues.MV].defaultBlockState()
                        .setValue(BlockStateProperties.FACING, Direction.NORTH),
                true);
        scene.overlay().showControls(
                util.vector().blockSurface(inputHatchPos, Direction.NORTH),
                Pointing.DOWN, 30)
                .rightClick()
                .withItem(GTMachines.DUAL_IMPORT_HATCH[GTValues.MV].asStack());
        // 以分化出意识装配机的那条配方为例：物品进格、流体进罐，一块面板全演完。
        scene.showUI(INPUT_HATCH_UI).at(util.vector().topOf(inputHatchPos))
                .forMachine(inputHatchPos)
                .slot(0)
                .withItem(new ItemStack(CBItems.OMNI_CORE.get(), 2), 20)
                .slot(1)
                .withItem(new ItemStack(GTItems.QUBIT_CENTRAL_PROCESSING_UNIT.get(), 16), 20)
                .slot(2)
                .withItem(new ItemStack(ChemicalHelper.get(TagPrefix.plateDense, CommonMaterials.BLOODSTEEL).getItem(),
                        12), 20)
                .slot(3)
                .withItem(new ItemStack(ModItems.CREATOR_MIX.get(), 8), 20)
                .tank(0)
                .withFluid(OrganicMaterials.Unstable_Compound.getFluid(6000), 20)
                .tank(1)
                .withFluid(OrganicMaterials.Genetic_Compound.getFluid(6000), 20)
                .tank(2)
                .withFluid(OrganicMaterials.Heterogeneous_Compound.getFluid(6000), 20)
                .outlineSlot(0, 20)
                .outlineTank(0, 20)
                .show(190);
        scene.idle(205);

        scene.world().setBlock(energyInputPos,
                GTMachines.ENERGY_INPUT_HATCH[GTValues.ZPM].defaultBlockState()
                        .setValue(BlockStateProperties.FACING, Direction.NORTH),
                true);
        scene.overlay().showControls(
                util.vector().blockSurface(energyInputPos, Direction.NORTH),
                Pointing.DOWN, 10)
                .rightClick()
                .withItem(GTMachines.ENERGY_INPUT_HATCH[GTValues.ZPM].asStack());
        scene.idle(150);

        scene.showText(160,
                "The input item matched by the recipe determines the result: Cogni Assembler, Circulatory System, or Weatherer.",
                "输入物品匹配的配方决定分化结果：意识装配机、循环系统或风化器。")
                .pointAt(util.vector().blockSurface(controllerPos, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(190);

        scene.world().replaceBlocks(flesh, Blocks.AIR.defaultBlockState(), true);
        scene.world().setBlock(controllerPos,
                CBMultiblocks.COGNI_ASSEMBLER.defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH),
                true);
        scene.showText(160,
                "When the recipe finishes, the flesh parts are consumed and the controller becomes the selected living machine. Its facing is retained.",
                "配方完成后，血肉部件会被消耗，主方块会变成选定的血肉机器，并保留原朝向。")
                .pointAt(util.vector().blockSurface(controllerPos, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(190);
        scene.markAsFinished();
    }
}
