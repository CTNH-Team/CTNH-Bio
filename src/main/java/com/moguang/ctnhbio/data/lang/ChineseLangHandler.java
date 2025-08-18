package com.moguang.ctnhbio.data.lang;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.moguang.ctnhbio.machine.multiblock.MultiblocksA;
import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMachines;
import com.moguang.ctnhbio.registry.CBMaterials;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static com.gregtechceu.gtceu.api.GTValues.*;


public class ChineseLangHandler {
    public static void init(RegistrateCNLangProvider provider){
        provider.addItem(CBItems.SYNET_CORE, "脉络核心");
        provider.addItem(CBItems.META_CORE, "拓扑核心");
        provider.addItem(CBItems.NOVA_CORE, "灵蜕核心");
        provider.addItem(CBItems.OMNI_CORE, "终观核心");
        provider.addItem(CBItems.ADVANCED_RAM_WAFER, "进阶RAM晶圆");
        provider.addItem(CBItems.ADVANCED_RAM_CHIP, "进阶RAM芯片");

        provider.addItem(CBItems.WETWARE_CAPACITOR, "湿件电容");
        provider.addItem(CBItems.WETWARE_DIODE, "湿件二极管");
        provider.addItem(CBItems.WETWARE_INDUCTOR, "湿件电感");
        provider.addItem(CBItems.WETWARE_RESISTOR, "湿件电阻");
        provider.addItem(CBItems.WETWARE_TRANSISTOR, "湿件晶体管");
        provider.addItem(CBItems.WETWARE_CIRCUIT_BOARD, "湿件电路基板");
        provider.addItem(CBItems.WETWARE_PRINTED_CIRCUIT_BOARD, "湿件印刷电路基板");
        provider.addItem(CBItems.ORGANIC_VIAL, "有机%s试管");
        provider.addItem(CBItems.ORGANIC_BEAKER, "有机%s烧杯");

        provider.addBlock(CBBlocks.FLESH_CASING, "血肉机械方块");
        provider.addBlock(CBBlocks.ORNATE_FLESH_CASING, "装饰性机械方块");
        provider.addBlock(CBBlocks.PRIMAL_FLESH_CASING, "原初机械方块");
        provider.addBlock(CBBlocks.ACID_FLESH_CASING, "酸液机械方块");
        provider.addBlock(CBBlocks.BIO_ACID_CASING, "生物酸机械方块");

        provider.addBlock(CBBlocks.IMPERMEABLE_MEMBRANE, "不渗透膜");

        provider.add(CBMachines.BIOELECTRIC_FORGE[LV].getBlock(), "基础生物电炉");
        provider.add(CBMachines.BIOELECTRIC_FORGE[MV].getBlock(), "§b进阶生物电炉§r");
        provider.add(CBMachines.BIOELECTRIC_FORGE[HV].getBlock(), "§6进阶生物电炉 II§r");
        provider.add(CBMachines.BIOELECTRIC_FORGE[EV].getBlock(), "§5进阶生物电炉 III§r");
        provider.add(CBMachines.BIOELECTRIC_FORGE[IV].getBlock(), "§9精英生物电炉§r");

        provider.add(CBMachines.DECOMPOSER[LV].getBlock(), "基础电力分解者");
        provider.add(CBMachines.DECOMPOSER[MV].getBlock(), "§b进阶电力分解者§r");
        provider.add(CBMachines.DECOMPOSER[HV].getBlock(), "§6进阶电力分解者 II§r");
        provider.add(CBMachines.DECOMPOSER[EV].getBlock(), "§5进阶电力分解者 III§r");
        provider.add(CBMachines.DECOMPOSER[IV].getBlock(), "§9精英电力分解者§r");

        provider.add(CBMachines.DIGESTER[LV].getBlock(), "基础电力消化器");
        provider.add(CBMachines.DIGESTER[MV].getBlock(), "§b进阶电力消化器§r");
        provider.add(CBMachines.DIGESTER[HV].getBlock(), "§6进阶电力消化器 II§r");
        provider.add(CBMachines.DIGESTER[EV].getBlock(), "§5进阶电力消化器 III§r");
        provider.add(CBMachines.DIGESTER[IV].getBlock(), "§9精英电力消化器§r");

        provider.add(CBMachines.BIOREACTOR[LV].getBlock(), "基础生物反应腔");
        provider.add(CBMachines.BIOREACTOR[MV].getBlock(), "§b进阶生物反应腔§r");
        provider.add(CBMachines.BIOREACTOR[HV].getBlock(), "§6进阶生物反应腔 II§r");
        provider.add(CBMachines.BIOREACTOR[EV].getBlock(), "§5进阶生物反应腔 III§r");
        provider.add(CBMachines.BIOREACTOR[IV].getBlock(), "§9精英生物反应腔§r");

        provider.add(CBMachines.BRAIN_IN_A_VAT[HV].getBlock(), "§6HV 缸中之脑§r");
        provider.add(CBMachines.BRAIN_IN_A_VAT[EV].getBlock(), "§5EV 缸中之脑§r");
        provider.add(CBMachines.BRAIN_IN_A_VAT[IV].getBlock(), "§9IV 缸中之脑§r");
        provider.add(CBMachines.BRAIN_IN_A_VAT[LuV].getBlock(), "§dLuV 缸中之脑§r");

        provider.add("ctnhbio.machine.hv_brain_in_a_vat.tooltip.0", "它觉得自己是一名出色的格雷员工");
        provider.add("ctnhbio.machine.ev_brain_in_a_vat.tooltip.0", "它正在优化铂系金属处理产线");
        provider.add("ctnhbio.machine.iv_brain_in_a_vat.tooltip.0", "它喜欢熬夜玩CTNH，这样不太好");
        provider.add("ctnhbio.machine.luv_brain_in_a_vat.tooltip.0", "它又开始自我怀疑了，重启一下吧");

        provider.add(MultiblocksA.GREAT_FLESH.getBlock(), "巨型肉块");
        provider.add(MultiblocksA.COGNI_ASSEMBLER.getBlock(), "意识装配机");
        provider.add(MultiblocksA.CIRCULATORY_SYSTEM.getBlock(), "循环系统");
        provider.add(MultiblocksA.WEATHERER.getBlock(), "风化器");

        provider.add("item.gtceu.tool.boning_knife", "%s剔骨刀");
        provider.add("config.jade.plugin_gtceu.living_machine_status_provider", "[CTNH]活体机器属性");
        provider.add("ctnhbio.living_machine", "living machine");


        provider.add("emi.category.ctnhbio.despoil_loot", "血肉掠夺");
        provider.add("jei.category.gtceu.bioelectric_forge", "生物电炉");
        provider.add("jei.category.gtceu.decomposer", "电力分解");
        provider.add("jei.category.gtceu.digest", "电力消化");
        provider.add("jei.category.gtceu.ctnhbio_reactor", "生物反应");
        provider.add("jei.category.gtceu.basic_living", "摄入营养");
        provider.add("jei.category.gtceu.great_flesh", "巨型肉块-分化");


        provider.add("ctnhbio.nutrient_bar.info", "营养:");
        provider.add("recipe.capability.nutrient.name", "营养");
        provider.add("jade.nutrient.info", "营养值：");

        provider.add("recipe.condition.effect.tooltip", "药水效果：%s");
        provider.add("ctnhbio.recipe.nutrient", "营养消耗：%d");
        provider.add("ctnhbio.jade.nutrient_stored", "%s / %s u");

        provider.add("jei.ctnhbio.mob_crushing", "生物粉碎");
        provider.add("jei.ctnhbio.tooltip.chance", "概率：");
        provider.add("jei.ctnhbio.tooltip.amount_range", "数量：%d-%d");

        provider.add("ctnhbio.mv_machine.tooltip", "MV生物芯片");
        provider.add("ctnhbio.hv_machine.tooltip", "HV生物芯片");
        provider.add("ctnhbio.ev_machine.tooltip", "EV生物芯片");
        provider.add("ctnhbio.iv_machine.tooltip", "IV生物芯片");
        provider.add("ctnhbio.advanced_ram_wafer.tooltip", "更好的内存原料");
        provider.add("ctnhbio.advanced_ram_chip.tooltip", "更好的随机存取存储器");

        provider.add("ctnhbio.synet_core.tooltip", "精密如血管回路的金属骨架，每一道凹槽都在规律脉动，仿佛在输送某种不可见的生命能量。");
        provider.add("ctnhbio.meta_core.tooltip", "不断自我折叠的几何结构，表面浮现出违反欧几里得定律的棱角，在静止中持续完成着不可能的空间变换。");
        provider.add("ctnhbio.nova_core.tooltip", "半透明外壳下悬浮着无数晶体碎片，它们持续剥落又再生，每次破碎都折射出不同的光谱。");
        provider.add("ctnhbio.omni_core.tooltip", "光滑如镜面的球体内部，有星云状物质在缓慢旋转，凝视越久越能看见其中浮现出观测者自身的轮廓。");

        provider.add("ctnhbio.fluid.type_organic.tooltip", "§a生物活性流体");
        provider.add("ctnhbio.fluid_pipe.can_handle_organic", "§a可传输生物活性流体");
        provider.add("ctnhbio.fluid_pipe.cannot_handle_organic", "§4生物活性流体可能失活！");

        provider.add("material.ctnhbio.wither_slime", "凋灵软泥");
        provider.add("material.ctnhbio.endocrine_hormone", "内分泌激素");
        provider.add("material.ctnhbio.toxin_extract", "毒素提取物");
        provider.add("material.ctnhbio.bile", "胆汁");
        provider.add("material.ctnhbio.mutagenic_secretion", "激变分泌液");
        provider.add("material.ctnhbio.regenerate_fluid", "再生粘液");
        provider.add("material.ctnhbio.organic_compound", "有机化合物");
        provider.add("material.ctnhbio.unstable_compound", "不稳定化合物");
        provider.add("material.ctnhbio.genetic_compound", "遗传化合物");
        provider.add("material.ctnhbio.heterogeneous_compound", "异质化合物");
        provider.add("material.ctnhbio.healing_compound", "治愈化合物");
        provider.add("material.ctnhbio.decay_essence", "衰败原液");
        provider.add("material.ctnhbio.rejuvenation_serum", "活力血清");
        provider.add("material.ctnhbio.ageing_serum", "成长血清");
        provider.add("material.ctnhbio.enlargement_serum", "巨化血清");
        provider.add("material.ctnhbio.shrinking_serum", "缩小血清");
        provider.add("material.ctnhbio.breeding_stimulant", "配种兴奋剂");
        provider.add("material.ctnhbio.absorption_boost", "伤痛反应剂");
        provider.add("material.ctnhbio.cleansing_serum", "净化血清");
        provider.add("material.ctnhbio.frenzy_serum", "狂化血清");
        provider.add("material.ctnhbio.pyrrole", "吡咯");
        provider.add("material.ctnhbio.polypyrrole", "聚吡咯");
        provider.add("material.ctnhbio.blue_titanium_alloy", "蓝钛");
        provider.add("material.ctnhbio.fentons_reagent", "芬顿试剂");
        provider.add("material.ctnhbio.bio_flexible", "生物柔性材质");
        provider.add("material.ctnhbio.weird_pixel_dust", "营养");
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
//            Field upsideDownField = RegistrateLangProvider.class.getDeclaredField("upsideDown");
//            upsideDownField.setAccessible(true);
//            // noinspection unchecked
//            map = (Map<String, String>) field.get(upsideDownField.get(provider));
//
//            Method toUpsideDown = RegistrateLangProvider.class.getDeclaredMethod("toUpsideDown",
//                    String.class);
//            toUpsideDown.setAccessible(true);
//
//            map.put(key, (String) toUpsideDown.invoke(provider, value));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error replacing entry in datagen.", e);
        }
    }
}
