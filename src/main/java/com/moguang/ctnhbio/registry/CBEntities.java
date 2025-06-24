package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.client.Renderer.BasicLivingMachineEntityRenderer;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootTable;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBEntities {
    public static EntityEntry<LivingMetaMachineEntity> LIVING_META_MACHINE_ENTITY = REGISTRATE
            .entity("living_machine", LivingMetaMachineEntity::createLivingMetaMachineEntity, MobCategory.CREATURE)
            .properties(props -> props.sized(0.9F, 0.9F))
            .attributes(LivingMetaMachineEntity::createAttributes)
            .loot((lootTables, entityType) -> lootTables.add(entityType, LootTable.lootTable()))
            .renderer(() -> BasicLivingMachineEntityRenderer::new)
            .lang("Living Machine")
            .register();

    public static void init() {}
}
