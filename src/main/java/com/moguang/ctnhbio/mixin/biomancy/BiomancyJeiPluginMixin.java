package com.moguang.ctnhbio.mixin.biomancy;

import com.github.elenterius.biomancy.integration.jei.BiomancyJeiPlugin;
import mezz.jei.api.registration.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(value = BiomancyJeiPlugin.class, remap = false)
public class BiomancyJeiPluginMixin {
    /**
     * @author luckyblock
     * @reason hide original decompose recipes display
     */
    @Overwrite
    public void registerCategories(IRecipeCategoryRegistration registration) {}

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void registerRecipes(IRecipeRegistration registration) {}


    /**
     * @author
     * @reason
     */
    @Overwrite
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {}

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {}


    /**
     * @author
     * @reason
     */
    @Overwrite
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {}
}
