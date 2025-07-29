package com.moguang.ctnhbio.data.lang;

import com.moguang.ctnhbio.registry.CBItems;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


public class ChineseLangHandler {
    public static void init(RegistrateCNLangProvider provider){
        provider.addItem(CBItems.BIO_CAPACITOR, "生物电容");
        provider.addItem(CBItems.BIO_DIODE, "生物二极管");
        provider.addItem(CBItems.BIO_INDUCTOR, "生物电感");
        provider.addItem(CBItems.BIO_RESISTOR, "生物电阻");
        provider.addItem(CBItems.BIO_TRANSISTOR, "生物晶体管");
        provider.addItem(CBItems.WETWARE_CIRCUIT_BOARD, "湿件电路基板");
        provider.addItem(CBItems.WETWARE_PRINTED_CIRCUIT_BOARD, "湿件印刷电路基板");
        provider.addItem(CBItems.ORGANIC_VIAL, "有机%s试管");
        provider.addItem(CBItems.ORGANIC_BEAKER, "有机%s烧杯");

        provider.add("item.gtceu.tool.boning_knife", "%s剔骨刀");
        provider.add("config.jade.plugin_gtceu.living_machine_status_provider", "[CTNH]活体机器属性");
        provider.add("ctnhbio.living_machine", "living machine");

        provider.add("emi.category.ctnhbio.despoil_loot", "血肉掠夺");
        provider.add("ctnhbio.nutrient_bar.info", "营养槽");
        provider.add("recipe.capability.nutrient.name", "营养");
        provider.add("jade.nutrient.info", "营养值：");

        provider.add("recipe.condition.effect.tooltip", "药水效果：%s");
        provider.add("ctnhbio.recipe.nutrient", "营养消耗：%d");
        provider.add("ctnhbio.jade.nutrient_stored", "%s / %s");

        provider.add("jei.ctnhbio.mob_crushing", "生物粉碎");
        provider.add("jei.ctnhbio.tooltip.chance", "概率：");
        provider.add("jei.ctnhbio.tooltip.amount_range", "数量：%d-%d");


        provider.add("ctnhbio.fluid.type_organic.tooltip", "§a生物活性流体");
        provider.add("ctnhbio.fluid_pipe.can_handle_organic", "§a可传输生物活性流体");
        provider.add("ctnhbio.fluid_pipe.cannot_handle_organic", "§4生物活性流体可能失活！");

        provider.add("material.gtceu.wither_slime", "凋灵软泥");
        provider.add("material.gtceu.endocrine_hormone", "内分泌激素");
        provider.add("material.gtceu.toxin_extract", "毒素提取物");
        provider.add("material.gtceu.bile", "胆汁");
        provider.add("material.gtceu.mutagenic_secretion", "激变分泌液");
        provider.add("material.gtceu.regenerate_fluid", "再生粘液");
        provider.add("material.gtceu.organic_compound", "有机化合物");
        provider.add("material.gtceu.unstable_compound", "不稳定化合物");
        provider.add("material.gtceu.genetic_compound", "遗传化合物");
        provider.add("material.gtceu.heterogeneous_compound", "异质化合物");
        provider.add("material.gtceu.healing_compound", "治愈化合物");
        provider.add("material.gtceu.decay_essence", "衰败原液");
        provider.add("material.gtceu.vitality_serum", "活力血清");
        provider.add("material.gtceu.growth_serum", "成长血清");
        provider.add("material.gtceu.gigantism_serum", "巨化血清");
        provider.add("material.gtceu.shrinking_serum", "缩小血清");
        provider.add("material.gtceu.breeding_stimulant", "配种兴奋剂");
        provider.add("material.gtceu.pain_response_agent", "伤痛反应剂");
        provider.add("material.gtceu.purification_serum", "净化血清");
        provider.add("material.gtceu.berserk_serum", "狂化血清");
    }

    public static void replace(@NotNull RegistrateCNLangProvider provider, @NotNull String key,
                               @NotNull String value) {
        try {
            // the regular lang mappings
            Field field = LanguageProvider.class.getDeclaredField("data");
            field.setAccessible(true);
            // noinspection unchecked
            Map<String, String> map = (Map<String, String>) field.get(provider);
            map.put(key, value);

            // upside-down lang mappings
            Field upsideDownField = RegistrateLangProvider.class.getDeclaredField("upsideDown");
            upsideDownField.setAccessible(true);
            // noinspection unchecked
            map = (Map<String, String>) field.get(upsideDownField.get(provider));

            Method toUpsideDown = RegistrateLangProvider.class.getDeclaredMethod("toUpsideDown",
                    String.class);
            toUpsideDown.setAccessible(true);

            map.put(key, (String) toUpsideDown.invoke(provider, value));
        } catch (NoSuchFieldException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error replacing entry in datagen.", e);
        }
    }
}
