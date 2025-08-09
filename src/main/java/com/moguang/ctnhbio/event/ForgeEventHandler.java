package com.moguang.ctnhbio.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.gregtechceu.gtceu.api.block.MetaMachineBlock;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.common.recipe.MobCrushingRecipeManager;
import com.yanny.ali.registries.LootCategories;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import static com.moguang.ctnhbio.machine.multiblock.MultiblocksA.GREAT_FLESH;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SubscribeEvent
    public static void onDataReload(AddReloadListenerEvent event) {
        event.addListener(LootCategories.getReloadListener(new Gson(), "loot_categories"));
        event.addListener(new SimpleJsonResourceReloadListener(GSON, "mob_crushing_recipes") {
            @Override
            protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager, ProfilerFiller profiler) {
                JsonArray recipesJson = new JsonArray();
                jsonMap.values().forEach(recipesJson::add);
                MobCrushingRecipeManager.loadFromJson(recipesJson);
            }
        });
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Iterator<LivingEntity> iterator = TransformManager.FLESH_BLOB_LIST.iterator();
            while (iterator.hasNext()) {
                LivingEntity entity = iterator.next();
                Level level = entity.level();
                BlockPos pos = entity.getOnPos();
                if (level.getBlockState(pos).canBeReplaced() && level.getBlockState(pos.below()).canBeReplaced()) {
                    iterator.remove();
                    entity.discard();
                    level.setBlock(
                            pos,
                            GREAT_FLESH.get().getDefinition().defaultBlockState(),
                            3
                    );

                }
            }
        }
    }


}
