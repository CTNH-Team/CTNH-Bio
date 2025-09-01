package com.moguang.ctnhbio.data.recipe.living;

import com.github.elenterius.biomancy.init.ModFluids;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import com.moguang.ctnhbio.registry.CBTags;
import it.unimi.dsi.fastutil.objects.ObjectIntPair;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.gemChipped;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

public class DecomposerRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        registerRawOreDecompositionRecipes(provider);
    }
    private static void registerRawOreDecompositionRecipes(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasProperty(PropertyKey.ORE) &&
                    !ChemicalHelper.get(TagPrefix.rawOre, material).isEmpty()) {
                addRawOreRecipe(provider, material);
            }
        }
    }

    private static void addRawOreRecipe(Consumer<FinishedRecipe> provider, Material material) {
        //洗矿-热离-粉碎
        OreProperty property = material.getProperty(PropertyKey.ORE);
        ItemStack finalDust = ChemicalHelper.get(dust, material);
        if (finalDust.isEmpty()) return;
        CBRecipeBuilder.of(CTNHBio.id("decompose_raw_" + material.getName()),
                        CBRecipeTypes.DECOMPOSER_RECIPES)
                .effect(MobEffects.DAMAGE_BOOST)
                .nutrient(10)
                .inputItems(TagPrefix.crushed, material)
                .inputFluids(new FluidStack(DistilledWater.getFluid(), 1000))
                .outputItems(finalDust)
                .chancedOutput(
                        ChemicalHelper.get(dust, property.getOreByProduct(0, material)),
                        1400,
                        0
                )
                .chancedOutput(
                        ChemicalHelper.get(dust, property.getOreByProduct(1, material)),
                        3300,
                        0
                )
                .chancedOutput(
                        ChemicalHelper.get(dust, property.getOreByProduct(2, material)),
                        1700,
                        0
                )
                .EUt(8000)
                .duration(60)
                .save(provider);
        //洗矿-粉碎-离心
        OreProperty one = material.getProperty(PropertyKey.ORE);
        ItemStack Dust = ChemicalHelper.get(dust, material);
        if (Dust.isEmpty()) return;
        CBRecipeBuilder.of(CTNHBio.id("wash_grind_centrifuge_" + material.getName()),
                        CBRecipeTypes.DECOMPOSER_RECIPES)
                .nutrient(5)
                .inputItems(TagPrefix.crushed, material)
                .inputFluids(new FluidStack(Fluids.WATER, 1500))
                .outputItems(Dust)
                .chancedOutput(
                        ChemicalHelper.get(dust, one.getOreByProduct(0, material)),
                        1400,
                        0
                )
                .chancedOutput(
                        ChemicalHelper.get(dust, one.getOreByProduct(1, material)),
                        1500,
                        0
                )
                .chancedOutput(
                        ChemicalHelper.get(dust, one.getOreByProduct(1, material)),
                        1700,
                        0
                )
                .EUt(24)
                .duration(200)
                .save(provider);
        //酸洗-粉碎-离心
        if (!property.getWashedIn().first().isNull()) {
            Material washingByproduct = property.getOreByProduct(3, material);
            //ObjectIntPair<Material> washedInTuple = property.getWashedIn();
            CBRecipeBuilder.of(CTNHBio.id("acid_grind_centrifuge_" + material.getName()),
                            CBRecipeTypes.DECOMPOSER_RECIPES)
                    //.effect(MobEffects.DAMAGE_BOOST)
                    .nutrient(5)
                    .inputItems(TagPrefix.crushed, material)
                    .inputFluids(FluidIngredient.of(CBTags.ACID_FLUIDS_TAG, 50))
                    .outputItems(ChemicalHelper.get(dust, material))
                    .chancedOutput(
                            ChemicalHelper.get(dust, washingByproduct),
                            7000,
                            0
                    )
                    .chancedOutput(
                            ChemicalHelper.get(dust, property.getOreByProduct(1, material)),
                            1700,
                            0
                    )
                    .chancedOutput(
                            ChemicalHelper.get(dust, property.getOreByProduct(1, material)),
                            1300,
                            0
                    )
                    .EUt(64)
                    .duration(200)
                    .save(provider);
        }
    }
}


