package com.moguang.ctnhbio.event;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.common.recipe.MobCrushingRecipeManager;

import java.util.Iterator;
import java.util.Map;

import static com.moguang.ctnhbio.registry.CBMultiblocks.GREAT_FLESH;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SubscribeEvent
    public static void onDataReload(AddReloadListenerEvent event) {
        // event.addListener(LootCategories.getReloadListener(new Gson(), "loot_categories"));
        event.addListener(new SimpleJsonResourceReloadListener(GSON, "mob_crushing_recipes") {

            @Override
            protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager,
                                 ProfilerFiller profiler) {
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
                            3);

                }
            }
        }
    }
}
