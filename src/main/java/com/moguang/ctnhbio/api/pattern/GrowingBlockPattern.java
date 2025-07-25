package com.moguang.ctnhbio.api.pattern;


import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.pattern.BlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockState;
import com.gregtechceu.gtceu.api.pattern.TraceabilityPredicate;
import com.gregtechceu.gtceu.api.pattern.predicates.SimplePredicate;
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection;

import com.lowdragmc.lowdraglib.utils.BlockInfo;

import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static com.lowdragmc.lowdraglib.LDLib.random;
import static net.minecraft.world.level.block.Block.UPDATE_CLIENTS;

public class GrowingBlockPattern extends BlockPattern {

    public static final TagKey<Block> GROWABLE_TAG = BlockTags.create(new ResourceLocation("ctnhbio", "growable"));
    public static final TagKey<Block> GROWING_REPLACEABLE_TAG = BlockTags.create(new ResourceLocation("ctnhbio", "growing_replaceable"));


    private final BuildContext context = new BuildContext();

    public final BuildPlan growPlan = new BuildPlan();

    static Direction[] FACINGS = { Direction.SOUTH, Direction.NORTH, Direction.WEST, Direction.EAST, Direction.UP,
            Direction.DOWN };
    static Direction[] FACINGS_H = { Direction.SOUTH, Direction.NORTH, Direction.WEST, Direction.EAST };

    public final int[][] aisleRepetitions;
    public final RelativeDirection[] structureDir;
    protected final TraceabilityPredicate[][][] blockMatches; // [z][y][x]
    protected final int fingerLength; // z size
    protected final int thumbLength; // y size
    protected final int palmLength; // x size
    protected final int[] centerOffset; // x, y, z, minZ, maxZ

    public GrowingBlockPattern(TraceabilityPredicate[][][] predicatesIn, RelativeDirection[] structureDir, int[][] aisleRepetitions, int[] centerOffset) {
        super(predicatesIn, structureDir, aisleRepetitions, centerOffset);
        this.blockMatches = predicatesIn;
        this.fingerLength = predicatesIn.length;
        this.structureDir = structureDir;
        this.aisleRepetitions = aisleRepetitions;

        if (this.fingerLength > 0) {
            this.thumbLength = predicatesIn[0].length;

            if (this.thumbLength > 0) {
                this.palmLength = predicatesIn[0][0].length;
            } else {
                this.palmLength = 0;
            }
        } else {
            this.thumbLength = 0;
            this.palmLength = 0;
        }

        this.centerOffset = centerOffset;


    }

    public static GrowingBlockPattern getGrowingBlockPattern(BlockPattern blockPattern) {
        try {
            Class<?> clazz = BlockPattern.class;
            // blockMatches
            Field blockMatchesField = clazz.getDeclaredField("blockMatches");
            blockMatchesField.setAccessible(true);
            TraceabilityPredicate[][][] blockMatches = (TraceabilityPredicate[][][]) blockMatchesField.get(blockPattern);
            // structureDir
            Field structureDirField = clazz.getDeclaredField("structureDir");
            structureDirField.setAccessible(true);
            RelativeDirection[] structureDir = (RelativeDirection[]) structureDirField.get(blockPattern);
            // aisleRepetitions
            Field aisleRepetitionsField = clazz.getDeclaredField("aisleRepetitions");
            aisleRepetitionsField.setAccessible(true);
            int[][] aisleRepetitions = (int[][]) aisleRepetitionsField.get(blockPattern);
            // centerOffset
            Field centerOffsetField = clazz.getDeclaredField("centerOffset");
            centerOffsetField.setAccessible(true);
            int[] centerOffset = (int[]) centerOffsetField.get(blockPattern);

            return new GrowingBlockPattern(blockMatches, structureDir, aisleRepetitions, centerOffset);
        } catch (Exception ignored) {}
        return null;
    }




    public void generateGrowPlan(Level level, MultiblockState worldState, GrowSetting setting) {
        // 初始化阶段
        initializeBuildContext(level, worldState, setting);

        // 计算每层的重复次数
        int[] repeatCounts = calculateLayerRepeatCounts(setting);

        // 生成所有建造任务
        for (int c = 0, z = context.minZ++, r; c < this.fingerLength; c++) {
            for (r = 0; r < repeatCounts[c]; r++) {
                context.worldState.getLayerCount().clear();
                generateLayerTasks(c, z);
                z++;
            }
        }

        growPlan.generateGrowOrder(context.centerPos);
    }
    private void generateLayerTasks(int layerIndex, int z) {
        for (int b = 0, y = -centerOffset[1]; b < this.thumbLength; b++, y++) {
            for (int a = 0, x = -centerOffset[0]; a < this.palmLength; a++, x++) {
                BlockPos pos = calculateBlockPosition(x, y, z);
                TraceabilityPredicate predicate = this.blockMatches[layerIndex][b][a];

                growPlan.add(new BuildTask(pos, predicate));
            }
        }

    }

    public class BuildTask {

        public final BlockPos pos;
        private final TraceabilityPredicate predicate;


        public BuildTask(BlockPos pos, TraceabilityPredicate predicate) {

            this.pos = pos;
            this.predicate = predicate;
        }

        public boolean execute() {

            updateWorldState(context.worldState, pos, predicate);

            if (!canReplaceExistingBlock(pos, predicate))
                return false;

            BlockInfo[] infos = determineRequiredBlockInfo(predicate);
            if (infos == null)
                return false;

            return placeBlock(pos, infos);

        }
        public boolean isFluid()
        {
            BlockInfo[] infos = determineRequiredBlockInfo(predicate);
            if(infos == null) return false;
            return infos[0].getBlockState().liquid();
        }


    }

    public class BuildPlan {
        private static final int BLOCKS_PER_TICK = 1; // 每tick放置的方块数量

        @Getter
        private final Queue<BuildTask> buildQueue = new ArrayDeque<>();
        private final List<BuildTask> buildTaskList = new ArrayList<>();
        private int fluidTaskNum = 0;

        public boolean isCompleted()
        {
            return buildQueue.isEmpty();
        }

        public void add(BuildTask task) {buildTaskList.add(task);}

        public boolean tick() {
            if (isCompleted()) return false;

            // 每tick处理一定数量的方块
            try {
                for (int i = 0; i < BLOCKS_PER_TICK && !buildQueue.isEmpty(); i++) {
                    BuildTask task = buildQueue.poll();
                    while(task != null) {
                        if(task.isFluid() && fluidTaskNum != buildQueue.size())
                        {
                            buildQueue.add(task);
                            fluidTaskNum++;

                        }
                        else if(task.execute()) return true;
                        task = buildQueue.poll();
                    }
                    return false;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            return false;
        }

        public void generateGrowOrder(BlockPos startPos) {
            fluidTaskNum = 0;
            Set<BlockPos> built = new HashSet<>();
            Queue<BuildTask> activeFrontier = new LinkedList<>();

            // 3. 从基点开始生长
            //growthOrder.add(startTask);
            built.add(startPos);
            addNeighborsToFrontier(startPos, activeFrontier, built);

            // 4. 逐步扩展
            while (!activeFrontier.isEmpty()) {
                // 随机选择下一个生长点（增加随机性）
                BuildTask next = selectRandomFromFrontier(activeFrontier);

                buildQueue.add(next);

                built.add(next.pos);
                activeFrontier.remove(next);

                // 添加新发现的相邻方块
                addNeighborsToFrontier(next.pos, activeFrontier, built);
            }
        }

        private void addNeighborsToFrontier(BlockPos center,
                                            Queue<BuildTask> frontier,
                                            Set<BlockPos> built) {
            // 检查6个方向的相邻方块
            for (Direction dir : Direction.values()) {
                BlockPos neighborPos = center.relative(dir);
                buildTaskList.stream()
                        .filter(t -> t.pos.equals(neighborPos))
                        .filter(t -> !built.contains(t.pos))
                        .filter(t -> !frontier.contains(t))
                        .findFirst()
                        .ifPresent(frontier::add);
            }
        }

        private BuildTask selectRandomFromFrontier(Queue<BuildTask> frontier) {
            // 将队列转为List以便随机选择
            List<BuildTask> candidates = new ArrayList<>(frontier);
            return candidates.get(random.nextInt(candidates.size()));
        }



    }

    private void initializeBuildContext(Level level, MultiblockState worldState, GrowSetting setting) {
        IMultiController controller = worldState.getController();


        context.world = level;

        context.worldState = worldState;
        context.settings = setting;
        context.controller = controller;
        context.centerPos = controller.self().getPos();
        context.facing = controller.self().getFrontFacing();
        context.upwardsFacing = controller.self().getUpwardsFacing();
        context.isFlipped = controller.self().isFlipped();
        context.minZ = -centerOffset[4];

        clearWorldState(worldState);
        //context.blocks.put(context.centerPos, controller);

    }

    private int[] calculateLayerRepeatCounts(GrowSetting settings) {
        int[] repeat = new int[this.fingerLength];
        for (int h = 0; h < this.fingerLength; h++) {
            var minH = aisleRepetitions[h][0];
            var maxH = aisleRepetitions[h][1];
            repeat[h] = (minH != maxH) ?
                    Math.max(minH, Math.min(maxH, settings.getRepeatCount())) :
                    minH;
        }
        return repeat;
    }


    private BlockPos calculateBlockPosition(int x, int y, int z) {
        return setActualRelativeOffset(x, y, z, context.facing, context.upwardsFacing, context.isFlipped)
                .offset(context.centerPos.getX(), context.centerPos.getY(), context.centerPos.getZ());
    }

    private boolean canReplaceExistingBlock(BlockPos pos, TraceabilityPredicate predicate) {

        context.inFluid = false;
        if (!context.world.isEmptyBlock(pos)) {
            BlockState existingState = context.world.getBlockState(pos);

            for (SimplePredicate limit : predicate.limited) {
                limit.testLimited(context.worldState);
            }

            if(existingState.liquid()){
                context.inFluid = true;
                return true;
            }

            if(existingState.is(GROWING_REPLACEABLE_TAG))
                return true;


            return false;
        }
        return true;
    }

    private BlockInfo[] determineRequiredBlockInfo(TraceabilityPredicate predicate) {
        // 有限谓词优先
        BlockInfo[] infos = checkLimitedPredicates(predicate);
        if (infos != null) return infos;

        // 无限制谓词
        return checkCommonPredicates(predicate);
    }

    private BlockInfo[] checkLimitedPredicates(TraceabilityPredicate predicate) {
        Map<SimplePredicate, Integer> cacheLayer = context.worldState.getLayerCount();
        Map<SimplePredicate, Integer> cacheGlobal = context.worldState.getGlobalCount();

        // 检查层级限制
        for (SimplePredicate limit : predicate.limited) {
            if (limit.minLayerCount > 0 ) {
                if (!cacheLayer.containsKey(limit)) {
                    cacheLayer.put(limit, 1);
                    return limit.candidates.get();
                } else if (cacheLayer.get(limit) < limit.minLayerCount &&
                        (limit.maxLayerCount == -1 || cacheLayer.get(limit) < limit.maxLayerCount)) {
                    cacheLayer.put(limit, cacheLayer.get(limit) + 1);
                    return limit.candidates.get();
                }
            }
        }

        // 检查全局限制
        for (SimplePredicate limit : predicate.limited) {
            if (limit.minCount > 0) {
                if (!cacheGlobal.containsKey(limit)) {
                    cacheGlobal.put(limit, 1);
                    return limit.candidates.get();
                } else if (cacheGlobal.get(limit) < limit.minCount &&
                        (limit.maxCount == -1 || cacheGlobal.get(limit) < limit.maxCount)) {
                    cacheGlobal.put(limit, cacheGlobal.get(limit) + 1);
                    return limit.candidates.get();
                }
            }
        }

        return null;
    }

    private BlockInfo[] checkCommonPredicates(TraceabilityPredicate predicate) {
        List<BlockInfo> infos = new ArrayList<>();
        Map<SimplePredicate, Integer> cacheLayer = context.worldState.getLayerCount();
        Map<SimplePredicate, Integer> cacheGlobal = context.worldState.getGlobalCount();

        // 无限制谓词处理
        for (SimplePredicate limit : predicate.limited) {

            if (limit.maxLayerCount != -1 &&
                    cacheLayer.getOrDefault(limit, Integer.MAX_VALUE) == limit.maxLayerCount) continue;

            if (limit.maxCount != -1 &&
                    cacheGlobal.getOrDefault(limit, Integer.MAX_VALUE) == limit.maxCount) continue;

            if (cacheLayer.containsKey(limit)) {
                cacheLayer.put(limit, cacheLayer.get(limit) + 1);
            } else {
                cacheLayer.put(limit, 1);
            }

            if (cacheGlobal.containsKey(limit)) {
                cacheGlobal.put(limit, cacheGlobal.get(limit) + 1);
            } else {
                cacheGlobal.put(limit, 1);
            }

            if (limit.candidates != null) {
                infos.addAll(Arrays.asList(limit.candidates.get()));
            }
        }

        // 通用谓词
        for (SimplePredicate common : predicate.common) {
            if (common.candidates != null && predicate.common.size() > 1) {
                continue;
            }
            if (common.candidates != null) {
                infos.addAll(Arrays.asList(common.candidates.get()));
            }
        }

        return infos.isEmpty() ? null : infos.toArray(new BlockInfo[0]);
    }



    private boolean placeBlock(BlockPos pos, BlockInfo[] infos) {

        BlockInfo info = Arrays.stream(infos)
                //.filter(i ->i.getBlockState().is(GROWABLE_TAG))
                .findFirst()
                .orElse(null);
        if(info != null)
        {
            var blockState = info.getBlockState();
            if(blockState.liquid())
            {
                if(!context.inFluid)
                    return context.world.setBlock(pos, blockState, UPDATE_CLIENTS);
                else
                    return true;
            }
            else
                return context.world.setBlock(pos, blockState, UPDATE_CLIENTS);
        }
        return false;
    }


    @Getter
    public static class GrowSetting {
        int repeatCount = 0;
    }


    // 用于保存构建过程中的上下文数据
    public static class BuildContext {
        Level world;
        MultiblockState worldState;
        GrowSetting settings;
        IMultiController controller;
        BlockPos centerPos;
        Direction facing;
        Direction upwardsFacing;
        boolean isFlipped;
        int minZ;


        boolean inFluid;


    }


    private void clearWorldState(MultiblockState worldState) {
        try {
            Class<?> clazz = Class.forName("com.gregtechceu.gtceu.api.pattern.MultiblockState");
            // Object obj = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("clean");
            method.setAccessible(true);
            method.invoke(worldState);
        } catch (Exception ignored) {}
    }

    private void updateWorldState(MultiblockState worldState, BlockPos posIn, TraceabilityPredicate predicate) {
        try {
            Class<?> clazz = Class.forName("com.gregtechceu.gtceu.api.pattern.MultiblockState");
            Method method = clazz.getDeclaredMethod("update", BlockPos.class, TraceabilityPredicate.class);
            method.setAccessible(true);
            method.invoke(worldState, posIn, predicate);
        } catch (Exception ignored) {}
    }

    private BlockPos setActualRelativeOffset(int x, int y, int z, Direction facing, Direction upwardsFacing,
                                             boolean isFlipped) {
        int[] c0 = new int[] { x, y, z }, c1 = new int[3];
        if (facing == Direction.UP || facing == Direction.DOWN) {
            Direction of = facing == Direction.DOWN ? upwardsFacing : upwardsFacing.getOpposite();
            for (int i = 0; i < 3; i++) {
                switch (structureDir[i].getActualDirection(of)) {
                    case UP -> c1[1] = c0[i];
                    case DOWN -> c1[1] = -c0[i];
                    case WEST -> c1[0] = -c0[i];
                    case EAST -> c1[0] = c0[i];
                    case NORTH -> c1[2] = -c0[i];
                    case SOUTH -> c1[2] = c0[i];
                }
            }
            int xOffset = upwardsFacing.getStepX();
            int zOffset = upwardsFacing.getStepZ();
            int tmp;
            if (xOffset == 0) {
                tmp = c1[2];
                c1[2] = zOffset > 0 ? c1[1] : -c1[1];
                c1[1] = zOffset > 0 ? -tmp : tmp;
            } else {
                tmp = c1[0];
                c1[0] = xOffset > 0 ? c1[1] : -c1[1];
                c1[1] = xOffset > 0 ? -tmp : tmp;
            }
            if (isFlipped) {
                if (upwardsFacing == Direction.NORTH || upwardsFacing == Direction.SOUTH) {
                    c1[0] = -c1[0]; // flip X-axis
                } else {
                    c1[2] = -c1[2]; // flip Z-axis
                }
            }
        } else {
            for (int i = 0; i < 3; i++) {
                switch (structureDir[i].getActualDirection(facing)) {
                    case UP -> c1[1] = c0[i];
                    case DOWN -> c1[1] = -c0[i];
                    case WEST -> c1[0] = -c0[i];
                    case EAST -> c1[0] = c0[i];
                    case NORTH -> c1[2] = -c0[i];
                    case SOUTH -> c1[2] = c0[i];
                }
            }
            if (upwardsFacing == Direction.WEST || upwardsFacing == Direction.EAST) {
                int xOffset = upwardsFacing == Direction.EAST ? facing.getClockWise().getStepX() :
                        facing.getClockWise().getOpposite().getStepX();
                int zOffset = upwardsFacing == Direction.EAST ? facing.getClockWise().getStepZ() :
                        facing.getClockWise().getOpposite().getStepZ();
                int tmp;
                if (xOffset == 0) {
                    tmp = c1[2];
                    c1[2] = zOffset > 0 ? -c1[1] : c1[1];
                    c1[1] = zOffset > 0 ? tmp : -tmp;
                } else {
                    tmp = c1[0];
                    c1[0] = xOffset > 0 ? -c1[1] : c1[1];
                    c1[1] = xOffset > 0 ? tmp : -tmp;
                }
            } else if (upwardsFacing == Direction.SOUTH) {
                c1[1] = -c1[1];
                if (facing.getStepX() == 0) {
                    c1[0] = -c1[0];
                } else {
                    c1[2] = -c1[2];
                }
            }
            if (isFlipped) {
                if (upwardsFacing == Direction.NORTH || upwardsFacing == Direction.SOUTH) {
                    if (facing == Direction.NORTH || facing == Direction.SOUTH) {
                        c1[0] = -c1[0]; // flip X-axis
                    } else {
                        c1[2] = -c1[2]; // flip Z-axis
                    }
                } else {
                    c1[1] = -c1[1]; // flip Y-axis
                }
            }
        }
        return new BlockPos(c1[0], c1[1], c1[2]);
    }

}
