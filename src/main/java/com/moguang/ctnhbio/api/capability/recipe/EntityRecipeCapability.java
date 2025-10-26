package com.moguang.ctnhbio.api.capability.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.ui.GTRecipeTypeUI;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.jei.IngredientIO;
import com.moguang.ctnhbio.api.gui.widget.EntityWidget;
import com.moguang.ctnhbio.api.machine.trait.NotifiableEntityContainer;
import com.moguang.ctnhbio.api.recipe.content.SerializerEntityIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import com.moguang.ctnhbio.integration.xei.entry.entity.EntityEntryList;
import com.moguang.ctnhbio.integration.xei.handlers.entity.CycleEntityEntryHandler;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityRecipeCapability extends RecipeCapability<EntityIngredient> {
    public static final EntityRecipeCapability CAP = new EntityRecipeCapability();
    protected EntityRecipeCapability() {
        super("entity", 0xf5424200, true, 5, SerializerEntityIngredient.INSTANCE);
    }

    @Override
    public EntityIngredient copyInner(EntityIngredient content) {
        return content.copy();
    }

    @Override
    public EntityIngredient copyWithModifier(EntityIngredient content, ContentModifier modifier) {
        final var ret = content.copy();
        ret.count = modifier.apply(ret.count);
        return ret;
    }

    @Override
    public int getMaxParallelByInput(IRecipeCapabilityHolder holder, GTRecipe recipe, int limit, boolean tick) {
        if (!holder.hasCapabilityProxies()) return 0;

        var inputs = (tick ? recipe.tickInputs : recipe.inputs).get(this);
        if (inputs == null || inputs.isEmpty()) return limit;

        // Get all entity containers from the holder
        var handlers = holder.getCapabilitiesFlat(IO.IN, this);
        if (handlers.isEmpty()) return 0;

        // Separate consumable and non-consumable ingredients
        var nonConsumables = new ArrayList<EntityIngredient>();
        var consumables = new ArrayList<EntityIngredient>();

        for (Content content : inputs) {
            EntityIngredient ingredient = (EntityIngredient) content.content;
//            if (content.chance == 0) {
//                nonConsumables.add(ingredient.copy());
//            } else {
//                consumables.add(ingredient.copy());
//            }
            consumables.add(ingredient.copy());
        }

        // If no consumables, just check non-consumables
        if (consumables.isEmpty()) {
            for (var handler : handlers) {
                for (var nc : nonConsumables) {
                    if (!handler.getContents().contains(nc)) {
                        return 0; // Missing required non-consumable
                    }
                }
            }
            return limit;
        }

        int maxMultiplier = 0;

        // Check each handler's capacity
        for (var handler : handlers) {
            // First check non-consumables
            boolean hasAllNonConsumables = true;
            for (var nc : nonConsumables) {
                if (!handler.getContents().contains(nc)) {
                    hasAllNonConsumables = false;
                    break;
                }
            }
            if (!hasAllNonConsumables) continue;

            // Then calculate parallel based on consumables
            int handlerMultiplier = Integer.MAX_VALUE;

            for (var c : consumables) {
                int required = c.count;
                int available = 0;

                // Count matching entities in this handler
                for (Object content : handler.getContents()) {
                    if (content instanceof Entity entity && c.test(entity)) {
                        available++;
                    }
                }

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

    @Override
    public boolean doAddGuiSlots() {
        return true;
    }

    @Override
    public @NotNull List<Object> createXEIContainerContents(List<Content> contents, GTRecipe recipe, IO io) {
        List<Object> entryLists = contents.stream()
                .map(Content::getContent)
                .map(this::of)
                .map(i->new EntityEntryList(i,true))
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
                .map(obj->(EntityEntryList) obj)
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
                                @Nullable("null when content == null") GTRecipe recipe,
                                @Nullable Content content,
                                @Nullable Object storage, int recipeTier, int chanceTier){
        if(!isXEI || storage == null)return;

        EntityWidget ew = (EntityWidget) widget;
        ew.setIngredientIO(io == IO.IN ? IngredientIO.INPUT : IngredientIO.OUTPUT);

        if(storage instanceof List<?>){
            //noinspection unchecked
            var list = (List<CycleEntityEntryHandler>) storage;
            ew.setCycle(list.get(index));
        }
        if(content != null) {
            EntityIngredient ingredient = (EntityIngredient) (content.content);
            ew.setCount(ingredient.count);
            ew.setChance((float) recipeType.getChanceFunction()
                    .getBoostedChance(content, recipeTier, chanceTier) / content.maxChance);
        }
    }

    public static String getTranslationKey(boolean isInput){
        return isInput? "ctnhbio.recipe.input_entity" : "ctnhbio.recipe.output_entity";
    }
}
