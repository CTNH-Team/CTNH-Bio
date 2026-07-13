package com.moguang.ctnhbio.event;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.ctnhlang.CN;
import com.ctnhlang.Category;
import com.ctnhlang.EN;
import com.moguang.ctnhbio.CTNHBio;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import java.util.Iterator;

import static com.moguang.ctnhbio.registry.CBMultiblocks.GREAT_FLESH;
import static dev.shadowsoffire.hostilenetworks.Hostile.Items.DEEP_LEARNER;
import static dev.shadowsoffire.hostilenetworks.Hostile.Items.SIM_CHAMBER;

@Category("item_tooltip")
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

    @CN("最高可将模型提升至[基础]等级")
    @EN("Can upgrade models to [Basic] tier at most")
    static Lang deep_learner;

    @CN("可放入等级为[缺陷]或[基础]的模型，最高可将模型提升至[进阶]\n不会产生预测产物")
    @EN("Accepts models of [Faulty] or [Basic] tier, can upgrade models to [Advanced] tier at most\nno predictive products will be generated")
    static Lang sim_chamber;

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(DEEP_LEARNER.get())) {
            event.getToolTip().add(deep_learner.translate().withStyle(ChatFormatting.YELLOW));
        } else if (stack.is(SIM_CHAMBER.get())) {
            event.getToolTip().add(sim_chamber.translate().withStyle(ChatFormatting.YELLOW));
        }
    }
}
