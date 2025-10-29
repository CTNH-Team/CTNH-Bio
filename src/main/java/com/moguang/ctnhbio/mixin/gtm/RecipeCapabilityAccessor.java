package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = RecipeCapability.class, remap = false)
public interface RecipeCapabilityAccessor {
    @Mutable
    @Accessor("name")
    void setName(String name);

    @Mutable
    @Accessor("sortIndex")
    void setSortIndex(int index);
}
