package com.moguang.ctnhbio.integration.jei;

import com.github.elenterius.biomancy.init.ModEnchantments;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.moguang.ctnhbio.utils.LootTableAccess;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.yanny.ali.compatibility.common.GameplayLootType;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusFactory;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import com.moguang.ctnhbio.CTNHBio;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.github.elenterius.biomancy.init.ModItems.DESPOIL_SICKLE;
import static com.moguang.ctnhbio.registry.CBMaterialItems.CB_TOOL_ITEMS;

@JeiPlugin
public class CTNHBioJeiPlugin implements IModPlugin {

    private static final ResourceLocation ID = new ResourceLocation(CTNHBio.MODID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }


    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime jeiRuntime) {
        IRecipeManager recipeManager = jeiRuntime.getRecipeManager();
        RecipeType<GameplayLootType> aliGameplayType = new RecipeType<>(new ResourceLocation("ctnhbio", "despoil_loot"), GameplayLootType.class);

        //List<GameplayLootType> toHide = new ArrayList<>();

        // 获取所有该类型的配方
        //IFocus<ItemStack> focus = IFocusFactory.

        //System.out.println(jeiRuntime.getIngredientFilter().getFilterText());
        var allRecipes = recipeManager.createRecipeLookup(aliGameplayType).get();

        List<GameplayLootType> toHide = allRecipes
                .filter(recipe -> {
                    ResourceLocation seq = ((LootTableAccess)recipe.entry()).getRandomSequence();
                    if (seq == null) return true;

                    String[] parts = seq.getPath().split("/");
                    if (parts.length < 3) return true;

                    ResourceLocation id = new ResourceLocation(seq.getNamespace(), parts[2]);
                    return !BuiltInRegistries.ENTITY_TYPE.containsKey(id);
                })
                .toList();

        // 隐藏所有无效实体对应的配方
        recipeManager.hideRecipes(aliGameplayType, toHide);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        RecipeType<GameplayLootType> type = new RecipeType<>(new ResourceLocation("ctnhbio", "despoil_loot"), GameplayLootType.class);

        registration.addRecipeCatalyst(new ItemStack(DESPOIL_SICKLE.get()), type);

        ItemStack enchantedBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.DESPOIL.get(), 1));
        registration.addRecipeCatalyst(enchantedBook, type);

        for (ItemProviderEntry<IGTTool> entry : CB_TOOL_ITEMS.column(CBToolType.BONING_KNIFE).values()) {
            ItemStack stack = new ItemStack(entry.get());
            registration.addRecipeCatalyst(stack, type);
        }

    }
}
