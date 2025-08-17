package com.moguang.ctnhbio.registry;

import com.github.elenterius.biomancy.init.ModEnchantments;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.MaterialEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.item.tool.ToolHelper;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.moguang.ctnhbio.data.recipe.*;
import com.moguang.ctnhbio.data.recipe.living.*;
import com.moguang.ctnhbio.data.recipe.multi.GreatFleshRecipes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.GENERATE_PLATE;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plate;


public class CBRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        DecomposerRecipes.init(provider);
        BasicLivingRecipes.init(provider);
        GreatFleshRecipes.init(provider);
        BioelectrlcForgeRecipes.init(provider);
        BioReactorRecipes.init(provider);
        DigesterRecipes.init(provider);
        CommonRecipes.init(provider);
        //BiomancyRecipes.init(provider);
        VanillaRecipeProvider.init(provider);
        recipeAddition(provider);
    }

    public static void recipeAddition(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            if (material.hasFlag(MaterialFlags.NO_UNIFICATION)
                    || !material.hasFlag(GENERATE_PLATE)
                    || !material.shouldGenerateRecipesFor(plate)
                    || material.getProperty(PropertyKey.TOOL) == null
                    || !material.getProperty(PropertyKey.TOOL).hasType(GTToolType.SWORD)

            ) {
                continue;
            }



            ItemStack stick = new ItemStack(Items.STICK);
            MaterialEntry plate = new MaterialEntry(TagPrefix.plate, material);
            MaterialEntry ingot = new MaterialEntry(
                    material.hasProperty(PropertyKey.GEM) ? TagPrefix.gem : TagPrefix.ingot, material);

            addToolRecipe(provider, material, CBToolType.BONING_KNIFE,
                    "PPI", "fSh", "ASA",
                    'P', plate,
                    'I', ingot,
                    'S', stick,
                    'A', new ItemStack(ModItems.LIVING_FLESH.get()));

        }
    }

    public static void addToolRecipe(@NotNull Consumer<FinishedRecipe> provider, @NotNull Material material,
                                     @NotNull GTToolType tool, Object... recipe) {

        ItemStack toolStack = CBMaterialItems.CB_TOOL_ITEMS.get(material, tool).asStack();
        toolStack.enchant(ModEnchantments.DESPOIL.get(), 3);
        if (toolStack.isEmpty()) return;
        VanillaRecipeHelper.addShapedRecipe(provider, String.format("%s_%s", tool.name, material.getName()),
                    toolStack, recipe);

    }
}
