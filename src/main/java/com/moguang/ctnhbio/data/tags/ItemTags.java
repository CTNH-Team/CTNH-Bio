package com.moguang.ctnhbio.data.tags;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

import com.moguang.ctnhbio.registry.CBTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;

import java.util.Objects;

public class ItemTags {

    public static void init(RegistrateTagsProvider<Item> provider) {
        create(provider, CBTags.FOOD_TAG, Items.BREAD, Items.APPLE);
    }

    public static void create(RegistrateTagsProvider<Item> provider, TagKey<Item> tagKey, Item... rls) {
        var builder = provider.addTag(tagKey);
        for (Item item : rls) {
            builder.addOptional(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
        }
    }
}
