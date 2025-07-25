package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.fluids.FluidConstants;
import com.gregtechceu.gtceu.api.fluids.FluidState;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttribute;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttributes;
import com.gregtechceu.gtceu.api.fluids.attribute.IAttributedFluid;
import com.moguang.ctnhbio.api.item.component.IOrganicFluidHandler;
import com.moguang.ctnhbio.data.materials.OrganicMaterials;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import net.minecraftforge.fluids.FluidStack;
import com.gregtechceu.gtceu.api.capability.IThermalFluidHandlerItemStack;

import java.util.Collection;

@Mixin(value = IThermalFluidHandlerItemStack.class, remap = false)
public interface IThermalFluidHandlerItemStackMixin {

    /**
     * @author YourName
     * @reason 自定义流体填充逻辑，支持 ORGANIC 类型流体
     * @param stack 待检查的流体堆栈
     * @return 是否允许填充
     */
    @Overwrite
    default boolean canFillFluidType(FluidStack stack) {
        // 原逻辑的基础检查
        if (stack == null || stack.getFluid() == null) {
            return false;
        }

        Fluid fluid = stack.getFluid();
        FluidType fluidType = fluid.getFluidType();

        // 1. 检查气体和轻于空气的流体
        if (fluidType.isLighterThanAir() && !((IThermalFluidHandlerItemStack) this).isGasProof()) {
            return false;
        }

        // 2. 处理带属性的流体（如酸、等离子体等）
        if (fluid instanceof IAttributedFluid attributedFluid) {
            Collection<FluidAttribute> attributes = attributedFluid.getAttributes();

            // 检查酸性流体
            if (attributes.contains(FluidAttributes.ACID) && !((IThermalFluidHandlerItemStack) this).isAcidProof()) {
                return false;
            }


            FluidState fluidState = attributedFluid.getState();
            if (fluidState == FluidState.PLASMA && !((IThermalFluidHandlerItemStack) this).isPlasmaProof()) {
                return false;
            }
            if (fluidState == FluidState.GAS && !((IThermalFluidHandlerItemStack) this).isGasProof()) {
                return false;
            }
            // 新增对 ORGANIC 流体的检查
            if (attributes.contains(OrganicMaterials.ORGANIC) && !(this instanceof IOrganicFluidHandler)) {
                return false;
            }
        }

        // 3. 温度检查
        int temperature = fluidType.getTemperature(stack);
        if (temperature < FluidConstants.CRYOGENIC_FLUID_THRESHOLD && !((IThermalFluidHandlerItemStack) this).isCryoProof()) {
            return false;
        }

        // 4. 最终温度上限验证
        return temperature <= ((IThermalFluidHandlerItemStack) this).getMaxFluidTemperature();
    }

}
