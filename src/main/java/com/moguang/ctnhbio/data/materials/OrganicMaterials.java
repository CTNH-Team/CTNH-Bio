package com.moguang.ctnhbio.data.materials;

import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.init.ModSerums;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttribute;
import com.moguang.ctnhbio.CTNHBio;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.function.Supplier;

public class OrganicMaterials {
    public static FluidAttribute ORGANIC = new FluidAttribute(
            CTNHBio.id("organic"),
            list -> list.accept(Component.translatable("ctnhbio.fluid.type_organic.tooltip")),
            list -> list.accept(Component.translatable("ctnhbio.fluid_pipe.can_handle_organic"))

    );

    public static Material Regenerate_Fluid;
    public static Material Wither_Slime;
    public static Material Endocrine_Hormone;
    public static Material Toxin_Extract;
    public static Material Bile;
    public static Material Mutagenic_Secretion;
    public static Material Organic_Compound;
    public static Material Unstable_Compound;
    public static Material Genetic_Compound;
    public static Material Heterogeneous_Compound;
    public static Material Healing_Compound;
    public static Material Decay_Essence;
    public static Material Rejuvenation_Serum;
    public static Material Ageing_Serum;
    public static Material Enlargement_Serum;
    public static Material Shrinking_Serum;
    public static Material Breeding_Stimulant;
    public static Material Absorption_Boost;
    public static Material Cleansing_Serum;
    public static Material Frenzy_Serum;
    public static Material Insomnia_Cure;

    

    public static void register() {
// 1. 凋零软泥
        Wither_Slime = new Material.Builder(GTCEu.id("wither_slime"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xA0A0A0)
                .buildAndRegister();

// 2. 内分泌激素
        Endocrine_Hormone = new Material.Builder(GTCEu.id("endocrine_hormone"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFFA500)
                .buildAndRegister();

// 3. 毒素提取物
        Toxin_Extract = new Material.Builder(GTCEu.id("toxin_extract"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x00FF00)
                .buildAndRegister();

// 4. 胆汁
        Bile = new Material.Builder(GTCEu.id("bile"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x006400)
                .buildAndRegister();

// 5. 激变分泌液
        Mutagenic_Secretion = new Material.Builder(GTCEu.id("mutagenic_secretion"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFF4500)
                .buildAndRegister();

// 6. 再生粘液
        Regenerate_Fluid = new Material.Builder(GTCEu.id("regenerate_fluid"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x8BC34A)
                .buildAndRegister();
// 6.  有机化合物
        Organic_Compound = new Material.Builder(GTCEu.id("organic_compound"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x00FFFF)
                .buildAndRegister();

// 7. 不稳定化合物
        Unstable_Compound = new Material.Builder(GTCEu.id("unstable_compound"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFF4500)
                .buildAndRegister();

// 8. 遗传化合物
        Genetic_Compound = new Material.Builder(GTCEu.id("genetic_compound"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFFFF00)
                .buildAndRegister();

// 9. 异质化合物
        Heterogeneous_Compound = new Material.Builder(GTCEu.id("heterogeneous_compound"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x00008B)
                .buildAndRegister();

// 10. 治愈化合物
        Healing_Compound = new Material.Builder(GTCEu.id("healing_compound"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x90EE90)
                .buildAndRegister();

// 11. 衰败原液
        Decay_Essence = new Material.Builder(GTCEu.id("decay_essence"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x000033)
                .buildAndRegister();

// 12. 活力血清
        Rejuvenation_Serum = new Material.Builder(GTCEu.id("rejuvenation_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x20B2AA)
                .buildAndRegister();

// 13. 成长血清
        Ageing_Serum = new Material.Builder(GTCEu.id("ageing_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x9ACD32)
                .buildAndRegister();

// 14. 巨化血清
        Enlargement_Serum = new Material.Builder(GTCEu.id("enlargement_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFFA500)
                .buildAndRegister();

// 15. 缩小血清
        Shrinking_Serum = new Material.Builder(GTCEu.id("shrinking_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x9ACD32)
                .buildAndRegister();

// 16. 配种兴奋剂
        Breeding_Stimulant = new Material.Builder(GTCEu.id("breeding_stimulant"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFF00FF)
                .buildAndRegister();

// 17. 伤痛反应剂
        Absorption_Boost = new Material.Builder(GTCEu.id("absorption_boost"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xFFFF00)
                .buildAndRegister();

// 18. 净化血清
        Cleansing_Serum = new Material.Builder(GTCEu.id("cleansing_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x800080)
                .buildAndRegister();

// 19. 狂化血清
        Frenzy_Serum = new Material.Builder(GTCEu.id("frenzy_serum"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0x8B0000)
                .buildAndRegister();

//20.  失眠抑制剂
        Insomnia_Cure = new Material.Builder(GTCEu.id("insomnia_cure"))
                .liquid(new FluidBuilder().attribute(ORGANIC))
                .color(0xD1001C)
                .buildAndRegister();



    }


}
