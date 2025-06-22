package com.moguang.ctnhbio.api.item.tool;

import com.github.elenterius.biomancy.init.ModEnchantments;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.gregtechceu.gtceu.api.item.tool.GTSwordItem;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.item.tool.IGTToolDefinition;
import com.gregtechceu.gtceu.api.sound.SoundEntry;
import com.gregtechceu.gtceu.common.data.GTDamageTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class CBToolType {
    public static final GTToolType BONING_KNIFE = GTToolType.builder("boning_knife")
            .toolTag(TagUtil.createItemTag("tools/boning_knives", false))
            .harvestTag(TagUtil.createBlockTag("mineable/sword", false))
            .toolStats(b -> b.attacking()
                    .attackDamage(4.0F)
                    .attackSpeed(-2.2F)
                    //.defaultEnchantment(ModEnchantments.DESPOIL.get(), 3)
            )
            .constructor(GTSwordItem::create)
            .toolClassNames("sword") // 可继承自 SWORD 类型
            .toolClasses(GTToolType.SWORD)
            .materialAmount(2 * GTValues.M)
            .build();
}
