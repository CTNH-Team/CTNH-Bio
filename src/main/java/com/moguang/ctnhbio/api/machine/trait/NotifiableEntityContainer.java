package com.moguang.ctnhbio.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.ICapabilityTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.moguang.ctnhbio.api.capability.IEntityContainer;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import lombok.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class NotifiableEntityContainer extends NotifiableRecipeHandlerTrait<EntityIngredient> implements IEntityContainer, ICapabilityTrait {

    @Getter
    public final IO handlerIO;
    @Getter
    public final RecipeCapability<EntityIngredient> capability = EntityRecipeCapability.CAP;
    @Getter
    public final AABB boundingBox;

    public NotifiableEntityContainer(MetaMachine machine, AABB boundingBox, IO io) {
        super(machine);
        this.boundingBox = boundingBox;
        this.handlerIO = io;
    }

    @Override @Nullable
    public List<EntityIngredient> handleRecipeInner(IO io, GTRecipe recipe, List<EntityIngredient> left, boolean simulate) {
        if (!handlerIO.support(io)) return left;
        if (io != IO.IN && io != IO.OUT) return left.isEmpty() ? null : left;

        ArrayList<EntityIngredient> ret = new ArrayList<>();
        if(io == IO.IN){
            var cache = new LinkedList<>(getAllEntities());
            ArrayList<Entity> toRemove = new ArrayList<>(); //实际上是交集
            for (EntityIngredient ingredient : left) {
                var iter = cache.iterator();

                while (iter.hasNext() && ingredient.count > 0) {
                    Entity entity = iter.next();

                    if (ingredient.test(entity)) {
                        ingredient.count--;
                        //remove
                        iter.remove(); //不能用cache.remove(entity)
                        toRemove.add(entity);
                        if(ingredient.count <= 0) break;
                    }
                }

                if(ingredient.count > 0) ret.add(ingredient);
            }
            if(!simulate) toRemove.forEach(this::removeEntity);
        } else { // io == IO.OUT
            Level level = getLevel();
            Predicate<Entity> detector = simulate ? this::canAddEntity : this::addEntity;

            for (EntityIngredient ingredient : left) {
                Entity entity = ingredient.createEntity(level);

                while(ingredient.count > 0){
                    if(!detector.test(entity)) break;
                    ingredient.count--;
                }
                if(ingredient.count > 0) ret.add(ingredient);
            }
        }
        return ret.isEmpty() ? null : ret; //差点忘了要null才跑配方
    }

    @Override
    public @NotNull List<Object> getContents() {
        return new ArrayList<>(getAllEntities());
    }

    @Override
    public double getTotalContentAmount() {
        return getAllEntities().size();
    }

    @Override
    public Level getLevel() {
        return machine.getLevel();
    }

    @Override
    public IO getCapabilityIO() {
        return handlerIO;
    }
}
