package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.common.entity.BasicLivingMachineEntity;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootTable;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBEntities {
    public static EntityEntry<BasicLivingMachineEntity> BASIC_LIVING_MACHINE_ENTITY = REGISTRATE
            .entity("living_machine", BasicLivingMachineEntity::new, MobCategory.CREATURE)
            .properties(props -> props.sized(0.9F, 0.9F))
            .attributes(BasicLivingMachineEntity::createAttributes)
            .loot((lootTables, entityType) -> lootTables.add(entityType, LootTable.lootTable()))
            .lang("Living Machine")
            .register();

    public static void init() {}
}
