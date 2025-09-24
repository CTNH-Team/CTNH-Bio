package com.moguang.ctnhbio.data.lang;

import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.*;

@Suffix("info")
public class Infos {
    @Prefix
    public abstract static class Jade {
        @EN("Nutrient : ")
        @CN("营养液 : ")
        public static Lang Nutrient;
    }
}
