package com.moguang.ctnhbio.data.lang;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data.EntityProperties;
import com.moguang.ctnhbio.api.recipe.matcher.PropertyOperators;
import com.moguang.ctnhbio.data.lang.utils.*;
import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class EnglishLangHandler {

    public static void init(RegistrateLangProvider provider) {
        provider.add("ctnhbio.great_flesh.info.0",
                "§5Feed the Primordial Cradlle and give it Potion of Healing, then the Cradle will hatch Flesh Blobs. Pick those Blobs with potentials (Those that are not hungry).");
        provider.add("ctnhbio.great_flesh.info.1",
                "§5Use Organic Vial to load Primordial Serum into Bio-injector and inject it to them.");
        provider.add("ctnhbio.great_flesh.info.2",
                "§5And they will show you the PUREST and the MOST PRIMAL forms of life.");

        provider.add("config.jade.plugin_gtceu.living_machine_status_provider", "[CTNH] Living Machine Status");
        provider.add("ctnhbio.living_machine", "living machine");
        provider.add("item.gtceu.tool.boning_knife", "%s Boning Knife");
        // replace(provider, );

        provider.add("fluid_type.biofactory.nutrients_fluid", "Nutrients Fluid");

        provider.add("emi.category.ctnhbio.despoil_loot", "Despoil");
        provider.add("ctnhbio.nutrient_bar.info", "Nutrients:");
        provider.add("jade.nutrient.info", "Nutrients: %s");

        // provider.add("recipe.condition.effect.tooltip", "Potion Effect : %s");
        provider.add("ctnhbio.recipe.nutrient", "Nutrients Consumption:%d");
        provider.add("ctnhbio.recipe.nutrient_generate", "Nutrients Generation:%d");
        provider.add("ctnhbio.jade.nutrient_stored", "%s / %s");

        provider.add("jei.ctnhbio.mob_crushing", "Mob Crushing");
        provider.add("jei.ctnhbio.tooltip.chance", "Chance:");
        provider.add("jei.ctnhbio.tooltip.amount_range", "Amount:%d-%d");

        // provider.add("ctnhbio.fluid.type_organic.tooltip", "§aBioactive Fluid");
        // provider.add("ctnhbio.fluid_pipe.can_handle_organic", "§aCan handle Bioactive Fluid");
        // provider.add("ctnhbio.fluid_pipe.cannot_handle_organic", "§4Bioactive Fluid may be inactivated");

        provider.add("ctnhbio.recipe.datamodel.requirement", "§pAbove Are Minimum Requirement(s)");

        provider.add("recipe.capability.entity.name", "Entity");

        provider.add("ctnhbio.copyright.info", "Added By CTNHBio");
        // EntityProperties
        EntityPropertyLangUtil epu = new EntityPropertyLangUtil(provider, "Input Entity", "Output Entity",
                "Available: %s", "Requirement:");

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
