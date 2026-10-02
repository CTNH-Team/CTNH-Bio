package com.moguang.ctnhbio.client.ponder;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.machines.GCYMMachines;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;
import com.moguang.ctnhbio.registry.CBMachines;
import dev.shadowsoffire.hostilenetworks.Hostile;
import tech.vixhentx.mcmod.ctnhlib.client.ponder.ui.MachineUI;

public final class CogniAssembler {

    /** 数据模型接口自己的界面：意识模型就放在它唯一的槽位里。 */
    private static final MachineUI NEURAL_MODEL_ACCESSOR_UI = MachineUI.of(CBMachines.NEURAL_MODEL_ACCESSOR)
            .scale(0.6f);

    /** LV 输入总成：以锻炉的肥料配方为例，3 种物品 + 2 种流体一块方块就装得下。 */
    private static final MachineUI INPUT_HATCH_UI = MachineUI.of(GTMachines.DUAL_IMPORT_HATCH[GTValues.LV])
            .scale(0.6f);

    /** LV 物品输出总线：这台机器的配方只出物品，没有流体产物。 */
    private static final MachineUI ITEM_OUTPUT_BUS_UI = MachineUI.of(GTMachines.ITEM_EXPORT_BUS[GTValues.LV])
            .scale(0.6f);

    private CogniAssembler() {}

    public static void common(SceneBuilder builder, SceneBuildingUtil util) {
        CTNHBioPonderSceneBuilder scene = new CTNHBioPonderSceneBuilder(builder);
        scene.title("cogni_assembler", "How to build the Cogni Assembler", "如何搭建意识装配机",
                "Cogni Assembler", "意识装配机");
        scene.init7x7(util);
        scene.idle(20);

        var controller = util.select().position(3, 2, 2);
        var bridge = util.select().position(5, 3, 3);
        var modelAccessor = util.select().position(3, 3, 2);

        scene.world().showSection(controller, Direction.DOWN);
        scene.showText(90, "Start with the Cogni Assembler controller.", "先放置意识装配机主方块。")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 2), Direction.NORTH))
                .attachKeyFrame();
        scene.idle(110);

        scene.overlay().showControls(
                util.vector().blockSurface(util.grid().at(3, 2, 2), Direction.NORTH),
                Pointing.LEFT, 60)
                .rightClick()
                .withItem(GTItems.TERMINAL.asStack())
                .whileSneaking();
        scene.showText(100,
                "While sneaking, right-click the controller with a terminal to place the structure in one click.",
                "手持终端蹲下右键主方块，一键放置装配机结构。")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 2, 2), Direction.NORTH))
                .attachKeyFrame();
        scene.idle(120);

        scene.world().showSection(util.select().fromTo(1, 1, 1, 5, 5, 5)
                .substract(controller)
                .substract(bridge)
                .substract(modelAccessor), Direction.DOWN);
        scene.showText(100,
                "The terminal places the casing structure at once; configure the H and I positions with their required parts next.",
                "终端会一次放置外壳结构，接下来再安装 H 位和 I 位所需的部件。")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 3, 3), Direction.NORTH))
                .attachKeyFrame();
        scene.idle(120);

        scene.world().showSection(bridge, Direction.DOWN);
        scene.rotateCameraY(90);
        scene.idle(40);
        scene.overlay().showControls(
                util.vector().blockSurface(util.grid().at(5, 3, 3), Direction.EAST),
                Pointing.LEFT, 60)
                .rightClick()
                .withItem(CBMachines.PARABIOTIC_BRIDGE.asStack());
        scene.showText(120,
                "Place the Parabiotic Bridge in an H position on the side of the assembler, then share it with a second Cogni Assembler so both controllers are connected.",
                "将联体桥放在装配机侧面的 H 位，再让第二台意识装配机共享这个位置，使两个主方块连通。")
                .pointAt(util.vector().blockSurface(util.grid().at(5, 3, 3), Direction.EAST))
                .attachKeyFrame();
        scene.idle(140);

        scene.rotateCameraY(-90);
        scene.idle(40);
        scene.world().showSection(modelAccessor, Direction.DOWN);
        scene.overlay().showControls(
                util.vector().blockSurface(util.grid().at(3, 3, 2), Direction.NORTH),
                Pointing.LEFT, 60)
                .rightClick()
                .withItem(CBMachines.NEURAL_MODEL_ACCESSOR.asStack());
        scene.showUI(NEURAL_MODEL_ACCESSOR_UI).at(util.vector().topOf(util.grid().at(3, 3, 2))).machinePos(util.grid().at(3, 3, 2))
                .slot(0)
                .withItem(new ItemStack(Hostile.Items.DATA_MODEL.get()), 20)
                .outlineSlot(0, 20)
                .show(170);
        scene.showText(120,
                "Install the Neural Model Accessor in the unique I position. Put the consciousness model into its slot; assembly steps read the model from this accessor.",
                "将数据模型接口安装在唯一的 I 位，并把意识模型放入接口槽位；装配步骤会从这里读取意识模型。")
                .pointAt(util.vector().blockSurface(util.grid().at(3, 3, 2), Direction.NORTH))
                .attachKeyFrame();
        scene.idle(180);

        scene.showText(100,
                "Fill the remaining ability positions with the energy, item, and fluid handlers required by the recipe.",
                "根据配方需求，将其余能力位置替换为所需的能源、物品和流体处理器。")
                .pointAt(util.vector().blockSurface(util.grid().at(2, 2, 2), Direction.NORTH))
                .attachKeyFrame();
        scene.idle(120);

        // 输入这一路以锻炉的肥料配方为例：3 种物品进格、2 种流体进罐，全在一块总成里。
        var inputPos = util.grid().at(4, 2, 2);
        scene.world().setBlock(inputPos, GTMachines.DUAL_IMPORT_HATCH[GTValues.LV].defaultBlockState()
                .setValue(BlockStateProperties.FACING, Direction.NORTH), true);
        scene.showUI(INPUT_HATCH_UI).at(util.vector().topOf(inputPos)).machinePos(inputPos)
                .slot(0)
                .withItem(new ItemStack(ModItems.NUTRIENTS.get(), 30), 20)
                .slot(1)
                .withItem(new ItemStack(ModItems.ORGANIC_MATTER.get(), 4), 20)
                .slot(2)
                .withItem(new ItemStack(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Steel).getItem(), 4), 20)
                .tank(0)
                .withFluid(OrganicMaterials.Hormone_Secretion.getFluid(600), 20)
                .tank(1)
                .withFluid(OrganicMaterials.Regenerative_Fluid.getFluid(400), 20)
                .outlineSlot(0, 20)
                .outlineTank(0, 20)
                .show(180);
        scene.idle(195);

        // 输出侧只出物品：锻炉配方一条流体产物都没有，这里用物品总线而不是输出总成。
        var outputPos = util.grid().at(4, 4, 2);
        scene.world().setBlock(outputPos, GTMachines.ITEM_EXPORT_BUS[GTValues.LV].defaultBlockState()
                .setValue(BlockStateProperties.FACING, Direction.NORTH), true);
        scene.showUI(ITEM_OUTPUT_BUS_UI).at(util.vector().topOf(outputPos)).machinePos(outputPos)
                .slot(0)
                .withItem(new ItemStack(ModItems.FERTILIZER.get(), 1), 20)
                .outlineSlot(0, 20)
                .show(180);
        scene.idle(195);

        var parallelHatchPosition = util.grid().at(4, 3, 2);
        scene.world().setBlock(parallelHatchPosition,
                GCYMMachines.PARALLEL_HATCH[GTValues.IV].defaultBlockState(), true);
        scene.overlay().showControls(
                util.vector().blockSurface(parallelHatchPosition, Direction.NORTH),
                Pointing.LEFT, 60)
                .rightClick()
                .withItem(GCYMMachines.PARALLEL_HATCH[GTValues.IV].asStack());
        scene.showText(110,
                "Parallel Control Hatches can be used by parallel assembly recipes, but Cogni Assembler consciousness recipes cannot use them.",
                "并行仓可用于并行组装配方，但意识装配配方不能使用并行仓。")
                .pointAt(util.vector().blockSurface(parallelHatchPosition, Direction.NORTH))
                .attachKeyFrame();
        scene.idle(130);
        scene.markAsFinished();
    }
}
