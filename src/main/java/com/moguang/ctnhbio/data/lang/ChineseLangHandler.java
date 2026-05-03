package com.moguang.ctnhbio.data.lang;

import net.minecraftforge.common.data.LanguageProvider;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityProperties;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperators;
import com.moguang.ctnhbio.data.lang.utils.EntityPropertyLangUtil;
import com.moguang.ctnhbio.registry.*;
import org.jetbrains.annotations.NotNull;
import tech.vixhentx.mcmod.ctnhlib.registrate.lang.RegistrateCNLangProvider;

import java.lang.reflect.Field;
import java.util.Map;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class ChineseLangHandler {

    public static void init(RegistrateCNLangProvider provider) {
        provider.add(CBMachines.BRAIN_IN_A_VAT[HV].getBlock(), "§6HV 缸中之脑§r");
        provider.add(CBMachines.BRAIN_IN_A_VAT[EV].getBlock(), "§5EV 缸中之脑§r");
        provider.add(CBMachines.BRAIN_IN_A_VAT[IV].getBlock(), "§9IV 缸中之脑§r");
        provider.add(CBMachines.BRAIN_IN_A_VAT[LuV].getBlock(), "§dLuV 缸中之脑§r");

        provider.add("ctnhbio.great_flesh.info.0", "§5喂食原初温床，并给予其治疗药水，温床会孵化出肉块，挑选出有潜质的那些（不饥饿的那些）。");
        provider.add("ctnhbio.great_flesh.info.1", "§5使用有机试管把原初血清装载入活体注射器，向它们注射，");
        provider.add("ctnhbio.great_flesh.info.2", "§5他们将会向你展示最原初、最纯粹的生命形态");

        provider.add("item.gtceu.tool.boning_knife", "%s剔骨刀");
        provider.add("config.jade.plugin_gtceu.living_machine_status_provider", "[CTNH]活体机器属性");
        provider.add("ctnhbio.living_machine", "living machine");

        // replace(provider, "fluid_type.biofactory.nutrients_fluid", "营养液");
        provider.add("fluid_type.biofactory.nutrients_fluid", "营养液");

        provider.add("emi.category.ctnhbio.despoil_loot", "血肉掠夺");

        provider.add("ctnhbio.nutrient_bar.info", "营养:");
        provider.add("recipe.capability.nutrient.name", "营养");
        provider.add("jade.nutrient.info", "营养值：%s");

        // provider.add("recipe.condition.effect.tooltip", "药水效果：%s");
        provider.add("ctnhbio.recipe.nutrient_consume", "营养消耗：%d");
        provider.add("ctnhbio.recipe.nutrient_generate", "营养获取：%d");
        provider.add("ctnhbio.jade.nutrient_stored", "%s / %s u");

        provider.add("jei.ctnhbio.mob_crushing", "生物粉碎");
        provider.add("jei.ctnhbio.tooltip.chance", "概率：");
        provider.add("jei.ctnhbio.tooltip.amount_range", "数量：%d-%d");

        // provider.add("ctnhbio.fluid_pipe.cannot_handle_organic", "§4生物活性流体可能失活！");

        provider.add("recipe.capability.entity.name", "实体");

        provider.add("ctnhbio.copyright.info", "由CTNHBio添加");

        // Entity Properties
        EntityPropertyLangUtil epu = new EntityPropertyLangUtil(provider, "实体输入", "实体输出", "接受实体: %s", "要求:");
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
            // Field upsideDownField = RegistrateLangProvider.class.getDeclaredField("upsideDown");
            // upsideDownField.setAccessible(true);
            // // noinspection unchecked
            // map = (Map<String, String>) field.get(upsideDownField.get(provider));
            //
            // Method toUpsideDown = RegistrateLangProvider.class.getDeclaredMethod("toUpsideDown",
            // String.class);
            // toUpsideDown.setAccessible(true);
            //
            // map.put(key, (String) toUpsideDown.invoke(provider, value));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Error replacing entry in datagen.", e);
        }
    }
}
