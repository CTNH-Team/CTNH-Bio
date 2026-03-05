package com.moguang.ctnhbio.mixin.hostilenetworks;

import dev.shadowsoffire.hostilenetworks.jei.HostileJeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = HostileJeiPlugin.class, remap = false)
public class HostileJeiPluginMixin {

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void registerRecipes(IRecipeRegistration reg) {}
}
