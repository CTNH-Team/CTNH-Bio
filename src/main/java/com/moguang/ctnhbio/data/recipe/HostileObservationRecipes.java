package com.moguang.ctnhbio.data.recipe;

import com.moguang.ctnhbio.CTNHBio;
import dev.shadowsoffire.hostilenetworks.Hostile;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.moguang.ctnhbio.registry.CBRecipeTypes.HOSTILE_OBSERVATION;

public class HostileObservationRecipes {
    public static void init(Consumer<FinishedRecipe> provider){
        registerForEntityType(EntityType.COW, provider);
    }
    static ResourceLocation getRecipeId(EntityType<?> type) {
        ResourceLocation t = ForgeRegistries.ENTITY_TYPES.getKey(type);
        return CTNHBio.id("observation_%s_%s".formatted(t.getNamespace(),t.getPath()));
    }
    static ResourceLocation getModelId(EntityType<?> type) {
        ResourceLocation t = ForgeRegistries.ENTITY_TYPES.getKey(type);
        return t.getNamespace().equals("minecraft")?
                ResourceLocation.fromNamespaceAndPath("hostilenetworks",t.getPath()) :
                ResourceLocation.fromNamespaceAndPath("hostilenetworks","%s/%s".formatted(t.getNamespace(),t.getPath()));
    }
    public static void registerForEntityType(EntityType<?> type, Consumer<FinishedRecipe> provider){
        ResourceLocation recipeId = getRecipeId(type);
        ResourceLocation model = getModelId(type);
        DataModelItem item = Hostile.Items.DATA_MODEL.get();

        ItemStack input = new ItemStack(item);
        DataModelItem.setStoredModel(input, model);
        DataModelItem.setData(input, ModelTier.SUPERIOR.data().requiredData());

        ItemStack output = new ItemStack(item);
        DataModelItem.setStoredModel(output, model);
        DataModelItem.setData(output, ModelTier.SELF_AWARE.data().requiredData());

        CBRecipeBuilder.of(recipeId, HOSTILE_OBSERVATION)
                .inputEntity(type)
                .inputItems(input)
                .outputItems(output)
//                .durationIsTotalCWU(true)
                .CWUt(64)
                .duration(ModelTier.SELF_AWARE.data().requiredData() * 64)
                .save(provider);
    }
}
