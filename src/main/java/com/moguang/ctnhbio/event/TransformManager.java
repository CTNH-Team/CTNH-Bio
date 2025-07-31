package com.moguang.ctnhbio.event;

import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.logging.Level;

public class TransformManager {
    public static ArrayList<LivingEntity> FLESH_BLOB_LIST = new ArrayList<>();

    public static void addEntity(LivingEntity entity)
    {
        FLESH_BLOB_LIST.add(entity);
    }
}
