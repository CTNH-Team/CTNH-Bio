package com.moguang.ctnhbio.integration.xei.handlers.entity;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityPropertyDetector;
import com.moguang.ctnhbio.integration.xei.entry.entity.EntityEntryList;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CycleEntityEntryHandler {
    final EntityEntryList entityList;
    public final List<Component> tooltips;
    int index = 0;
    public CycleEntityEntryHandler(EntityEntryList entityList){
        this.entityList = entityList;
        tooltips = entityList.buildTooltip();
    }

    public boolean isEmpty(){
        return entityList.entries.isEmpty();
    }

    public EntityType<?> currentType(){
        return entityList.entries.get(Math.abs((int) (System.currentTimeMillis() / 1000L) % entityList.entries.size()));
    }
    public Entity currentEntity(@NotNull Minecraft mc){
        Entity entity = currentType().create(mc.level);

        if (entity == null) return null;
        if (entityList.nbt!=null) entity.load(EntityPropertyDetector.getNormalizedNBT(entityList.nbt));
        return entity;
    }
    public List<EntityType<?>> getEntries(){
        return entityList.entries;
    }
}
