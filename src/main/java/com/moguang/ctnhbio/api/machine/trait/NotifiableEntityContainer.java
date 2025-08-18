package com.moguang.ctnhbio.api.machine.trait;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.ICapabilityTrait;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableRecipeHandlerTrait;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.lowdragmc.lowdraglib.syncdata.ISubscription;
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

    protected boolean shouldNotify;

    public NotifiableEntityContainer(MetaMachine machine, AABB boundingBox, IO io, boolean shouldNotify) {
        super(machine);
        this.boundingBox = boundingBox;
        this.handlerIO = io;
        this.shouldNotify = shouldNotify;
    }
    public NotifiableEntityContainer(MetaMachine machine, AABB boundingBox, IO io) {
        this(machine, boundingBox, io, io==IO.IN);
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

    //Notify
    protected TickableSubscription subscription;

    private ISubscription rlSubscription;
    @Override
    public void onMachineLoad() {
        updateTickSubscription();
        if(machine instanceof IRecipeLogicMachine rlm){
            rlSubscription = addChangedListener(rlm.getRecipeLogic()::updateTickSubscription);
        }
    }

    @Override
    public void onMachineUnLoad() {
        unsubscribe();
        if(rlSubscription != null){
            rlSubscription.unsubscribe();
            rlSubscription = null;
        }
    }

    private void unsubscribe() {
        if(subscription!= null){
            subscription.unsubscribe();
            subscription = null;
        }
    }

    //配方机器自动处理,非配方机器需要手动处理shouldNotify
    public void updateTickSubscription() {
        if(!shouldNotify ||
                (machine instanceof IRecipeLogicMachine rlm && !rlm.isWorkingEnabled())){
            unsubscribe();
        }
        else{
            subscription = getMachine().subscribeServerTick(subscription, this::serverTick);
        }
    }
    private int lastHashCode;
    private int lastCount = -1;
    public void serverTick() {
        Level level = getLevel();
        if (level == null || level.getGameTime() % 20 != 0) return;
        var entities = getAllEntities();
        if(lastCount != entities.size()){
            notifyListeners();
            lastCount = entities.size();
        } else if(lastHashCode != entities.hashCode()){
            notifyListeners();
            lastHashCode = entities.hashCode();
        }
    }
    public void startNotify() {
        shouldNotify = true;
        updateTickSubscription();
    }
    public void stopNotify() {
        shouldNotify = false;
        unsubscribe();
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
