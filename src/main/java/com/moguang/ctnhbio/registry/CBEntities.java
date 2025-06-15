package com.moguang.ctnhbio.registry;

import com.moguang.ctnhbio.common.entity.BasicLivingMachineEntity;
import com.tterrag.registrate.util.entry.EntityEntry;
import net.minecraft.world.entity.MobCategory;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public class CBEntities {
    public static EntityEntry<BasicLivingMachineEntity> BASIC_LIVING_MACHINE_ENTITY = REGISTRATE
            .entity("living_machine", BasicLivingMachineEntity::new, MobCategory.MISC)
            .properties(props -> props.sized(0.1F, 0.1F))
            .attributes(BasicLivingMachineEntity::createAttributes)
            .lang("living machine")
            .register();

    public static void init() {}
}
