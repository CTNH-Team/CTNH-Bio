package com.moguang.ctnhbio.mixin.ali;

import com.github.elenterius.biomancy.init.ModEnchantments;
import com.gregtechceu.gtceu.api.item.IGTTool;
import com.llamalad7.mixinextras.sugar.Local;
import com.moguang.ctnhbio.api.item.tool.CBToolType;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.yanny.ali.compatibility.EmiCompatibility;
import com.yanny.ali.configuration.LootCategory;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Map;

import static com.github.elenterius.biomancy.init.ModItems.DESPOIL_SICKLE;
import static com.moguang.ctnhbio.registry.CBMaterialItems.CB_TOOL_ITEMS;

@Mixin(value = EmiCompatibility.class, remap = false)
public class EmiCompatibilityMixin {
    @Inject(
            method = "registerData",
            at = @At(value = "INVOKE",
                    target = "Lcom/yanny/ali/compatibility/common/GenericUtils;processData(Lnet/minecraft/client/multiplayer/ClientLevel;Lcom/yanny/ali/manager/AliClientRegistry;Lcom/yanny/ali/configuration/AliConfig;[BLcom/yanny/ali/compatibility/common/QuadConsumer;Lcom/yanny/ali/compatibility/common/QuadConsumer;Lcom/yanny/ali/compatibility/common/TriConsumer;Lcom/yanny/ali/compatibility/common/QuadConsumer;Lcom/yanny/ali/compatibility/common/QuadConsumer;)V"
            )
    )
    private void registerData(EmiRegistry registry, byte[] fullCompressedData, CallbackInfo ci,
                              @Local(name = "gameplayCategories") Map<LootCategory<ResourceLocation>, EmiRecipeCategory> gameplayCategories) {
        var category =gameplayCategories.values().stream().filter(
                c -> c.id.getPath().contains("despoil")
        ).findFirst().orElse(null);



        ItemStack enchantedBook = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.DESPOIL.get(), 1));
        var list = new ArrayList<EmiIngredient>();
        for (ItemProviderEntry<IGTTool> entry : CB_TOOL_ITEMS.column(CBToolType.BONING_KNIFE).values()) {
            if(entry == null ) continue;
            ItemStack stack = new ItemStack(entry.get());
            list.add(EmiIngredient.of(Ingredient.of(stack)));
        }
        if(category != null)
        {
            registry.addWorkstation(category, EmiIngredient.of(Ingredient.of(DESPOIL_SICKLE.get())));
            registry.addWorkstation(category, EmiIngredient.of(Ingredient.of(enchantedBook)));
            registry.addWorkstation(category, EmiIngredient.of(list));
        }
    }
}
