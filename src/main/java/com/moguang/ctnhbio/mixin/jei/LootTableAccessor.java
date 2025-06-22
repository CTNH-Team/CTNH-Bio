package com.moguang.ctnhbio.mixin.jei;

import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = LootTable.class, remap = false)
public interface LootTableAccessor {

    /**
     * Accesses the private field `randomSequence` from LootTable.
     */
    @Accessor("randomSequence")
    ResourceLocation getRandomSequence();
}

