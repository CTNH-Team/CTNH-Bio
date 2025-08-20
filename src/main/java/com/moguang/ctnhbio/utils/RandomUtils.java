package com.moguang.ctnhbio.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

@UtilityClass
public class RandomUtils {
    public final RandomSource RNG = com.gregtechceu.gtceu.api.GTValues.RNG;
    public double randDouble(double min, double max){
        return RNG.nextDouble() * (max - min) + min;
    }
    public Vec3 randPosIn(AABB aabb){
        return new Vec3(randDouble(aabb.minX, aabb.maxX), randDouble(aabb.minY, aabb.maxY), randDouble(aabb.minZ, aabb.maxZ));
    }
}
