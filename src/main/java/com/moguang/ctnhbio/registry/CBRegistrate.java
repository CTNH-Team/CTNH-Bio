package com.moguang.ctnhbio.registry;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.IMachineBlock;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;

import com.moguang.ctnhbio.api.item.LivingMetaMachineItem;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.api.recipe.CBRecipeModifier;

import com.moguang.ctnhbio.client.Renderer.LivingMetaMachineBERProvider;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.commons.lang3.function.TriFunction;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CBRegistrate extends GTRegistrate {
    protected CBRegistrate() {
        super(CTNHBio.MODID);
    }
    public static CBRegistrate create() {
        return new CBRegistrate();
    }
    public MultiblockMachineBuilder biomultiblock(String name, Function<IMachineBlockEntity, ? extends MultiblockControllerMachine> metaMachine
    ) {
        return new MultiblockMachineBuilder(this, name, metaMachine,
                LivingMetaMachineBlock::new, MetaMachineItem::new,
                (type, pos, state) -> new LivingMetaMachineBlockEntity<>(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get()));
    }

    public MultiblockMachineBuilder biomultiblock(String name,
                                               Function<IMachineBlockEntity, ? extends MultiblockControllerMachine> metaMachine,
                                               BiFunction<BlockBehaviour.Properties, MultiblockMachineDefinition, IMachineBlock> blockFactory,
                                               BiFunction<IMachineBlock, Item.Properties, MetaMachineItem> itemFactory) {
        return new MultiblockMachineBuilder(this, name, metaMachine,
                blockFactory, itemFactory,
                (type, pos, state) -> new LivingMetaMachineBlockEntity<>(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get()));
    }


    @Override @NotNull @ParametersAreNonnullByDefault
    public <DEFINITION extends MachineDefinition> MachineBuilder<DEFINITION> machine(String name,
                                                                                     Function<ResourceLocation, DEFINITION> definitionFactory,
                                                                                     Function<IMachineBlockEntity, MetaMachine> metaMachine,
                                                                                     BiFunction<BlockBehaviour.Properties, DEFINITION, IMachineBlock> blockFactory,
                                                                                     BiFunction<IMachineBlock, Item.Properties, MetaMachineItem> itemFactory,
                                                                                     TriFunction<BlockEntityType<?>, BlockPos, BlockState, IMachineBlockEntity> blockEntityFactory) {
        return super.machine(name, definitionFactory, metaMachine, blockFactory, itemFactory, blockEntityFactory)
                .hasBER(false);
    }

    @Override @NotNull @ParametersAreNonnullByDefault
    public MachineBuilder<MachineDefinition> machine(String name, Function<IMachineBlockEntity, MetaMachine> metaMachine) {
        return super.machine(name, metaMachine)
                .hasBER(false);
    }

    @NotNull @ParametersAreNonnullByDefault
    public MachineBuilder<MachineDefinition> livingMachine(int tier,
                                                           String name,
                                                           BiFunction<IMachineBlockEntity, Integer, MetaMachine> metaMachine,
                                                           BiFunction<BlockBehaviour.Properties, MachineDefinition, IMachineBlock> blockFactory,
                                                           TriFunction<BlockEntityType<?>, BlockPos, BlockState, IMachineBlockEntity> blockEntityFactory,
                                                           GTRecipeType recipeType,
                                                           boolean transparent
    ) {
        return super.machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + '_' + name,
                        MachineDefinition::new,
                        holder -> metaMachine.apply(holder, tier),
                        blockFactory,
                        (b, p) -> new LivingMetaMachineItem(b, p, name),
                        blockEntityFactory
                )
                .recipeModifiers(GTRecipeModifiers.OC_NON_PERFECT, CBRecipeModifier::batchMode)
                .tier(tier)
                .hasBER(false)
                .recipeType(recipeType)
                .editableUI(BasicLivingMachine.EDITABLE_UI_CREATOR_BIO.apply(CTNHBio.id(name),recipeType))
                .rotationState(RotationState.NON_Y_AXIS)

                .onBlockEntityRegister(beType -> {
                    if (FMLEnvironment.dist == Dist.CLIENT) {
                        LivingMetaMachineBERProvider.registerRenderer(beType, name, transparent);
                    }

                })
                .simpleModel(ResourceLocation.tryBuild("biomancy", "block/flesh"))
                ;
    }
    @NotNull @ParametersAreNonnullByDefault
    public MachineBuilder<MachineDefinition>    livingMachine(int tier,
                                                           String name,
                                                           BiFunction<IMachineBlockEntity, Integer, MetaMachine> metaMachine,
                                                           BiFunction<BlockBehaviour.Properties, MachineDefinition, IMachineBlock> blockFactory,
                                                           GTRecipeType recipeType,
                                                           boolean transparent) {
        return livingMachine(
                tier,
                name,
                metaMachine,
                blockFactory,
                (type, pos, state) -> LivingMetaMachineBlockEntity.create(type, pos, state, CBEntities.LIVING_META_MACHINE_ENTITY.get()),
                recipeType,
                transparent
        );
    }
}
