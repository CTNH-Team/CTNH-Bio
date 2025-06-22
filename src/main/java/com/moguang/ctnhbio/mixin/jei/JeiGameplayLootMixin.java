package com.moguang.ctnhbio.mixin.jei;

import com.yanny.ali.api.Rect;
import com.yanny.ali.compatibility.common.GameplayLootType;
import com.yanny.ali.compatibility.common.GenericUtils;
import com.yanny.ali.compatibility.jei.JeiGameplayLoot;
import com.yanny.ali.compatibility.jei.JeiLootSlotWidget;
import com.yanny.ali.plugin.client.EntryTooltipUtils;
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.gui.widgets.IRecipeWidget;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeSpawnEggItem;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Mixin(value = JeiGameplayLoot.class, remap = false)
public abstract class JeiGameplayLootMixin{


    @Inject(method = "getWidgets*", at = @At("RETURN"), cancellable = true)
    private void injectDespoilEntityModel(
            IRecipeExtrasBuilder builder, GameplayLootType recipe,
            CallbackInfoReturnable<Pair<List<IRecipeWidget>, List<IRecipeSlotDrawable>>> cir
    ) {
        if(!recipe.id().contains("despoil")) return;
        ResourceLocation lootTableId = ((LootTableAccessor)recipe.entry()).getRandomSequence();

        EntityType<?> type = tryGuessEntityTypeFromDespoilId(lootTableId);
        if (type == null || Minecraft.getInstance().level == null) {
            cir.setReturnValue(new Pair<>(List.of(), List.of()));
            return;
        }

        LivingEntity entity = (LivingEntity) type.create(Minecraft.getInstance().level);
        if (entity == null){
            cir.setReturnValue(new Pair<>(List.of(), List.of()));
            return;
        }

        //Pair<List<IRecipeWidget>, List<IRecipeSlotDrawable>> original = cir.getReturnValue();
        List<IRecipeWidget> widgets = new ArrayList<>();

        widgets.add(new IRecipeWidget() {
            final Rect rect = new Rect(0, 0, 162, 8);

            @Override
            public void drawWidget(GuiGraphics guiGraphics, double mouseX, double mouseY) {
                int width = Minecraft.getInstance().font.width(entity.getDisplayName());
                int xOffset = (162 - width) / 2;
                guiGraphics.drawString(Minecraft.getInstance().font, entity.getDisplayName(), xOffset, 0, 0x404040, false);
            }

            @Override
            public @NotNull ScreenPosition getPosition() {
                return new ScreenPosition(rect.x(), rect.y());
            }
        });

        widgets.add(new IRecipeWidget() {
            private static final int WIDGET_SIZE = 36;
            final Rect rect = new Rect(63, 10, 36, 36);
            final ScreenPosition position = new ScreenPosition(0, 0);

            public void drawWidget(GuiGraphics guiGraphics, double mouseX, double mouseY) {
                GenericUtils.renderEntity(entity, this.rect, 162, guiGraphics, (int)mouseX, (int)mouseY);
            }

            public @NotNull ScreenPosition getPosition() {
                return this.position;
            }
        });

        List<IRecipeSlotDrawable> slotDrawables = new LinkedList<>();
        builder.getRecipeSlots().findSlotByName("spawn_egg").ifPresent((slotDrawable) -> {
            widgets.add(new JeiLootSlotWidget(slotDrawable, 72, 0, EntryTooltipUtils.getBaseMap(0.0F)));
            slotDrawables.add(slotDrawable);
        });

        cir.setReturnValue(new Pair<>(widgets, slotDrawables));
    }

    private static EntityType<?> tryGuessEntityTypeFromDespoilId(ResourceLocation randomSequence) {
        if (randomSequence == null) return null;

        String namespace = randomSequence.getNamespace(); // e.g. "alexsmobs"
        String path = randomSequence.getPath();           // e.g. "biomancy/despoil/catfish"

        String[] parts = path.split("/");
        if (parts.length < 3) return null;

        // 一般为：["biomancy", "despoil", "catfish"]
        String entityPath = String.join("/", Arrays.copyOfRange(parts, 2, parts.length));
        ResourceLocation entityId = new ResourceLocation(namespace, entityPath);

        return BuiltInRegistries.ENTITY_TYPE.getOptional(entityId).orElse(null);
    }

    private static ItemStack getSpawnEggForEntity(EntityType<?> type) {
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof ForgeSpawnEggItem eggItem && eggItem.getType(null) == type) {
                return new ItemStack(eggItem);
            }
        }
        return ItemStack.EMPTY;
    }

    @Inject(method = "getYOffset*", at = @At("HEAD"), cancellable = true)
    private void overrideYOffset(GameplayLootType recipe, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(54); // 将掉落物部分整体下移
    }



}

