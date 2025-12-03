package com.moguang.ctnhbio.integration.emi;

import com.mojang.logging.LogUtils;
import com.yanny.ali.Utils;
import com.yanny.ali.configuration.GameplayLootCategory;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@EmiEntrypoint
public class CTNHBioEmiPlugin implements EmiPlugin {
    private static final Logger LOGGER = LogUtils.getLogger();

    public List<GameplayLootCategory> gameplayCategories;

    @Override
    public void register(EmiRegistry registry) {
        //gameplayCategories.add(new GameplayLootCategory(Utils.modLoc("despoil_loot"), Items.COMPASS, false, List.of((Pattern.compile(".*")))));
    }
}
