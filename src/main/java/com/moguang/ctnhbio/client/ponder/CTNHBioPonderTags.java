package com.moguang.ctnhbio.client.ponder;

import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBMultiblocks;
import tech.vixhentx.mcmod.ctnhlib.client.ponder.CTNHPonderTagHelper;

import static com.moguang.ctnhbio.CTNHBio.REGISTRATE;

public final class CTNHBioPonderTags {

    public static final ResourceLocation BioMultiblock = ResourceLocation.tryBuild(CTNHBio.MODID, "bio_multiblock");

    private CTNHBioPonderTags() {}

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        CTNHPonderTagHelper.registerTag(REGISTRATE, helper, BioMultiblock,
                "Biological Multiblocks", "生物多方块机器",
                "Ponders on CTNH biological multiblock machines", "CTNH 生物多方块机器思索")
                .addToIndex()
                .item(CBMultiblocks.COGNI_ASSEMBLER.getItem(), true, false)
                .register();

        helper.addToTag(BioMultiblock).add(CBMultiblocks.COGNI_ASSEMBLER.getId());
    }
}
