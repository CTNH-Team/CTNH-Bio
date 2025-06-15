package com.moguang.ctnhbio.event;

import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.common.machine.BasicLivingMachineBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {
    public static final Map<UUID, Boolean> isHoldingLeftClick = new HashMap<>();

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {

        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        Player player = event.getEntity();
        if (level.isClientSide) {
            return;
        }

        if (state.getBlock() instanceof MetaMachineBlock block &&
                block.getDefinition().getId().getNamespace().equals(CTNHBio.MODID) &&
                block.getDefinition().getId().getPath().contains("living_machine") &&
                block.getMachine(level, pos) instanceof BasicLivingMachineBlock machine
        ) {
            InteractionResult result = machine.machineEntity.interact(player, event.getHand());

            if (result.consumesAction()) {
                event.setCanceled(true);
            }

        }
    }

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        UUID id = player.getUUID();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = event.getItemStack();

        boolean previouslyHolding = isHoldingLeftClick.getOrDefault(id, false);



        if (state.getBlock() instanceof MetaMachineBlock block &&
                block.getDefinition().getId().getNamespace().equals(CTNHBio.MODID) &&
                //block.getDefinition().getId().getPath().contains("living_machine") &&
                block.getMachine(level, pos) instanceof BasicLivingMachineBlock machine &&
                !stack.isCorrectToolForDrops(state)
        ) {

//            if (!previouslyHolding) {
//
//            }
            if(!level.isClientSide) player.attack(machine.machineEntity);
            player.resetAttackStrengthTicker();
            isHoldingLeftClick.put(id, true);
            //Minecraft.getInstance().gameMode.attack(player, machine.machineEntity);
            event.setCanceled(true);

        }

    }

//    @SubscribeEvent
//    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
//        if (event.phase != TickEvent.Phase.END) return;
//        Player player = event.player;
//
//
//        //if (!(player.level().isClientSide)) return;
//
//        boolean isKeyDown = Minecraft.getInstance().options.keyAttack.isDown();
//        if (!isKeyDown) {
//
//            isHoldingLeftClick.put(player.getUUID(), false);
//        }
//    }

}
