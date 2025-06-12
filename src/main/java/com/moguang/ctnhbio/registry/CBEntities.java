package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.common.entity.BasicMobEntity;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBEntities {
    public static EntityEntry<BasicMobEntity> BASIC_MOB = REGISTRATE
            .entity("basic_mob", BasicMobEntity::new, MobCategory.CREATURE)
            .properties(props -> props.sized(3.0F, 3.0F))
            .attributes(BasicMobEntity::createAttributes)
            .lang("Basic Mob")
            .register();

    public static void init() {}
}
