package com.moguang.ctnhbio.api.capability;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.EntityIngredient;
import com.moguang.ctnhbio.utils.RandomUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface IEntityContainer {
    AABB getBoundingBox();
    Level getLevel();
    default List<Entity> getAllEntities(){
        Level level = getLevel();
        if (level == null || level.isClientSide()) return Collections.emptyList();
        return level.getEntities(null, getBoundingBox());
    }
    default List<Entity> getEntities(EntityIngredient target){
        Level level = getLevel();
        if (level == null || level.isClientSide()) return Collections.emptyList();
        return level.getEntities((Entity) null, getBoundingBox(), target);
    }
    default boolean addEntity(Entity entity){
        Level level = getLevel();
        if (level == null || level.isClientSide()) return false;
        entity.setPos(RandomUtils.randPosIn(getBoundingBox()));
        return level.addFreshEntity(entity);
    }
    default boolean addEntity(EntityIngredient target){
        return addEntity(target.createEntity(getLevel()));
    }
    default boolean canAddEntity(Entity entity){
        Level level = getLevel();
        if (level == null || level.isClientSide()) return false;
        //TODO: check if entity can be spawned
        return true;
    }
    default boolean canAddEntity(EntityIngredient target){
        return canAddEntity(target.createEntity(getLevel()));
    }
    default List<Entity> addEntities(Collection<Entity> entities){
        List<Entity> left = new ArrayList<>();
        for (Entity entity : entities) {
            if (!addEntity(entity)) {
                left.add(entity);
            }
        }
        return left;
    }
    default void removeEntity(Entity entity){
        entity.remove(Entity.RemovalReason.DISCARDED);
    }
    default void removeAllEntities(){
        Level level = getLevel();
        if (level == null || level.isClientSide()) return;
        for (Entity entity : getAllEntities()) {
            entity.remove(Entity.RemovalReason.DISCARDED);
        }
    }
}
