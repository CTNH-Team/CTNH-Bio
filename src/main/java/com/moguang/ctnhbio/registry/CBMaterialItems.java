package com.moguang.ctnhbio.registry;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.world.item.Items;

import java.util.List;

import static com.gregtechceu.gtceu.common.data.GTCreativeModeTabs.TOOL;
import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;
import static com.moguang.ctnhbio.registry.CBCreativeModeTabs.ITEM;

public class CBMaterialItems{
    static {
        REGISTRATE.creativeModeTab(() -> CBCreativeModeTabs.ITEM);
    }
    public final static Table<Material, GTToolType, ItemProviderEntry<IGTTool>> CB_TOOL_ITEMS = ArrayTable.create(
            GTCEuAPI.materialManager.getRegisteredMaterials().stream()
                    .filter(mat -> mat.hasProperty(PropertyKey.TOOL))
                    .toList(),
            List.of(CBToolType.BONING_KNIFE));

    public static void generateTools() {
        //System.out.println("CBMaterialItems.generateTools()");
        //REGISTRATE.creativeModeTab(() -> ITEM);
        GTToolType toolType = CBToolType.BONING_KNIFE;
        for (MaterialRegistry registry : GTCEuAPI.materialManager.getRegistries()) {
            for (Material material : registry.getAllMaterials()) {
                if (material.hasProperty(PropertyKey.TOOL)) {
                    var property = material.getProperty(PropertyKey.TOOL);
                    if (property.hasType(GTToolType.SWORD)) {
                        generateTool(material, toolType);
                    }
                }
            }
        }
    }

    public static void generateTool(Material material, GTToolType toolType) {
        var tier = material.getToolTier();
        CB_TOOL_ITEMS.put(material, toolType, (ItemProviderEntry<IGTTool>) (ItemProviderEntry<?>) com.moguang.ctnhbio.CTNHBio.REGISTRATE
                .item(toolType.idFormat.formatted(tier.material.getName()),
                        p -> toolType.constructor.apply(toolType, tier, material,
                                toolType.toolDefinition, p).asItem())
                .properties(p -> p.craftRemainder(Items.AIR))
                .setData(ProviderType.LANG, NonNullBiConsumer.noop())
                .model(NonNullBiConsumer.noop())
                .color(() -> IGTTool::tintColor)
                .register());
    }
}
