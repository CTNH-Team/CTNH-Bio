package com.moguang.ctnhbio.utils;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.handler.RecipeHandlerList;

import java.util.ArrayList;
import java.util.List;

public class MetaMachineUtils {

    public static List<RecipeHandlerList> getRecipeHandlers(MetaMachine machine) {
        int paintingColor = machine.getPaintingColor();
        var traits = machine.getTraits();
        List<IRecipeHandler<?>> handlers = new ArrayList<>();
        for (var trait : traits) {
            if (trait instanceof IRecipeHandler<?> handler) {
                handlers.add(handler);
            }
        }
        return handlers.isEmpty() ? List.of() : List.of(RecipeHandlerList.of(() -> paintingColor, handlers));
    }

    public static List<RecipeHandlerList> getRecipeHandlers(MetaMachine machine,
                                                             Iterable<IRecipeHandler<?>> certainHandlers) {
        int paintingColor = machine.getPaintingColor();
        List<IRecipeHandler<?>> handlers = new ArrayList<>();
        for (var handler : certainHandlers) {
            handlers.add(handler);
        }
        return handlers.isEmpty() ? List.of() : List.of(RecipeHandlerList.of(() -> paintingColor, handlers));
    }

    public static List<RecipeHandlerList> getRecipeHandlers(MetaMachine machine,
                                                             IRecipeHandler<?>... certainHandlers) {
        return getRecipeHandlers(machine, List.of(certainHandlers));
    }
}
