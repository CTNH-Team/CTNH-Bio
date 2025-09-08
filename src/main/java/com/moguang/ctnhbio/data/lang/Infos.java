package com.moguang.ctnhbio.data.lang;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.*;

@Suffix("info")
public class Infos {
    @Prefix
    public abstract static class Jade {
        @Localized("Nutrient : ")
        public static Lang Nutrient;
    }
}
