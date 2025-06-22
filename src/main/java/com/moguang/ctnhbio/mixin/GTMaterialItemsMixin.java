package com.moguang.ctnhbio.mixin;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.moguang.ctnhbio.registry.CBMaterialItems.generateTool;

@Mixin(value = GTMaterialItems.class, remap = false)
public class GTMaterialItemsMixin {

    //@Invoker("generateTool")
    //private static void generateTool(Material material, GTToolType toolType, GTRegistrate registrate){}

    @Inject(
            method = "generateTools",
            at = @At("TAIL"),
            remap = false
    )
    private static void generateToolsMixin(CallbackInfo ci){

        GTToolType toolType = CBToolType.BONING_KNIFE;
        for (MaterialRegistry registry : GTCEuAPI.materialManager.getRegistries()) {
            //GTRegistrate registrate = registry.getRegistrate();
            for (Material material : registry.getAllMaterials()) {
                if (material.hasProperty(PropertyKey.TOOL)) {
                    generateTool(material, toolType);
                }
            }
        }
    }

}
