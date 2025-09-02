package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.client.Renderer.BasicLivingMachineEntityRenderer;

import com.moguang.ctnhbio.machine.braininavat.Brain;
import com.moguang.ctnhbio.machine.braininavat.BrainRenderer;

import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootTable;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBEntities {
    public static EntityEntry<LivingMetaMachineEntity> LIVING_META_MACHINE_ENTITY = REGISTRATE
            .entity("living_machine", LivingMetaMachineEntity::create, MobCategory.CREATURE)
            .properties(props -> props.sized(0.9F, 0.9F))
            .attributes(LivingMetaMachineEntity::createAttributes)
            .loot((lootTables, entityType) -> lootTables.add(entityType, LootTable.lootTable()))
            .renderer(() -> BasicLivingMachineEntityRenderer::new)
            .lang("Living Machine")
            .register();

    public static EntityEntry<Brain> BRAIN_IN_A_VAT_BRAIN = REGISTRATE
            .entity("brain_in_a_vat_brain", Brain::new, MobCategory.CREATURE)
            .properties(props -> props.sized(0.25F, 0.2F))
            .attributes(Brain::createAttributes)
            .loot((lootTables, entityType) -> lootTables.add(entityType, LootTable.lootTable()))
            .renderer(() -> BrainRenderer::new)
            .lang("Brain")
            .register();




    public static void init() {}
}
