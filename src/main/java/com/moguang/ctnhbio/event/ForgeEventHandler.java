package com.moguang.ctnhbio.event;

import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.common.machine.BasicLivingMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {
    //public static final Map<UUID, Boolean> isHoldingLeftClick = new HashMap<>();

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
                block.getMachine(level, pos) instanceof BasicLivingMachine machine
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
        Minecraft mc = Minecraft.getInstance();

        //boolean previouslyHolding = isHoldingLeftClick.getOrDefault(id, false);
        boolean cooledDown = player.getAttackStrengthScale(0.5f) >= 1.0f;


        if (!player.isCreative() &&
                state.getBlock() instanceof MetaMachineBlock block &&
                block.getDefinition().getId().getNamespace().equals(CTNHBio.MODID) &&
                //block.getDefinition().getId().getPath().contains("living_machine") &&
                block.getMachine(level, pos) instanceof BasicLivingMachine machine &&
                !stack.isCorrectToolForDrops(state)
        ) {
            if(cooledDown){
                if(!level.isClientSide) player.attack(machine.machineEntity);
                player.resetAttackStrengthTicker();
            }
            else {
                player.swinging = false;
            }
            //isHoldingLeftClick.put(id, true);
            //Minecraft.getInstance().gameMode.attack(player, machine.machineEntity);
            mc.gameMode.stopDestroyBlock();
            event.setCanceled(true);

        }

    }


}
