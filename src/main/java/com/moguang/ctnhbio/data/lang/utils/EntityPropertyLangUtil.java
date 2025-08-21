package com.moguang.ctnhbio.data.lang.utils;

import com.moguang.ctnhbio.api.capability.recipe.EntityRecipeCapability;
import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.IBaseEntityProperty;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperator;
import net.minecraftforge.common.data.LanguageProvider;

public class EntityPropertyLangUtil {
    LanguageProvider provider;
    public EntityPropertyLangUtil(LanguageProvider provider,String inText, String outText, String type, String requirement){
        this.provider = provider;
        provider.add(EntityRecipeCapability.getTranslationKey(true), inText);
        provider.add(EntityRecipeCapability.getTranslationKey(false), outText);
        provider.add("jei.tooltip.recipe.entitytype",type);
        provider.add("ctnhbio.tooltip.entity.requirement",requirement);
    }
    //example: text = "Name %s %s" verb = "contains" , the result will be "Name contains %s" or "Name : %s"
    public void add(IBaseEntityProperty<?> prop, String text){
        provider.add(prop.getLanguageKey(), text);
    }
    public void add(PropertyOperator<?> operator, String text){
        provider.add(operator.getVerbKey(), text);
    }
}
