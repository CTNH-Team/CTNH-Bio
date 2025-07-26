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
// 1. 凋零软泥
        Regenerate_Fluid = new Material.Builder(GTCEu.id("wither_slime"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xA0A0A0)
                .buildAndRegister();

// 2. 内分泌激素
        Regenerate_Fluid = new Material.Builder(GTCEu.id("endocrine_hormone"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFFA500)
                .buildAndRegister();

// 3. 毒素提取物
        Regenerate_Fluid = new Material.Builder(GTCEu.id("toxin_extract"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x00FF00)
                .buildAndRegister();

// 4. 胆汁
        Regenerate_Fluid = new Material.Builder(GTCEu.id("bile"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x006400)
                .buildAndRegister();

// 5. 激变分泌液
        Regenerate_Fluid = new Material.Builder(GTCEu.id("mutagenic_secretion"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFF4500)
                .buildAndRegister();

// 6. 有机化合物
        Regenerate_Fluid = new Material.Builder(GTCEu.id("regenerate_fluid"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x00FFFF)
                .buildAndRegister();

// 7. 不稳定化合物
        Regenerate_Fluid = new Material.Builder(GTCEu.id("unstable_compound"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFF4500)
                .buildAndRegister();

// 8. 遗传化合物
        Regenerate_Fluid = new Material.Builder(GTCEu.id("genetic_compound"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFFFF00)
                .buildAndRegister();

// 9. 异质化合物
        Regenerate_Fluid = new Material.Builder(GTCEu.id("heterogeneous_compound"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x00008B)
                .buildAndRegister();

// 10. 治愈化合物
        Regenerate_Fluid = new Material.Builder(GTCEu.id("healing_compound"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x90EE90)
                .buildAndRegister();

// 11. 衰败原液
        Regenerate_Fluid = new Material.Builder(GTCEu.id("decay_essence"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x000033)
                .buildAndRegister();

// 12. 活力血清
        Regenerate_Fluid = new Material.Builder(GTCEu.id("vitality_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x20B2AA)
                .buildAndRegister();

// 13. 成长血清
        Regenerate_Fluid = new Material.Builder(GTCEu.id("growth_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x9ACD32)
                .buildAndRegister();

// 14. 巨化血清
        Regenerate_Fluid = new Material.Builder(GTCEu.id("gigantism_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFFA500)
                .buildAndRegister();

// 15. 缩小血清
        Regenerate_Fluid = new Material.Builder(GTCEu.id("shrinking_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x9ACD32)
                .buildAndRegister();

// 16. 配种兴奋剂
        Regenerate_Fluid = new Material.Builder(GTCEu.id("breeding_stimulant"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFF00FF)
                .buildAndRegister();

// 17. 伤痛反应剂
        Regenerate_Fluid = new Material.Builder(GTCEu.id("pain_response_agent"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFFFF00)
                .buildAndRegister();

// 18. 净化血清
        Regenerate_Fluid = new Material.Builder(GTCEu.id("purification_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x800080)
                .buildAndRegister();

// 19. 狂化血清
        Regenerate_Fluid = new Material.Builder(GTCEu.id("berserk_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x8B0000)
                .buildAndRegister();
    }


}
