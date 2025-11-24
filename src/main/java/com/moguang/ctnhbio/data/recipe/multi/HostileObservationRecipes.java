package com.moguang.ctnhbio.data.recipe.multi;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import dev.latvian.mods.kubejs.util.Tags;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;


import java.util.function.Consumer;

import static com.moguang.ctnhbio.api.recipe.ingredient.model.ModelIngredient.getModelId;
import static com.moguang.ctnhbio.registry.CBRecipeTypes.HOSTILE_OBSERVATION;
import static dev.shadowsoffire.hostilenetworks.Hostile.Items.PREDICTION_MATRIX;
import static net.minecraft.world.item.Items.*;
import static com.gregtechceu.gtceu.api.GTValues.*;

public class HostileObservationRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        addEntityTypeWithItem(EntityType.COW,WHEAT, provider);
        addEntityTypeWithItem(EntityType.IRON_GOLEM, IRON_INGOT, provider);
        addEntityTypeWithItem(EntityType.SLIME, SLIME_BALL, provider);
        addEntityTypeWithItem(EntityType.GUARDIAN, PRISMARINE_SHARD, provider);
        addEntityTypeWithItem(EntityType.SPIDER, COBWEB, provider);
        addEntityTypeWithItem(EntityType.WITHER_SKELETON, SOUL_SAND, provider);
        addEntityTypeWithItem(EntityType.SHULKER, CHORUS_FRUIT, provider);
        addEntityTypeWithItem(EntityType.ELDER_GUARDIAN, PRISMARINE_CRYSTALS, provider);
        addEntityTypeWithItem(EntityType.GLOW_SQUID, SEAGRASS, provider);
        addEntityTypeWithItem(EntityType.WARDEN, ECHO_SHARD, provider);
    }
    static ResourceLocation getRecipeId(EntityType<?> type, ModelTier tier) {
        ResourceLocation t = ForgeRegistries.ENTITY_TYPES.getKey(type);
        return CTNHBio.id("%s/%s/%s".formatted(t.getNamespace(),t.getPath(), tier.name));
    }
    public static void addEntityTypeWithItem(EntityType<?> type, ItemLike item, Consumer<FinishedRecipe> provider){


        CBRecipeBuilder.of(getRecipeId(type, ModelTier.BASIC), HOSTILE_OBSERVATION)
                .inputEntity(type, 1, 0)
                .inputModel(ModelIngredient.of(ModelTier.FAULTY,type))
                .outputModel(ModelIngredient.of(ModelTier.BASIC,type), 3000)
                .notConsumable(Ingredient.of(Tags.item(ResourceLocation.parse("minecraft:swords"))))
                .inputItems(item)
                .EUt(VA[EV])
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(getRecipeId(type, ModelTier.ADVANCED), HOSTILE_OBSERVATION)
                .inputEntity(type, 1, 0)
                .inputModel(ModelIngredient.of(ModelTier.BASIC,type))
                .outputModel(ModelIngredient.of(ModelTier.ADVANCED,type), 1000)
                .inputItems(PREDICTION_MATRIX)
                .inputItems(item)
                .EUt(VA[IV])
                .duration(200)
                .save(provider);

        CBRecipeBuilder.of(getRecipeId(type, ModelTier.SUPERIOR), HOSTILE_OBSERVATION)
                .inputEntity(type, 1, 0)
                .inputModel(ModelIngredient.of(ModelTier.ADVANCED,type))
                .outputModel(ModelIngredient.of(ModelTier.SUPERIOR,type), 500)
                .inputItems(GTItems.TOOL_DATA_STICK)
                .inputItems(item)
                .EUt(VA[LuV])
                .CWUt(8)
                .duration(200)
                .save(provider);
    }
}
