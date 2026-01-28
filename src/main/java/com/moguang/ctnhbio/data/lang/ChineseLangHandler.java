package com.moguang.ctnhbio.data.lang;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityProperties;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperators;
import com.moguang.ctnhbio.data.lang.utils.EntityPropertyLangUtil;
import com.moguang.ctnhbio.registry.*;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.registrate.lang.RegistrateCNLangProvider;

import java.lang.reflect.Field;
import java.util.Map;

import static com.gregtechceu.gtceu.api.GTValues.*;


public class ChineseLangHandler {
    public static void init(RegistrateCNLangProvider provider){
//        provider.addItem(CBItems.SYNET_CORE, "脉络核心");
//        provider.addItem(CBItems.META_CORE, "拓扑核心");
//        provider.addItem(CBItems.NOVA_CORE, "灵蜕核心");
//        provider.addItem(CBItems.OMNI_CORE, "终观核心");

        //provider.addItem(CBItems.WETWARE_CAPACITOR, "湿件电容");
//        provider.addItem(CBItems.WETWARE_DIODE, "湿件二极管");
//        provider.addItem(CBItems.WETWARE_INDUCTOR, "湿件电感");
//        provider.addItem(CBItems.WETWARE_RESISTOR, "湿件电阻");
//        provider.addItem(CBItems.WETWARE_TRANSISTOR, "湿件晶体管");
//        provider.addItem(CBItems.WETWARE_CIRCUIT_BOARD, "湿件电路基板");
//        provider.addItem(CBItems.WETWARE_PRINTED_CIRCUIT_BOARD, "湿件印刷电路基板");
//        provider.addItem(CBItems.ORGANIC_VIAL, "有机%s试管");
//        provider.addItem(CBItems.ORGANIC_BEAKER, "有机%s烧杯");

        //provider.addBlock(CBBlocks.FLESH_CASING, "血肉机械方块");
//        provider.addBlock(CBBlocks.ORNATE_FLESH_CASING, "装饰性机械方块");
//        provider.addBlock(CBBlocks.PRIMAL_FLESH_CASING, "原初机械方块");
//        provider.addBlock(CBBlocks.ACID_FLESH_CASING, "酸液机械方块");
//        provider.addBlock(CBBlocks.BIO_ACID_CASING, "生物酸机械方块");
//        provider.addBlock(CBBlocks.SYNAPTIC_CASING, "神经突触机械方块");
//
//        provider.addBlock(CBBlocks.CONSCIOUSNESS_LINKER, "意识链接器");
//        provider.addBlock(CBBlocks.NEURAL_NETWORK_CASING, "神经网络外壳");
//        provider.addBlock(CBBlocks.NEURAL_COOLING_CONDUIT, "神经冷却导管");
//        provider.addBlock(CBBlocks.CONSCIOUSNESS_CONTROLLER, "意识控制器");
//        provider.addBlock(CBBlocks.CONSCIOUSNESS_SENSOR_GLASS, "意识传感玻璃");
//
//        provider.addBlock(CBBlocks.IMPERMEABLE_MEMBRANE, "不渗透膜");

//        provider.add(CBMachines.BIOELECTRIC_FORGE[LV].getBlock(), "基础生物电炉");
//        provider.add(CBMachines.BIOELECTRIC_FORGE[MV].getBlock(), "§b进阶生物电炉§r");
//        provider.add(CBMachines.BIOELECTRIC_FORGE[HV].getBlock(), "§6进阶生物电炉 II§r");
//        provider.add(CBMachines.BIOELECTRIC_FORGE[EV].getBlock(), "§5进阶生物电炉 III§r");
//        provider.add(CBMachines.BIOELECTRIC_FORGE[IV].getBlock(), "§9精英生物电炉§r");

//        provider.add(CBMachines.DECOMPOSER[LV].getBlock(), "基础电力分解者");
//        provider.add(CBMachines.DECOMPOSER[MV].getBlock(), "§b进阶电力分解者§r");
//        provider.add(CBMachines.DECOMPOSER[HV].getBlock(), "§6进阶电力分解者 II§r");
//        provider.add(CBMachines.DECOMPOSER[EV].getBlock(), "§5进阶电力分解者 III§r");
//        provider.add(CBMachines.DECOMPOSER[IV].getBlock(), "§9精英电力分解者§r");
//
//        provider.add(CBMachines.DIGESTER[LV].getBlock(), "基础电力消化器");
//        provider.add(CBMachines.DIGESTER[MV].getBlock(), "§b进阶电力消化器§r");
//        provider.add(CBMachines.DIGESTER[HV].getBlock(), "§6进阶电力消化器 II§r");
//        provider.add(CBMachines.DIGESTER[EV].getBlock(), "§5进阶电力消化器 III§r");
//        provider.add(CBMachines.DIGESTER[IV].getBlock(), "§9精英电力消化器§r");
//
//        provider.add(CBMachines.BIOREACTOR[LV].getBlock(), "基础生物反应腔");
//        provider.add(CBMachines.BIOREACTOR[MV].getBlock(), "§b进阶生物反应腔§r");
//        provider.add(CBMachines.BIOREACTOR[HV].getBlock(), "§6进阶生物反应腔 II§r");
//        provider.add(CBMachines.BIOREACTOR[EV].getBlock(), "§5进阶生物反应腔 III§r");
//        provider.add(CBMachines.BIOREACTOR[IV].getBlock(), "§9精英生物反应腔§r");

        provider.add(CBMachines.BRAIN_IN_A_VAT[HV].getBlock(), "§6HV 缸中之脑§r");
        provider.add(CBMachines.BRAIN_IN_A_VAT[EV].getBlock(), "§5EV 缸中之脑§r");
        provider.add(CBMachines.BRAIN_IN_A_VAT[IV].getBlock(), "§9IV 缸中之脑§r");
        provider.add(CBMachines.BRAIN_IN_A_VAT[LuV].getBlock(), "§dLuV 缸中之脑§r");

        //provider.add("ctnhbio.machine.brain_in_a_vat.tooltip.1", "§3自动化思考");
        //provider.add("ctnhbio.machine.brain_in_a_vat.tooltip.2", "§r电量和营养充足时,提供%d算力");
        //provider.add("ctnhbio.machine.brain_in_a_vat.tooltip.3", "§r超频可提供双倍算力，但会对大脑造成不可逆损伤");

//        provider.add("ctnhbio.machine.hv_brain_in_a_vat.tooltip.0", "它觉得自己是一名出色的格雷员工");
//        provider.add("ctnhbio.machine.ev_brain_in_a_vat.tooltip.0", "它正在优化铂系金属处理产线");
//        provider.add("ctnhbio.machine.iv_brain_in_a_vat.tooltip.0", "它喜欢熬夜玩CTNH，这样不太好");
//        provider.add("ctnhbio.machine.luv_brain_in_a_vat.tooltip.0", "它又开始自我怀疑了，重启一下吧");

//        provider.add(CBMultiblocks.GREAT_FLESH.getBlock(), "巨型肉块");
//        provider.add(CBMultiblocks.COGNI_ASSEMBLER.getBlock(), "意识装配机");
//        provider.add(CBMultiblocks.CIRCULATORY_SYSTEM.getBlock(), "循环系统");
//        provider.add(CBMultiblocks.WEATHERER.getBlock(), "风化器");
//        provider.add(CBMultiblocks.HOSTILE_OBSERVER.getBlock(), "敌意观测站");

        provider.add("ctnhbio.great_flesh.info.0", "§5喂食原初温床，并给予其治疗药水，温床会孵化出肉块，挑选出有潜质的那些（不饥饿的那些）。");
        provider.add("ctnhbio.great_flesh.info.1", "§5使用有机试管把原初血清装载入活体注射器，向它们注射，");
        provider.add("ctnhbio.great_flesh.info.2", "§5他们将会向你展示最原初、最纯粹的生命形态");

        provider.add("item.gtceu.tool.boning_knife", "%s剔骨刀");
        provider.add("config.jade.plugin_gtceu.living_machine_status_provider", "[CTNH]活体机器属性");
        provider.add("ctnhbio.living_machine", "living machine");

        //replace(provider, "fluid_type.biofactory.nutrients_fluid", "营养液");
        provider.add("fluid_type.biofactory.nutrients_fluid", "营养液");

        provider.add("emi.category.ctnhbio.despoil_loot", "血肉掠夺");

        //provider.add("gtceu.bioelectric_forge", "生物电炉");
//        provider.add("gtceu.decomposer", "电力分解");
//        provider.add("gtceu.digest", "电力消化");
//        provider.add("gtceu.ctnhbio_reactor", "生物反应");
//        provider.add("gtceu.basic_living", "摄入营养");
//        provider.add("gtceu.great_flesh", "巨型肉块-分化");
        //provider.add(CBRecipeTypes.CONSCIOUSNESS_ASSEMBLY.registryName.toLanguageKey(), "意识装配");

        provider.add("ctnhbio.nutrient_bar.info", "营养:");
        provider.add("recipe.capability.nutrient.name", "营养");
        provider.add("jade.nutrient.info", "营养值：%s");

        //provider.add("recipe.condition.effect.tooltip", "药水效果：%s");
        provider.add("ctnhbio.recipe.nutrient_consume", "营养消耗：%d");
        provider.add("ctnhbio.recipe.nutrient_generate", "营养获取：%d");
        provider.add("ctnhbio.jade.nutrient_stored", "%s / %s u");

        provider.add("jei.ctnhbio.mob_crushing", "生物粉碎");
        provider.add("jei.ctnhbio.tooltip.chance", "概率：");
        provider.add("jei.ctnhbio.tooltip.amount_range", "数量：%d-%d");

        provider.add("ctnhbio.fluid_pipe.cannot_handle_organic", "§4生物活性流体可能失活！");

        provider.add("ctnhbio.copyright.info", "由CTNHBio添加");

        //Entity Properties
        EntityPropertyLangUtil epu = new EntityPropertyLangUtil(provider,"实体输入","实体输出","接受实体: %s","要求:");
        epu.add(EntityProperties.FALL_DISTANCE, "落地距离");
        epu.add(EntityProperties.REMAINING_FIRE_TICKS, "剩余火焰");
        epu.add(EntityProperties.AIR_SUPPLY, "空气供应");
        epu.add(EntityProperties.ON_GROUND, "是否在地上");
        epu.add(EntityProperties.INVULNERABLE, "是否无敌");
        epu.add(EntityProperties.PORTAL_COOLDOWN, "传送门冷却时间");
        epu.add(EntityProperties.CUSTOM_NAME, "自定义名称");
        epu.add(EntityProperties.CUSTOM_NAME_VISIBLE, "自定义名称可见性");
        epu.add(EntityProperties.SILENT, "是否静默");
        epu.add(EntityProperties.NO_GRAVITY, "是否无重力");
        epu.add(EntityProperties.GLOWING, "是否闪烁");
        epu.add(EntityProperties.TICKS_FROZEN, "冻结时间");
        epu.add(EntityProperties.HAS_VISUAL_FIRE, "是否有可视火焰");
        epu.add(EntityProperties.CAN_UPDATE, "是否可更新");
        epu.add(EntityProperties.HEALTH, "生命值");
        epu.add(EntityProperties.HURT_TIME, "受伤时间");
        epu.add(EntityProperties.HURT_BY_TIMESTAMP, "受伤时间戳");
        epu.add(EntityProperties.DEATH_TIME, "死亡时间");
        epu.add(EntityProperties.ABSORPTION_AMOUNT, "吸收量");
        epu.add(EntityProperties.FALL_FLYING, "是否在飞行中");

        epu.add(PropertyOperators.EQUAL, "等于");
        epu.add(PropertyOperators.GREATER, "大于");
        epu.add(PropertyOperators.LESSER, "小于");
        epu.add(PropertyOperators.CONTAIN, "包含");

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
