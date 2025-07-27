package com.moguang.ctnhbio.mixin.gtm;


import com.gregtechceu.gtceu.api.item.component.ThermalFluidStats;
import com.gregtechceu.gtceu.client.TooltipsHandler;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTUtil;
import com.moguang.ctnhbio.api.item.component.OrganicFluidStats;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidUtil;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(value = ThermalFluidStats.class, remap = false)
public class ThermalFluidStatsMixin {


    @Unique
    public ThermalFluidStats self = (ThermalFluidStats)(Object)this;
    /**
     * @author LuckyBlock
     * @reason Add Organic Fluid Info
     */
    @Overwrite
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
                                TooltipFlag isAdvanced) {
        if (stack.hasTag()) {
            FluidUtil.getFluidContained(stack).ifPresent(tank -> {
                tooltipComponents
                        .add(Component.translatable("gtceu.universal.tooltip.fluid_stored", tank.getDisplayName(),
                                tank.getAmount()));
                TooltipsHandler.appendFluidTooltips(tank, tooltipComponents::add, null);
            });
        } else {
            tooltipComponents.add(Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity",
                    FormattingUtil.formatNumbers(self.capacity)));
        }
        if (GTUtil.isShiftDown()) {
            tooltipComponents.add(Component.translatable("gtceu.fluid_pipe.max_temperature",
                    FormattingUtil.formatNumbers(self.maxFluidTemperature)));
            if (self.gasProof) tooltipComponents.add(Component.translatable("gtceu.fluid_pipe.gas_proof"));
            else tooltipComponents.add(Component.translatable("gtceu.fluid_pipe.not_gas_proof"));
            if (self.plasmaProof) tooltipComponents.add(Component.translatable("gtceu.fluid_pipe.plasma_proof"));
            if (self.cryoProof) tooltipComponents.add(Component.translatable("gtceu.fluid_pipe.cryo_proof"));
            if (self.acidProof) tooltipComponents.add(Component.translatable("gtceu.fluid_pipe.acid_proof"));

            if(self instanceof OrganicFluidStats) tooltipComponents.add(Component.translatable("ctnhbio.fluid_pipe.can_handle_organic"));
            else tooltipComponents.add(Component.translatable("ctnhbio.fluid_pipe.cannot_handle_organic"));

        } else{
            tooltipComponents.add(Component.translatable("gtceu.tooltip.fluid_pipe_hold_shift"));
        }
    }
}
