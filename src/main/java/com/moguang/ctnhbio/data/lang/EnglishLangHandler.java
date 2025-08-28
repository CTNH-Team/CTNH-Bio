package com.moguang.ctnhbio.data.lang;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityProperties;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperators;
import com.moguang.ctnhbio.data.lang.utils.*;
import com.tterrag.registrate.providers.RegistrateLangProvider;


public class EnglishLangHandler {
    public static void init(RegistrateLangProvider provider){

        provider.add("config.jade.plugin_gtceu.living_machine_status_provider", "[CTNH] Living Machine Status");
        provider.add("ctnhbio.living_machine", "living machine");
        provider.add("item.gtceu.tool.boning_knife", "%s Boning Knife");
        //replace(provider, );
        provider.add("emi.category.ctnhbio.despoil_loot", "Despoil");
        provider.add("ctnhbio.nutrient_bar.info", "Nutrient:");
        provider.add("jade.nutrient.info", "Nutrient：");

        provider.add("recipe.condition.effect.tooltip", "Potion Effect : %s");
        provider.add("ctnhbio.recipe.nutrient", "Nutrient Consumption: %d");
        provider.add("ctnhbio.jade.nutrient_stored", "%s / %s");

        provider.add("ctnhbio.fluid.type_organic.tooltip", "§aBioactive Fluid");
        provider.add("ctnhbio.fluid_pipe.can_handle_organic", "§aCan handle Bioactive Fluid");
        provider.add("ctnhbio.fluid_pipe.cannot_handle_organic", "§4Bioactive Fluid may be inactivated");

        provider.add("ctnhbio.mv_machine.tooltip", "MV Biochip");
        provider.add("ctnhbio.hv_machine.tooltip", "HV Biochip");
        provider.add("ctnhbio.ev_machine.tooltip", "EV Biochip");
        provider.add("ctnhbio.iv_machine.tooltip", "IV Biochip");


        provider.add("ctnhbio.synet_core.tooltip", "A metal skeleton intricate as vascular circuits, with every groove pulsing rhythmically, as if transporting some invisible life energy.");
        provider.add("ctnhbio.meta_core.tooltip", "A geometric structure perpetually folding upon itself, its surface revealing angles that defy Euclidean laws, continuously performing impossible spatial transformations in stillness.");
        provider.add("ctnhbio.nova_core.tooltip", "Beneath its semi-transparent shell float countless crystal fragments, constantly shedding and regenerating, each fracture refracting different light spectra.");
        provider.add("ctnhbio.omni_core.tooltip", "Within the mirror-smooth sphere, nebular matter slowly rotates - the longer you gaze, the clearer your own silhouette emerges within it.");

        provider.add("ctnhbio.recipe.datamodel.requirement", "§pAbove Are Minimum Requirement(s)");

        //EntityProperties
        EntityPropertyLangUtil epu = new EntityPropertyLangUtil(provider,"Input Entity","Output Entity","Available: %s","Requirement:");

        epu.add(EntityProperties.FALL_DISTANCE, "Fall Distance");
        epu.add(EntityProperties.REMAINING_FIRE_TICKS, "Remaining Fire Ticks");
        epu.add(EntityProperties.AIR_SUPPLY, "Air Supply");
        epu.add(EntityProperties.ON_GROUND, "On Ground");
        epu.add(EntityProperties.INVULNERABLE, "Invulnerable");
        epu.add(EntityProperties.PORTAL_COOLDOWN, "Portal Cooldown");
        epu.add(EntityProperties.CUSTOM_NAME, "Custom Name");
        epu.add(EntityProperties.CUSTOM_NAME_VISIBLE, "Custom Name Visible");
        epu.add(EntityProperties.SILENT, "Silent");
        epu.add(EntityProperties.NO_GRAVITY, "No Gravity");
        epu.add(EntityProperties.GLOWING, "Glowing");
        epu.add(EntityProperties.TICKS_FROZEN, "Ticks Frozen");
        epu.add(EntityProperties.HAS_VISUAL_FIRE, "Has Visual Fire");
        epu.add(EntityProperties.CAN_UPDATE, "Can Update");
        epu.add(EntityProperties.HEALTH, "Health");
        epu.add(EntityProperties.HURT_TIME, "Hurt Time");
        epu.add(EntityProperties.HURT_BY_TIMESTAMP, "Hurt By Timestamp");
        epu.add(EntityProperties.DEATH_TIME, "Death Time");
        epu.add(EntityProperties.ABSORPTION_AMOUNT, "Absorption Amount");
        epu.add(EntityProperties.FALL_FLYING, "Fall Flying");

        epu.add(PropertyOperators.EQUAL, "Equals to");
        epu.add(PropertyOperators.GREATER, "Greater Than");
        epu.add(PropertyOperators.LESSER, "Lesser Than");
        epu.add(PropertyOperators.CONTAIN, "Contains");

    }
}
