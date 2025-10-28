package com.moguang.ctnhbio.utils;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeHandler;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.IRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;

import java.util.ArrayList;
import java.util.List;

public class MetaMachineUtils {
    public static List<RecipeHandlerList> getRecipeHandlers(MetaMachine machine) {
        int paintingColor = machine.getPaintingColor();
        var traits = machine.getTraits();
        List<IRecipeHandler<?>> inHandlers = new ArrayList<>(),outHandlers = new ArrayList<>();
        for(var trait : traits){
            if(trait instanceof IRecipeHandlerTrait<?> rht) {
                var io = rht.getHandlerIO();
                if (io.support(IO.IN)) inHandlers.add(rht);
                if (io.support(IO.OUT)) outHandlers.add(rht);
            }
        }
        List<RecipeHandlerList> handlers = new ArrayList<>();
        if(!inHandlers.isEmpty()) handlers.add(RecipeHandlerList.of(IO.IN, paintingColor, inHandlers));
        if(!outHandlers.isEmpty()) handlers.add(RecipeHandlerList.of(IO.OUT, paintingColor, outHandlers));
        return handlers;
    }
    public static List<RecipeHandlerList> getRecipeHandlers(MetaMachine machine, Iterable<IRecipeHandlerTrait<?>> certainHandlers) {
        int paintingColor = machine.getPaintingColor();
        List<IRecipeHandler<?>> inHandlers = new ArrayList<>(),outHandlers = new ArrayList<>();
        for(var rht : certainHandlers){
            var io = rht.getHandlerIO();
            if(io.support(IO.IN)) inHandlers.add(rht);
            if(io.support(IO.OUT)) outHandlers.add(rht);
        }
        List<RecipeHandlerList> handlers = new ArrayList<>();
        if(!inHandlers.isEmpty()) handlers.add(RecipeHandlerList.of(IO.IN, paintingColor, inHandlers));
        if(!outHandlers.isEmpty()) handlers.add(RecipeHandlerList.of(IO.OUT, paintingColor, outHandlers));
        return handlers;
    }
    public static List<RecipeHandlerList> getRecipeHandlers(MetaMachine machine, IRecipeHandlerTrait<?>... certainHandlers) {
         return getRecipeHandlers(machine, List.of(certainHandlers));
    }
}
