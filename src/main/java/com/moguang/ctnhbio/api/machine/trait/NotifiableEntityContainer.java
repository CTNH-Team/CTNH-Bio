package com.moguang.ctnhbio.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.ingredient.IChancedIngredient;
import com.gregtechceu.gtceu.api.recipe.lookup.ingredient.AbstractMapIngredient;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import com.moguang.ctnhbio.api.capability.IEntityContainer;
import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.ChancedEntityIngredient;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import com.moguang.ctnhbio.api.recipe.lookup.EntityTagMapIngredient;
import com.moguang.ctnhbio.api.recipe.lookup.EntityTypeMapIngredient;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class NotifiableEntityContainer extends NotifiableRecipeHandlerTrait<EntityIngredient>
                                       implements IEntityContainer {

    @Getter
    public final IO handlerIO;
    @Getter
    public final RecipeCapability<EntityIngredient> capability = EntityRecipeCapability.CAP;
    @Getter
    public final AABB boundingBox;

    protected boolean shouldNotify;

    public NotifiableEntityContainer(MetaMachine machine, AABB boundingBox, IO io, boolean shouldNotify) {
        super(machine);
        this.boundingBox = boundingBox;
        this.handlerIO = io;
        this.shouldNotify = shouldNotify;
    }

    public NotifiableEntityContainer(MetaMachine machine, AABB boundingBox, IO io) {
        this(machine, boundingBox, io, io.support(IO.IN));
    }

    @Override
    public boolean handleRecipe(IO io, GTRecipe recipe, List<EntityIngredient> left, boolean simulate) {
        if (!handlerIO.support(io)) return false;
        if (io != IO.IN && io != IO.OUT) return false;

        if (io == IO.IN) {
            var cache = new LinkedList<>(getAllEntities());
            ArrayList<Entity> toRemove = new ArrayList<>(); // 实际上是交集
            for (var it = left.listIterator(); it.hasNext();) {
                EntityIngredient ingredient = it.next();
                int amount = getRecipeAmount(ingredient, simulate);
                if (amount <= 0) {
                    it.remove();
                    continue;
                }
                var iter = cache.iterator();

                while (iter.hasNext()) {
                    Entity entity = iter.next();

                    if (ingredient.test(entity)) {
                        amount--;
                        iter.remove();
                        toRemove.add(entity);
                        if (amount <= 0) break;
                    }
                }

                if (amount == 0) {
                    it.remove();
                } else {
                    it.set(ingredient.copyWithCount(amount));
                }
            }
            if (!simulate) toRemove.forEach(this::removeEntity);
        } else { // io == IO.OUT
            Level level = getLevel();
            Predicate<Entity> detector = simulate ? this::canAddEntity : this::addEntity;

            for (var it = left.listIterator(); it.hasNext();) {
                EntityIngredient ingredient = it.next();
                int amount = getRecipeAmount(ingredient, simulate);
                if (amount <= 0) {
                    it.remove();
                    continue;
                }

                while (amount > 0) {
                    Entity entity = ingredient.createEntity(level);
                    if (!detector.test(entity)) break;
                    amount--;
                }
                if (amount <= 0) {
                    it.remove();
                } else {
                    it.set(ingredient.copyWithCount(amount));
                }
            }
        }
        return left.isEmpty();
    }

    private static int getRecipeAmount(EntityIngredient ingredient, boolean simulate) {
        if (simulate || !ingredient.isChanced()) return ingredient.count;
        ChancedEntityIngredient chanced = (ChancedEntityIngredient) ingredient;
        return chanced.getInner().count * IChancedIngredient.rollSuccesses(chanced.getMultiplier(),
                chanced.getChance());
    }

    // Notify
    protected TickableSubscription subscription;

    @Override
    public void onMachineLoad() {
        updateTickSubscription();
    }

    @Override
    public void onMachineUnLoad() {
        unsubscribe();
    }

    private void unsubscribe() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    // 配方机器自动处理,非配方机器需要手动处理shouldNotify
    public void updateTickSubscription() {
        if (!shouldNotify) {
            unsubscribe();
        } else {
            subscription = getMachine().subscribeServerTick(subscription, this::serverTick);
        }
    }

    IntOpenHashSet lastIds = new IntOpenHashSet();
    private int lastCount = -1;

    public void serverTick() {
        if (getMachine().getOffsetTimer() % 40 != 0) return;
        var entities = getAllEntities();
        if (lastCount != entities.size()) {
            lastCount = entities.size();
            notifyListeners();
        } else {
            IntOpenHashSet current = new IntOpenHashSet();
            for (Entity e : entities) {
                current.add(e.getId());
            }
            if (!current.equals(lastIds)) {
                lastIds = current;
                notifyListeners();
            }
        }
    }

    @Override
    public @NotNull List<Object> getContents() {
        return new ArrayList<>(getAllEntities());
    }

    @Override
    public @NotNull List<AbstractMapIngredient> getMapIngredients() {
        List<AbstractMapIngredient> ingredients = new ArrayList<>();
        for (Entity entity : getAllEntities()) {
            ingredients.addAll(EntityTypeMapIngredient.from(entity));
            ingredients.addAll(EntityTagMapIngredient.from(entity));
        }
        return ingredients;
    }

    @Override
    public double getTotalContentAmount() {
        return lastCount;
    }

    @Override
    public Level getLevel() {
        return machine.getLevel();
    }
}
