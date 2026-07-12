package com.moguang.ctnhbio.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerGroup;
import com.gregtechceu.gtceu.api.recipe.ingredient.IChancedIngredient;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;
import com.gregtechceu.gtceu.api.recipe.ui.GTRecipeTypeUI;

import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.jei.IngredientIO;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;

import com.moguang.ctnhbio.api.gui.widget.EntityWidget;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import com.moguang.ctnhbio.api.recipe.lookup.EntityTagMapIngredient;
import com.moguang.ctnhbio.api.recipe.lookup.EntityTypeMapIngredient;
import com.moguang.ctnhbio.integration.xei.entry.entity.EntityEntryList;
import com.moguang.ctnhbio.integration.xei.handlers.entity.CycleEntityEntryHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.gregtechceu.gtceu.client.util.DrawUtil.drawChance;
import static com.gregtechceu.gtceu.client.util.DrawUtil.drawString;

public class EntityRecipeCapability extends RecipeCapability<EntityIngredient> {

    public static final EntityRecipeCapability CAP = new EntityRecipeCapability();

    protected EntityRecipeCapability() {
        super("entity", 0xf5424200, true, EntityIngredient.CODEC);
    }

    @Override
    public EntityIngredient fromNetwork(FriendlyByteBuf friendlyByteBuf) {
        return EntityIngredient.fromNetwork(friendlyByteBuf);
    }

    @Override
    public void toNetwork(EntityIngredient ingredient, FriendlyByteBuf friendlyByteBuf) {
        ingredient.toNetwork(friendlyByteBuf);
    }

    @Override
    public EntityIngredient copyInner(EntityIngredient content, int multiplier) {
        return content.copyWithMultiplier(multiplier);
    }

    @Override
    public boolean isChanced(EntityIngredient content) {
        return content.isChanced();
    }

    @Override
    public IGuiTexture createXEIOverlay(EntityIngredient content, boolean perTick) {
        return new IGuiTexture() {

            @Override
            public void draw(GuiGraphics graphics, int mouseX, int mouseY, float x, float y, int width, int height) {
                drawChance(graphics, x, y, width, height, content.getChance());
                if (content.count > 1) {
                    drawString(graphics, x, y, width, height, String.valueOf(content.count), 0xFFFFFF, false);
                }
            }
        };
    }

    @Override
    public int getMaxParallelByInput(RecipeHandlerGroup holder, GTRecipe recipe, int limit, boolean tick) {
        var inputs = (tick ? recipe.tickInputs : recipe.inputs).get(this);
        if (inputs == null || inputs.isEmpty()) return limit;

        var handlers = holder.getInputHandlerMap().get(this);
        if (handlers == null || handlers.isEmpty()) return 0;

        int maxMultiplier = 0;

        // Check each handler's capacity
        for (var handler : handlers) {

            // Then calculate parallel based on consumables
            int handlerMultiplier = Integer.MAX_VALUE;

            for (var c : inputs) {
                int required = c.count;
                int available = countMatches(handler.getContents(), c);

                if (available < required) {
                    handlerMultiplier = 0;
                    break;
                }

                int possible = available / required;
                handlerMultiplier = Math.min(handlerMultiplier, possible);
            }

            maxMultiplier = Math.max(maxMultiplier, handlerMultiplier);
            if (maxMultiplier == limit) break; // Early exit if we hit the limit
        }

        return Math.min(maxMultiplier, limit);
    }

    private static int countMatches(List<Object> contents, EntityIngredient ingredient) {
        int available = 0;
        for (Object content : contents) {
            if (content instanceof Entity entity && ingredient.test(entity)) {
                available++;
            }
        }
        return available;
    }

    @Override
    public boolean doAddGuiSlots() {
        return true;
    }

    @Override
    public @NotNull List<Object> createXEIContainerContents(List<EntityIngredient> contents, GTRecipeDefinition recipe,
                                                            IO io) {
        List<Object> entryLists = contents.stream()
                .map(i -> new EntityEntryList(i, true))
                .collect(Collectors.toList());

        while (entryLists.size() < recipe.recipeType.getMaxOutputs(this)) entryLists.add(null);
        return entryLists;
    }

    @Override
    @SuppressWarnings("unchecked")
    // arg: List<EntityEntryList> contents
    // ret: List<CycleEntityEntryHandler>
    public @NotNull Object createXEIContainer(List<?> contents) {
        return contents.stream()
                .map(obj -> (EntityEntryList) obj)
                .map(CycleEntityEntryHandler::new)
                .collect(Collectors.toList());
    }

    @Override
    public @NotNull Widget createWidget() {
        return new EntityWidget();
    }

    @Override
    public @NotNull Class<? extends Widget> getWidgetClass() {
        return EntityWidget.class;
    }

    @Override
    public void applyWidgetInfo(@NotNull Widget widget,
                                int index,
                                boolean isXEI,
                                IO io,
                                @Nullable("null when storage == null") GTRecipeTypeUI.RecipeHolder recipeHolder,
                                @NotNull GTRecipeType recipeType,
                                @Nullable("null when content == null") GTRecipeDefinition recipe,
                                @Nullable EntityIngredient content,
                                @Nullable Object storage, int recipeTier, int chanceTier) {
        if (!isXEI || storage == null) return;

        EntityWidget ew = (EntityWidget) widget;
        ew.setIngredientIO(io == IO.IN ? IngredientIO.INPUT : IngredientIO.OUTPUT);

        if (storage instanceof List<?>) {
            // noinspection unchecked
            var list = (List<CycleEntityEntryHandler>) storage;
            ew.setCycle(list.get(index));
        }
        if (content != null) {
            ew.setXEIChance((float) content.getChance() / IChancedIngredient.MAX_CHANCE);
        }
    }

    @Override
    public boolean isRecipeSearchFilter() {
        return true;
    }

    @Override
    public List<AbstractMapIngredient> getMapIngredients(EntityIngredient content) {
        List<AbstractMapIngredient> ingredients = new ArrayList<>();
        ingredients.addAll(EntityTypeMapIngredient.from(content));
        ingredients.addAll(EntityTagMapIngredient.from(content));
        return ingredients;
    }

    public static String getTranslationKey(boolean isInput) {
        return isInput ? "ctnhbio.recipe.input_entity" : "ctnhbio.recipe.output_entity";
    }
}
