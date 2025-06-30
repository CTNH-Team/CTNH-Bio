package com.moguang.ctnhbio.api;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface ILivingEntityHost<T extends LivingEntity> {
    // 核心方法（需实现）
    //CompoundTag entityTag = null;

    T getHostedEntity();
    void setHostedEntity(T entity);
    BlockPos getHostPos();

    default boolean isEntityHostAware(T entity) {
        return entity instanceof IHostAwareEntity;
    }

    // 默认方法（可覆盖）---
    default void spawnHostedEntity(Level level) {
        if (getHostedEntity() == null) {
            T entity = createHostedEntity(level);
            if(entity != null)
            {
                setHostedEntity(entity);
                if (isEntityHostAware(entity)) {
                    ((IHostAwareEntity) entity).bindToHost(this);
                }
            }

        }
    }

    default void despawnHostedEntity() {
        if (getHostedEntity() != null) {
            getHostedEntity().discard();
            setHostedEntity(null);
        }
    }

//    default void bindEntityToHost(T entity) {
//        entity.getPersistentData().putLong("HostID", getHostPos().asLong());
//    }

    // 持久化默认实现
    default void saveHostedEntityData(CompoundTag nbt) {
        if (getHostedEntity() != null) {
            CompoundTag entityTag = new CompoundTag();
            getHostedEntity().save(entityTag);
            nbt.put("HostedEntity", entityTag);
        }
    }

    @SuppressWarnings("unchecked")
    default void loadHostedEntityData(CompoundTag entityTag, Level level) {
        Entity entity = EntityType.loadEntityRecursive(entityTag, level, e -> e);
        if (entity != null && getEntityClass().isInstance(entity)) {
            setHostedEntity((T) entity);
            if (isEntityHostAware((T) entity)) {
                ((IHostAwareEntity) entity).bindToHost(this);
            }
        }
//        if (nbt.contains("HostedEntity")) {
            //CompoundTag entityTag = nbt.getCompound("HostedEntity");

//        }

    }

    // 生成生物时自动绑定宿主
//    default T createAndBindEntity(Level level) {
//        T entity = createHostedEntity(level);
//
//        return entity;
//    }

   void onHostedEntityRemoved(T entity);

    // 子类需实现的方法 ---
    T createHostedEntity(Level level);
    Class<T> getEntityClass(); // 用于NBT加载时类型检查
}
