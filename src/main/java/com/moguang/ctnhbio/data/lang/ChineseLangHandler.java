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
        provider.addItem(CBItems.WETWARE_PRINTED_CIRCUIT_BOARD, "蚀刻湿件电路基板");
        provider.add("item.gtceu.tool.boning_knife", "%s剔骨刀");
        provider.add("config.jade.plugin_gtceu.living_machine_status_provider", "[CTNH]活体机器属性");
        provider.add("ctnhbio.living_machine", "living machine");

        provider.add("emi.category.ctnhbio.despoil_loot", "血肉掠夺");
        provider.add("ctnhbio.nutrient_bar.info", "营养槽");
        provider.add("jade.nutrient.info", "营养值：");
        provider.add("recipe.condition.effect.tooltip", "药水效果：%s");
        provider.add("ctnhbio.recipe.nutrient", "营养消耗：%d");
        provider.add("ctnhbio.jade.nutrient_stored", "%s / %s");
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
