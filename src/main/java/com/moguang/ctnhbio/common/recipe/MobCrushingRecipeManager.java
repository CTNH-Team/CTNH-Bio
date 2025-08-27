package com.moguang.ctnhbio.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MobCrushingRecipeManager {
    private static final List<MobCrushingRecipe> RECIPES = new ArrayList<>();

    public static List<MobCrushingRecipe> getAllRecipes() {
        return Collections.unmodifiableList(RECIPES);
    }

    // 添加配方（从 JSON 或代码注册）
    public static void addRecipe(MobCrushingRecipe recipe) {
        RECIPES.add(recipe);
    }

    // 查找匹配的配方
    public static Optional<MobCrushingRecipe> findRecipe(Entity entity) {
        return RECIPES.stream()
                .filter(recipe -> recipe.matches(entity))
                .findFirst();
    }

    // 从 JSON 加载所有配方
    public static void loadFromJson(JsonArray jsonArray) {
        for (JsonElement element : jsonArray) {
            JsonObject json = element.getAsJsonObject();
            EntityType<?> entityType = EntityType.byString(json.get("entity").getAsString())
                    .orElseThrow(() -> new JsonSyntaxException("未知生物类型: " + json.get("entity")));

            ItemStack result = new ItemStack(
                    ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(json.get("item").getAsString())),
                    1 // 基础数量为1，实际数量由 rollResult() 决定
            );

            float chance = json.has("chance") ? json.get("chance").getAsFloat() : 1.0f;
            int minAmount = json.has("min_amount") ? json.get("min_amount").getAsInt() : 1;
            int maxAmount = json.has("max_amount") ? json.get("max_amount").getAsInt() : minAmount;

            addRecipe(new MobCrushingRecipe(entityType, result, chance, minAmount, maxAmount));
        }
    }
}
