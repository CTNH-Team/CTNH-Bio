package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = FluidBuilder.class, remap = false)
public class FluidBuilderMixin {

    @Shadow
    private ResourceLocation still = null;
    @Shadow
    private ResourceLocation flowing = null;
    @Shadow
    private boolean hasCustomStill = false;
    @Shadow
    private boolean hasCustomFlowing = false;
    @Shadow
    private String name = null;
    /**
     * @author luckyblock
     * @reason fix custom still texture
     */
    @Overwrite
    private void determineTextures(@NotNull Material material, @Nullable FluidStorageKey key, @NotNull String modid){
        if(still == null){
            if (!material.isNull() && key != null) {
                if (hasCustomStill) {
                    still = ResourceLocation.tryBuild(modid, "block/fluids/fluid." + name);
                } else {
                    still = key.getIconType().getBlockTexturePath(material.getMaterialIconSet(), true);
                }
            } else {
                still = ResourceLocation.tryBuild(modid, "block/fluids/fluid." + name);
            }
        }

        if (hasCustomFlowing) {
            flowing = ResourceLocation.tryBuild(modid, "block/fluids/fluid." + name + "_flow");
        } else {
            flowing = still;
        }
    }
}
