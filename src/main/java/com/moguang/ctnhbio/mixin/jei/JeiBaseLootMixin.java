package com.moguang.ctnhbio.mixin.jei;

import com.yanny.ali.compatibility.common.GameplayLootType;
import com.yanny.ali.compatibility.common.IType;
import com.yanny.ali.compatibility.jei.JeiBaseLoot;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.recipe.IFocusGroup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(value = JeiBaseLoot.class, remap = false)
public abstract class JeiBaseLootMixin {

    @Inject(method = "setRecipe*", at = @At("HEAD"))
    private void injectSpawnEggSlot(
            IRecipeLayoutBuilder builder, IType recipe, IFocusGroup focuses,
            CallbackInfo ci
    ) {
        // 只处理 GameplayLootType 类型（或你需要的其他）
        if (!(recipe instanceof GameplayLootType loot)) return;

        if (!loot.id().contains("despoil")) return;

        // 获取刷怪蛋
        ResourceLocation lootTableId = ((LootTableAccessor) loot.entry()).getRandomSequence();
        EntityType<?> type = tryGuessEntityTypeFromDespoilId(lootTableId);
        if (type == null) return;

        ItemStack spawnEgg = getSpawnEggForEntity(type);
        if (spawnEgg.isEmpty()) return;

        builder.addInputSlot()
                .setSlotName("spawn_egg")
                .setStandardSlotBackground()
                .setPosition(2, 2)
                .addItemStack(spawnEgg);
    }

    private static ItemStack getSpawnEggForEntity(EntityType<?> type) {
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof SpawnEggItem eggItem && eggItem.getType(null) == type) {
                return new ItemStack(eggItem);
            }
        }
        return ItemStack.EMPTY;
    }

    // Util: 推测生物类型
    private static EntityType<?> tryGuessEntityTypeFromDespoilId(ResourceLocation randomSequence) {
        if (randomSequence == null) return null;

        String namespace = randomSequence.getNamespace();
        String[] parts = randomSequence.getPath().split("/");
        if (parts.length < 3) return null;

        String entityPath = String.join("/", Arrays.copyOfRange(parts, 2, parts.length));
        ResourceLocation entityId = new ResourceLocation(namespace, entityPath);

        return BuiltInRegistries.ENTITY_TYPE.getOptional(entityId).orElse(null);
    }
}

