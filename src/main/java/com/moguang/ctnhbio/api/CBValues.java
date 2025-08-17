package com.moguang.ctnhbio.api;

import com.moguang.ctnhbio.CTNHBio;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.object.Color;

public class CBValues {
    public static final ResourceLocation EMPTY_ANIMATION = CTNHBio.id("animation/empty.animation.json");

    public enum VoltageColor{
        ULV(0x99928f, 0x7a6e6a, 0x52413d),
        LV(0x85807b, 0x746f6b, 0x3d3934),
        MV(0xa5c6d6, 0xa5c6d6, 0x526e73),
        HV(0xd7d7e7, 0xc7c7d7, 0x808090),
        EV(0xdcb8e3, 0xd1abd9, 0x7d6c83),
        IV(0x8f9cb8, 0x76819f, 0x344549),
        LuV(0xdadada, 0xb9b9b9, 0x5e5e5e),
        ZPM(0x6e6c6d, 0x514a50, 0x2e2931),
        UV(0x6b8d74, 0x577960, 0x25462d)
        ;


        public final Color LIGHT;
        public final Color NORMAL;
        public final Color DARK;


        VoltageColor(int light, int normal, int dark) {
            LIGHT = Color.ofOpaque(light);
            NORMAL = Color.ofOpaque(normal);
            DARK = Color.ofOpaque(dark);
        }
    }

}
