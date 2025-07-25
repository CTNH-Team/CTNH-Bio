package com.moguang.ctnhbio.data.materials;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttribute;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttributes;
import com.moguang.ctnhbio.CTNHBio;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class OrganicMaterials {
    public static FluidAttribute ORGANIC = new FluidAttribute(
            CTNHBio.id("organic"),
            list -> list.accept(Component.translatable("ctnhbio.fluid.type_organic.tooltip")),
            list -> list.accept(Component.translatable("ctnhbio.fluid_pipe.can_handle_organic"))

    );

    public static Material Regenerate_Fluid;

    public static void register() {
        Regenerate_Fluid = new Material.Builder(GTCEu.id("regenerate_fluid"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .buildAndRegister();
    }


}
