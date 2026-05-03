package com.moguang.ctnhbio.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttribute;

import net.minecraft.resources.ResourceLocation;

import com.ctnhlang.*;
import com.moguang.ctnhbio.CTNHBio;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

@Category("tooltip")
public class OrganicMaterials {

    @CN({
            "§a生物活性流体",
            "§a可传输生物活性流体"
    })
    @EN({
            "§aBioactive Fluid",
            "§aCan transport Bioactive Fluid"
    })
    static Lang[] organic;
    public static FluidAttribute ORGANIC = new FluidAttribute(
            CTNHBio.id("organic"),
            list -> list.accept(organic[0].translate()),
            list -> list.accept(organic[1].translate()));

    public static Material Regenerative_Fluid;
    public static Material Withering_Ooze;
    public static Material Hormone_Secretion;
    public static Material Toxin_Extract;
    public static Material Bile;
    public static Material Volatile_Fluid;
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
    public static Material Primordial_Serum;

    public static void register() {
        // 凋灵软泥 (已存在示例)
        Withering_Ooze = REGISTRATE.material(CTNHBio.id("withering_ooze"))
                .lang("Withering Ooze")
                .cnlang("凋灵软泥")
                .liquid(organicBuilder())
                .color(0xA0A0A0)
                .buildAndRegister();

        // 1. 再生粘液
        Regenerative_Fluid = REGISTRATE.material(CTNHBio.id("regenerative_fluid"))
                .lang("Regenerative Fluid")
                .cnlang("再生粘液")
                .liquid(organicBuilder())
                .color(0x8BC34A)
                .buildAndRegister();

        // 2. 内分泌激素
        Hormone_Secretion = REGISTRATE.material(CTNHBio.id("hormone_secretion"))
                .lang("Hormone Secretion")
                .cnlang("内分泌激素")
                .liquid(organicBuilder())
                .color(0xFFA500)
                .buildAndRegister();

        // 3. 毒素提取物
        Toxin_Extract = REGISTRATE.material(CTNHBio.id("toxin_extract"))
                .lang("Toxin Extract")
                .cnlang("毒素提取物")
                .liquid(organicBuilder())
                .color(0x00FF00)
                .buildAndRegister();

        // 4. 胆汁
        Bile = REGISTRATE.material(CTNHBio.id("bile"))
                .lang("Bile")
                .cnlang("胆汁")
                .liquid(organicBuilder())
                .color(0x006400)
                .buildAndRegister();

        // 5. 激变分泌液
        Volatile_Fluid = REGISTRATE.material(CTNHBio.id("volatile_fluid"))
                .lang("Volatile Fluid")
                .cnlang("激变分泌液")
                .liquid(organicBuilder())
                .color(0xFF4500)
                .buildAndRegister();

        // 6. 有机化合物
        Organic_Compound = REGISTRATE.material(CTNHBio.id("organic_compound"))
                .lang("Organic Compound")
                .cnlang("有机化合物")
                .liquid(organicBuilder())
                .color(0x00FFFF)
                .buildAndRegister();

        // 7. 不稳定化合物
        Unstable_Compound = REGISTRATE.material(CTNHBio.id("unstable_compound"))
                .lang("Unstable Compound")
                .cnlang("不稳定化合物")
                .liquid(organicBuilder())
                .color(0xFF4500)
                .buildAndRegister();

        // 8. 遗传化合物
        Genetic_Compound = REGISTRATE.material(CTNHBio.id("genetic_compound"))
                .lang("Genetic Compound")
                .cnlang("遗传化合物")
                .liquid(organicBuilder())
                .color(0xFFFF00)
                .buildAndRegister();

        // 9. 异质化合物
        Heterogeneous_Compound = REGISTRATE.material(CTNHBio.id("heterogeneous_compound"))
                .lang("Heterogeneous Compound")
                .cnlang("异质化合物")
                .liquid(organicBuilder())
                .color(0x00008B)
                .buildAndRegister();

        // 10. 治愈化合物
        Healing_Compound = REGISTRATE.material(CTNHBio.id("healing_compound"))
                .lang("Healing Compound")
                .cnlang("治愈化合物")
                .liquid(organicBuilder())
                .color(0x90EE90)
                .buildAndRegister();

        // 11. 衰败原液
        Decay_Essence = REGISTRATE.material(CTNHBio.id("decay_essence"))
                .lang("Decay Essence")
                .cnlang("衰败原液")
                .liquid(organicBuilder())
                .color(0x000033)
                .buildAndRegister();

        // 12. 活力血清
        Rejuvenation_Serum = REGISTRATE.material(CTNHBio.id("rejuvenation_serum"))
                .lang("Rejuvenation Serum")
                .cnlang("活力血清")
                .liquid(organicBuilder())
                .color(0x20B2AA)
                .buildAndRegister();

        // 13. 成长血清
        Ageing_Serum = REGISTRATE.material(CTNHBio.id("ageing_serum"))
                .lang("Ageing Serum")
                .cnlang("成长血清")
                .liquid(organicBuilder())
                .color(0x9ACD32)
                .buildAndRegister();

        // 14. 巨化血清
        Enlargement_Serum = REGISTRATE.material(CTNHBio.id("enlargement_serum"))
                .lang("Enlargement Serum")
                .cnlang("巨化血清")
                .liquid(organicBuilder())
                .color(0xFFA500)
                .buildAndRegister();

        // 15. 缩小血清
        Shrinking_Serum = REGISTRATE.material(CTNHBio.id("shrinking_serum"))
                .lang("Shrinking Serum")
                .cnlang("缩小血清")
                .liquid(organicBuilder())
                .color(0x9ACD32)
                .buildAndRegister();

        // 16. 配种兴奋剂
        Breeding_Stimulant = REGISTRATE.material(CTNHBio.id("breeding_stimulant"))
                .lang("Breeding Stimulant")
                .cnlang("配种兴奋剂")
                .liquid(organicBuilder())
                .color(0xFF00FF)
                .buildAndRegister();

        // 17. 伤痛反应剂
        Absorption_Boost = REGISTRATE.material(CTNHBio.id("absorption_boost"))
                .lang("Absorption Boost")
                .cnlang("伤痛反应剂")
                .liquid(organicBuilder())
                .color(0xFFFF00)
                .buildAndRegister();

        // 18. 净化血清
        Cleansing_Serum = REGISTRATE.material(CTNHBio.id("cleansing_serum"))
                .lang("Cleansing Serum")
                .cnlang("净化血清")
                .liquid(organicBuilder())
                .color(0x800080)
                .buildAndRegister();

        // 19. 狂化血清
        Frenzy_Serum = REGISTRATE.material(CTNHBio.id("frenzy_serum"))
                .lang("Frenzy Serum")
                .cnlang("狂化血清")
                .liquid(organicBuilder())
                .color(0x8B0000)
                .buildAndRegister();

        // 20. 失眠抑制剂
        Insomnia_Cure = REGISTRATE.material(CTNHBio.id("insomnia_cure"))
                .lang("Insomnia Cure")
                .cnlang("失眠抑制剂")
                .liquid(organicBuilder())
                .color(0xD1001C)
                .buildAndRegister();

        // 21. 原初血清
        Primordial_Serum = REGISTRATE.material(CTNHBio.id("primordial_serum"))
                .lang("Primordial Serum")
                .cnlang("原初血清")
                .liquid(organicBuilder())
                .color(0xac4a5a)
                .buildAndRegister();
    }

    @SuppressWarnings("all")
    public static FluidBuilder organicBuilder() {
        return new FluidBuilder().attribute(ORGANIC).still(ResourceLocation.parse("create:fluid/potion_still"));
    }
}
