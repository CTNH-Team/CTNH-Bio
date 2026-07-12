package com.moguang.ctnhbio.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.moguang.ctnhbio.CTNHBio;

import java.util.Iterator;

import static com.moguang.ctnhbio.registry.CBMultiblocks.GREAT_FLESH;

@Mod.EventBusSubscriber(modid = CTNHBio.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {
    // spotless: off
    // private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    //
    // @SubscribeEvent
    // public static void onDataReload(AddReloadListenerEvent event) {
    // // event.addListener(LootCategories.getReloadListener(new Gson(), "loot_categories"));
    // class MobCrushingReloadListener extends SimpleJsonResourceReloadListener {
    //
    // MobCrushingReloadListener(Gson gson, String directory) {
    // super(gson, directory);
    // }
    //
    // @Override
    // protected void apply(Map<ResourceLocation, JsonElement> jsonMap, ResourceManager resourceManager,
    // ProfilerFiller profiler) {
    // JsonArray recipesJson = new JsonArray();
    // jsonMap.values().forEach(recipesJson::add);
    // MobCrushingRecipeManager.loadFromJson(recipesJson);
    // }
    // }
    // event.addListener(new MobCrushingReloadListener(GSON, "mob_crushing_recipes"));
    // }
    // spotless: on

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
