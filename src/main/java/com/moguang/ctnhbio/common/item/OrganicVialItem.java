package com.moguang.ctnhbio.common.item;

import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.api.serum.SerumContainer;
import com.github.elenterius.biomancy.init.ModSerums;
import com.gregtechceu.gtceu.api.item.ComponentItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;
import static net.minecraftforge.fluids.FluidUtil.getFluidContained;

public class OrganicVialItem extends ComponentItem implements SerumContainer {
    public static final HashMap<Fluid, Supplier<? extends Serum>> SERUM_FLUID_MAP = new HashMap<>();

    static {
        SERUM_FLUID_MAP.put(Ageing_Serum.getFluid(), ModSerums.AGEING_SERUM);
        SERUM_FLUID_MAP.put(Enlargement_Serum.getFluid(), ModSerums.ENLARGEMENT_SERUM);
        SERUM_FLUID_MAP.put(Shrinking_Serum.getFluid(), ModSerums.SHRINKING_SERUM);
        SERUM_FLUID_MAP.put(Rejuvenation_Serum.getFluid(), ModSerums.REJUVENATION_SERUM);
        SERUM_FLUID_MAP.put(Breeding_Stimulant.getFluid(), ModSerums.BREEDING_STIMULANT);
        SERUM_FLUID_MAP.put(Absorption_Boost.getFluid(), ModSerums.ABSORPTION_BOOST);
        SERUM_FLUID_MAP.put(Insomnia_Cure.getFluid(), ModSerums.INSOMNIA_CURE);
        SERUM_FLUID_MAP.put(Cleansing_Serum.getFluid(), ModSerums.CLEANSING_SERUM);
        SERUM_FLUID_MAP.put(Frenzy_Serum.getFluid(), ModSerums.FRENZY_SERUM);
    }


    public OrganicVialItem(Properties properties) {
        super(properties);
    }

    public static Serum getSerumFromStack(ItemStack stack) {
            return ((Optional<Serum>)(getFluidContained(stack)
                    .map(FluidStack::getFluid)
                    .map(SERUM_FLUID_MAP::get)
                    .map(Supplier::get))).orElse(Serum.EMPTY);

    }


    @Override
    public Serum getSerum() {
        return Serum.EMPTY;
    }
}
