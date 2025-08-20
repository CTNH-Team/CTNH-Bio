package com.moguang.ctnhbio.api.capability.forge;

import com.moguang.ctnhbio.api.capability.IEntityContainer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class CBCapabilities {
    public static final Capability<IEntityContainer> ENTITY_CONTAINER = CapabilityManager.get(new CapabilityToken<>() {});
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(IEntityContainer.class);
    }
}
