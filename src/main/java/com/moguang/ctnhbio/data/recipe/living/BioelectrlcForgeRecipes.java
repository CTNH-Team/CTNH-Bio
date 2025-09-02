package com.moguang.ctnhbio.data.recipe.living;

import com.github.elenterius.biomancy.crafting.EssenceIngredient;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.ingredient.FluidIngredient;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.recipe.CBRecipeBuilder;
import com.moguang.ctnhbio.registry.CBBlocks;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMachines;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plateDense;
import static com.gregtechceu.gtceu.common.data.GTItems.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.HULL;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.moguang.ctnhbio.data.materials.CommonMaterials.*;
import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

@SuppressWarnings("all")
public class BioelectrlcForgeRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        //生物锻炉
        //组件类
// 创造者混合物
        CBRecipeBuilder.of(CTNHBio.id("creator_mix_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 4)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 3)
                .inputItems(ModItems.EXOTIC_DUST.get().getDefaultInstance(), 2)
                .inputItems(ModItems.NUTRIENTS.get().getDefaultInstance(), 15)
                .outputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),1)
                .EUt(12)
                .duration(80)
                .save(provider);

// 肥料
        CBRecipeBuilder.of(CTNHBio.id("fertilizer_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.NUTRIENTS.get().getDefaultInstance(), 30)
                .inputItems(ModItems.ORGANIC_MATTER.get().getDefaultInstance(), 4)
                .inputItems(TagPrefix.dust,Steel, 4)
                .inputFluids(new FluidStack(Hormone_Secretion.getFluid(), 600))
                .inputFluids(new FluidStack(Regenerative_Fluid.getFluid(), 400))
                .outputItems(ModItems.FERTILIZER.get().getDefaultInstance())
                .EUt(12)
                .duration(80)
                .save(provider);

// 生物尖牙
        CBRecipeBuilder.of(CTNHBio.id("mob_fang_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(TagPrefix.dust,Steel, 6)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 8)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .outputItems(ModItems.MOB_FANG.get().getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);

// 生物爪子
        CBRecipeBuilder.of(CTNHBio.id("mob_claw_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(TagPrefix.dust,Steel, 7)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 8)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .outputItems(ModItems.MOB_CLAW.get().getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);

// 骨头（原版）
        CBRecipeBuilder.of(CTNHBio.id("bone_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 5)
                .inputItems(TagPrefix.dust,Steel, 1)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .outputItems(Items.BONE.getDefaultInstance())
                .EUt(8)
                .duration(50)
                .save(provider);

// 皮革（原版）
        CBRecipeBuilder.of(CTNHBio.id("leather_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 1)
                .outputItems(Items.LEATHER.getDefaultInstance())
                .EUt(8)
                .duration(50)
                .save(provider);

// 线（原版）
        CBRecipeBuilder.of(CTNHBio.id("string_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.dust,Steel, 1)
                .outputItems(Items.STRING.getDefaultInstance())
                .EUt(8)
                .duration(40)
                .save(provider);

// 蜘蛛网（原版）
        CBRecipeBuilder.of(CTNHBio.id("cobweb_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 8)
                .inputItems(TagPrefix.dust,Steel, 4)
                .outputItems(Items.COBWEB.getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);

// 鳞甲（原版）
        CBRecipeBuilder.of(CTNHBio.id("scute_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 6)
                .inputItems(TagPrefix.dust,Steel, 4)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .outputItems(Items.SCUTE.getDefaultInstance())
                .EUt(16)
                .duration(80)
                .save(provider);

// 鹦鹉螺壳（原版）
        CBRecipeBuilder.of(CTNHBio.id("nautilus_shell_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(TagPrefix.dust,Steel, 11)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 8)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("ctnhbio:xxx_cb")),1)
                .outputItems(Items.NAUTILUS_SHELL.getDefaultInstance())
                .EUt(24)
                .duration(100)
                .save(provider);

// 骷髅头颅（原版）
        CBRecipeBuilder.of(CTNHBio.id("skeleton_skull_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(4)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 50)
                .inputItems(TagPrefix.dust,Steel, 9)
                .outputItems(Items.SKELETON_SKULL.getDefaultInstance())
                .EUt(32)
                .duration(150)
                .save(provider);

// 凋灵骷髅头颅（原版）
        CBRecipeBuilder.of(CTNHBio.id("wither_skeleton_skull_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(4)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 50)
                .inputItems(TagPrefix.dust,Steel, 9)
                .inputFluids(new FluidStack(Withering_Ooze.getFluid(), 1800))
                .outputItems(Items.WITHER_SKELETON_SKULL.getDefaultInstance())
                .EUt(32)
                .duration(150)
                .save(provider);

// 玩家头颅（原版）
        CBRecipeBuilder.of(CTNHBio.id("player_head_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(4)
                .inputItems(Items.SKELETON_SKULL.getDefaultInstance())
                .inputItems(EssenceIngredient.of(EntityType.PLAYER))
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 34)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 11)
                .outputItems(Items.PLAYER_HEAD.getDefaultInstance())
                .EUt(32)
                .duration(150)
                .save(provider);

// 猪灵头颅（原版）
        CBRecipeBuilder.of(CTNHBio.id("piglin_head_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(4)
                .inputItems(Items.SKELETON_SKULL.getDefaultInstance())
                .inputItems(EssenceIngredient.of(EntityType.PIGLIN))
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 38)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 14)
                .outputItems(Items.PIGLIN_HEAD.getDefaultInstance())
                .EUt(32)
                .duration(150)
                .save(provider);

// 培养皿
        CBRecipeBuilder.of(CTNHBio.id("vial_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 1)
                .outputItems(ModItems.VIAL.get().getDefaultInstance())
                .EUt(8)
                .duration(50)
                .save(provider);

//建筑方块类
// 菌光体（原版）
        CBRecipeBuilder.of(CTNHBio.id("shroomlight_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.BIO_LUMENS.get().getDefaultInstance(), 10)
                .inputItems(Items.YELLOW_DYE.getDefaultInstance(), 2)
                .inputItems(ModItems.ORGANIC_MATTER.get().getDefaultInstance(), 4)
                .outputItems(Items.SHROOMLIGHT.getDefaultInstance())
                .EUt(20)
                .duration(100)
                .save(provider);

// 培养皿支架
        CBRecipeBuilder.of(CTNHBio.id("vial_holder_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 8)
                .outputItems(ModItems.VIAL_HOLDER.get().getDefaultInstance())
                .EUt(8)
                .duration(40)
                .save(provider);
//电力分解者-MV
        CBRecipeBuilder.of(CTNHBio.id("mv_decomposer_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.DECOMPOSER.get().getDefaultInstance())
                .inputItems(GTMachines.MACERATOR[MV],1)
                .inputItems(CBItems.SYNET_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Copper, 2)
                .inputItems(FLUID_REGULATOR_MV,2)
                .inputItems(ELECTRIC_PUMP_MV,2)
                .outputItems(CBMachines.DECOMPOSER[MV],1)
                .EUt(96)
                .duration(300)
                .save(provider);
//电力生物反应腔-MV
        CBRecipeBuilder.of(CTNHBio.id("mv_bioreactor_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(Items.SLIME_BLOCK,1)
                .inputItems(GTMachines.CHEMICAL_REACTOR[MV],1)
                .inputItems(CBItems.SYNET_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Copper, 2)
                .inputItems(FLUID_REGULATOR_MV,2)
                .inputItems(ELECTRIC_PUMP_MV,2)
                .outputItems(CBMachines.BIOREACTOR[MV],1)
                .EUt(96)
                .duration(300)
                .save(provider);
//电力生物消化器-MV
        CBRecipeBuilder.of(CTNHBio.id("mv_digester_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.DIGESTER.get().getDefaultInstance())
                .inputItems(GTMachines.BREWERY[MV],1)
                .inputItems(CBItems.SYNET_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Copper, 2)
                .inputItems(FLUID_REGULATOR_MV,2)
                .inputItems(ELECTRIC_PUMP_MV,2)
                .outputItems(CBMachines.DIGESTER[MV],1)
                .EUt(96)
                .duration(300)
                .save(provider);
//电力生物锻炉-MV
        CBRecipeBuilder.of(CTNHBio.id("mv_forge_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.BIO_FORGE.get().getDefaultInstance())
                .inputItems(GTMachines.ASSEMBLER[MV],1)
                .inputItems(CBItems.SYNET_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Copper, 2)
                .inputItems(FLUID_REGULATOR_MV,2)
                .inputItems(ELECTRIC_PUMP_MV,2)
                .outputItems(CBMachines.BIOELECTRIC_FORGE[MV],1)
                .EUt(96)
                .duration(300)
                .save(provider);
//电力分解者-HV
        CBRecipeBuilder.of(CTNHBio.id("hv_decomposer_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.DECOMPOSER.get().getDefaultInstance())
                .inputItems(GTMachines.MACERATOR[HV],1)
                .inputItems(CBItems.META_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Gold, 2)
                .inputItems(FLUID_REGULATOR_HV,2)
                .inputItems(ELECTRIC_PUMP_HV,2)
                .outputItems(CBMachines.DECOMPOSER[HV],1)
                .EUt(480)
                .duration(300)
                .save(provider);
//电力生物反应腔-HV
        CBRecipeBuilder.of(CTNHBio.id("hv_bioreactor_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(Items.SLIME_BLOCK,1)
                .inputItems(GTMachines.CHEMICAL_REACTOR[HV],1)
                .inputItems(CBItems.META_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Gold, 2)
                .inputItems(FLUID_REGULATOR_HV,2)
                .inputItems(ELECTRIC_PUMP_HV,2)
                .outputItems(CBMachines.BIOREACTOR[HV],1)
                .EUt(480)
                .duration(300)
                .save(provider);
//电力生物消化器-HV
        CBRecipeBuilder.of(CTNHBio.id("hv_digester_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.DIGESTER.get().getDefaultInstance())
                .inputItems(GTMachines.BREWERY[HV],1)
                .inputItems(CBItems.META_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Gold, 2)
                .inputItems(FLUID_REGULATOR_HV,2)
                .inputItems(ELECTRIC_PUMP_HV,2)
                .outputItems(CBMachines.DIGESTER[HV],1)
                .EUt(480)
                .duration(300)
                .save(provider);
//电力生物锻炉-HV
        CBRecipeBuilder.of(CTNHBio.id("hv_forge_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.BIO_FORGE.get().getDefaultInstance())
                .inputItems(GTMachines.ASSEMBLER[HV],1)
                .inputItems(CBItems.META_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Gold, 2)
                .inputItems(FLUID_REGULATOR_HV,2)
                .inputItems(ELECTRIC_PUMP_HV,2)
                .outputItems(CBMachines.BIOELECTRIC_FORGE[HV],1)
                .EUt(480)
                .duration(300)
                .save(provider);
//电力分解者-EV
        CBRecipeBuilder.of(CTNHBio.id("ev_decomposer_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.DECOMPOSER.get().getDefaultInstance())
                .inputItems(GTMachines.MACERATOR[EV],1)
                .inputItems(CBItems.NOVA_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Aluminium, 2)
                .inputItems(FLUID_REGULATOR_EV,2)
                .inputItems(ELECTRIC_PUMP_EV,2)
                .outputItems(CBMachines.DECOMPOSER[EV],1)
                .EUt(2000)
                .duration(300)
                .save(provider);
//电力生物反应腔-EV
        CBRecipeBuilder.of(CTNHBio.id("ev_bioreactor_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(Items.SLIME_BLOCK,1)
                .inputItems(GTMachines.CHEMICAL_REACTOR[EV],1)
                .inputItems(CBItems.NOVA_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Aluminium, 2)
                .inputItems(FLUID_REGULATOR_EV,2)
                .inputItems(ELECTRIC_PUMP_EV,2)
                .outputItems(CBMachines.BIOREACTOR[EV],1)
                .EUt(2000)
                .duration(300)
                .save(provider);
//电力生物消化器-EV
        CBRecipeBuilder.of(CTNHBio.id("ev_digester_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.DIGESTER.get().getDefaultInstance())
                .inputItems(GTMachines.BREWERY[EV],1)
                .inputItems(CBItems.NOVA_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Aluminium, 2)
                .inputItems(FLUID_REGULATOR_EV,2)
                .inputItems(ELECTRIC_PUMP_EV,2)
                .outputItems(CBMachines.DIGESTER[EV],1)
                .EUt(2000)
                .duration(300)
                .save(provider);
//电力生物锻炉-EV
        CBRecipeBuilder.of(CTNHBio.id("ev_forge_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.BIO_FORGE.get().getDefaultInstance())
                .inputItems(GTMachines.ASSEMBLER[EV],1)
                .inputItems(CBItems.NOVA_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Aluminium, 2)
                .inputItems(FLUID_REGULATOR_EV,2)
                .inputItems(ELECTRIC_PUMP_EV,2)
                .outputItems(CBMachines.BIOELECTRIC_FORGE[EV],1)
                .EUt(2000)
                .duration(300)
                .save(provider);
//电力分解者-IV
        CBRecipeBuilder.of(CTNHBio.id("iv_decomposer_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.DECOMPOSER.get().getDefaultInstance())
                .inputItems(GTMachines.MACERATOR[IV],1)
                .inputItems(CBItems.OMNI_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Platinum, 2)
                .inputItems(FLUID_REGULATOR_IV,2)
                .inputItems(ELECTRIC_PUMP_IV,2)
                .outputItems(CBMachines.DECOMPOSER[IV],1)
                .EUt(6144)
                .duration(300)
                .save(provider);

//电力生物反应腔-IV
        CBRecipeBuilder.of(CTNHBio.id("iv_bioreactor_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(Items.SLIME_BLOCK,1)
                .inputItems(GTMachines.CHEMICAL_REACTOR[IV],1)
                .inputItems(CBItems.OMNI_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Platinum, 2)
                .inputItems(FLUID_REGULATOR_IV,2)
                .inputItems(ELECTRIC_PUMP_IV,2)
                .outputItems(CBMachines.BIOREACTOR[IV],1)
                .EUt(6144)
                .duration(300)
                .save(provider);

//电力生物消化器-IV
        CBRecipeBuilder.of(CTNHBio.id("iv_digester_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.DIGESTER.get().getDefaultInstance())
                .inputItems(GTMachines.BREWERY[IV],1)
                .inputItems(CBItems.OMNI_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Platinum, 2)
                .inputItems(FLUID_REGULATOR_IV,2)
                .inputItems(ELECTRIC_PUMP_IV,2)
                .outputItems(CBMachines.DIGESTER[IV],1)
                .EUt(6144)
                .duration(300)
                .save(provider);
//电力生物锻炉-IV
        CBRecipeBuilder.of(CTNHBio.id("iv_forge_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.BIO_FORGE.get().getDefaultInstance())
                .inputItems(GTMachines.ASSEMBLER[IV],1)
                .inputItems(CBItems.OMNI_CORE.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.cableGtSingle, Platinum, 2)
                .inputItems(FLUID_REGULATOR_IV,2)
                .inputItems(ELECTRIC_PUMP_IV,2)
                .outputItems(CBMachines.BIOELECTRIC_FORGE[IV],1)
                .EUt(6144)
                .duration(300)
                .save(provider);
//缸中之脑-HV
        CBRecipeBuilder.of(CTNHBio.id("hv_brain_in_a_vat_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),8)
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS,5)
                .inputItems(HULL[HV],1)
                .inputItems(CustomTags.EV_CIRCUITS,4)
                .inputItems(TagPrefix.cableGtSingle, Gold, 4)
                .inputItems(GTBlocks.HERMETIC_CASING_HV,2)
                .outputItems(CBMachines.BRAIN_IN_A_VAT[HV],1)
                .EUt(480)
                .duration(500)
                .save(provider);
// 缸中之脑-EV
        CBRecipeBuilder.of(CTNHBio.id("ev_brain_in_a_vat_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),8)
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS,5)
                .inputItems(HULL[EV],1)
                .inputItems(CustomTags.IV_CIRCUITS,4)
                .inputItems(TagPrefix.cableGtSingle, Aluminium, 4)
                .inputItems(GTBlocks.HERMETIC_CASING_EV,2)
                .outputItems(CBMachines.BRAIN_IN_A_VAT[EV],1)
                .EUt(2000)
                .duration(500)
                .save(provider);

// 缸中之脑-IV
        CBRecipeBuilder.of(CTNHBio.id("iv_brain_in_a_vat_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),8)
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS,5)
                .inputItems(HULL[IV],1)
                .inputItems(CustomTags.LuV_CIRCUITS,4)
                .inputItems(TagPrefix.cableGtSingle, Platinum, 4)
                .inputItems(GTBlocks.HERMETIC_CASING_IV,2)
                .outputItems(CBMachines.BRAIN_IN_A_VAT[IV],1)
                .EUt(6144)
                .duration(500)
                .save(provider);

// 缸中之脑-LuV
        CBRecipeBuilder.of(CTNHBio.id("luv_brain_in_a_vat_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),8)
                .inputItems(GTBlocks.CASING_TEMPERED_GLASS,5)
                .inputItems(HULL[LuV],1)
                .inputItems(CustomTags.ZPM_CIRCUITS,4)
                .inputItems(TagPrefix.cableGtSingle, NiobiumTitanium, 4)
                .inputItems(GTBlocks.HERMETIC_CASING_LuV,2)
                .outputItems(CBMachines.BRAIN_IN_A_VAT[LuV],1)
                .EUt(24768)
                .duration(500)
                .save(provider);
//侍僧套-武器盾牌
        CBRecipeBuilder.of(CTNHBio.id("acolyte_armor_helmet"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(25)
                .effect(MobEffects.JUMP)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance(),2)
                .inputItems(Items.LEATHER_HELMET,1)
                .inputItems(ModItems.MOB_CLAW.get().getDefaultInstance(),2)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(),4)
                .inputItems(TagPrefix.plateDense,Steel,2)
                .outputItems(ModItems.ACOLYTE_ARMOR_HELMET,1)
                .EUt(64)
                .duration(500)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("acolyte_armor_chestpalte"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(25)
                .effect(MobEffects.JUMP)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance(),2)
                .inputItems(Items.LEATHER_CHESTPLATE,1)
                .inputItems(ModItems.MOB_CLAW.get().getDefaultInstance(),2)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(),4)
                .inputItems(TagPrefix.plateDense,Steel,2)
                .outputItems(ModItems.ACOLYTE_ARMOR_CHESTPLATE,1)
                .EUt(64)
                .duration(500)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("acolyte_armor_leggings"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(25)
                .effect(MobEffects.JUMP)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance(),2)
                .inputItems(Items.LEATHER_LEGGINGS,1)
                .inputItems(ModItems.MOB_CLAW.get().getDefaultInstance(),2)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(),4)
                .inputItems(TagPrefix.plateDense,Steel,2)
                .outputItems(ModItems.ACOLYTE_ARMOR_LEGGINGS,1)
                .EUt(64)
                .duration(500)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("acolyte_armor_boots"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(25)
                .effect(MobEffects.JUMP)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance(),2)
                .inputItems(Items.LEATHER_BOOTS,1)
                .inputItems(ModItems.MOB_CLAW.get().getDefaultInstance(),2)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(),4)
                .inputItems(TagPrefix.plateDense,Steel,2)
                .outputItems(ModItems.ACOLYTE_ARMOR_BOOTS,1)
                .EUt(64)
                .duration(500)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("thorn_shield"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(10)
                .effect(MobEffects.JUMP)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance(),2)
                .inputItems(Items.SHIELD,1)
                .inputItems(ModItems.TOXIN_GLAND.get().getDefaultInstance(),2)
                .inputItems(ModItems.VOLATILE_GLAND.get().getDefaultInstance(),4)
                .inputItems(Items.PUFFERFISH,4)
                .outputItems(ModItems.THORN_SHIELD,1)
                .EUt(128)
                .duration(250)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("ravenous_claws_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(10)
                .effect(MobEffects.JUMP)
                .inputItems(Items.GOLDEN_SHOVEL.getDefaultInstance(),1)
                .inputItems(CBItems.META_CORE.get().getDefaultInstance(),1)
                .inputItems(ModItems.MOB_CLAW,8)
                .inputItems(ModItems.TOXIN_GLAND.get().getDefaultInstance(),4)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),8)
                .inputFluids(new FluidStack(Frenzy_Serum.getFluid(), 500))
                .inputFluids(new FluidStack(Absorption_Boost.getFluid(), 500))
                .outputItems(ModItems.RAVENOUS_CLAWS,1)
                .EUt(384)
                .duration(150)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("caustic_gunblade_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(12)
                .effect(MobEffects.JUMP)
                .inputItems(TagPrefix.pipeSmallFluid,Steel,4)
                .inputItems(CBItems.META_CORE.get().getDefaultInstance(),1)
                .inputItems(ModItems.MOB_FANG,4)
                .inputItems(CBItems.ORGANIC_VIAL.get().getDefaultInstance(),2)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),8)
                .inputFluids(new FluidStack(HydrochloricAcid.getFluid(), 1000))
                .inputFluids(new FluidStack(HypochlorousAcid.getFluid(), 1000))
                .outputItems(ModItems.CAUSTIC_GUNBLADE,1)
                .EUt(384)
                .duration(250)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("injector_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(10)
                .effect(MobEffects.JUMP)
                .inputItems(ELECTRIC_PUMP_MV,2)
                .inputItems(CBItems.SYNET_CORE.get().getDefaultInstance(),2)
                .inputItems(TagPrefix.pipeTinyFluid,StainlessSteel,1)
                .inputItems(CBItems.ORGANIC_VIAL.get().getDefaultInstance(),2)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),8)
                .inputFluids(new FluidStack(Toxin_Extract.getFluid(), 1000))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 1000))
                .outputItems(ModItems.INJECTOR,1)
                .EUt(192)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("essence_extractor_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(10)
                .effect(MobEffects.JUMP)
                .inputItems(FLUID_REGULATOR_MV,2)
                .inputItems(CBItems.SYNET_CORE.get().getDefaultInstance(),2)
                .inputItems(TagPrefix.pipeTinyFluid,StainlessSteel,1)
                .inputItems(CBItems.ORGANIC_VIAL.get().getDefaultInstance(),2)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(),8)
                .inputFluids(new FluidStack(Toxin_Extract.getFluid(), 1000))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 1000))
                .outputItems(ModItems.ESSENCE_EXTRACTOR,1)
                .EUt(192)
                .duration(100)
                .save(provider);
//脉络核心
        CBRecipeBuilder.of(CTNHBio.id("synet_core_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance())
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:aluminium_frame")),1)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:copper_ring")),1)
                .inputItems(CustomTags.HV_CIRCUITS,2)
                .inputItems(Items.GLOW_INK_SAC.getDefaultInstance(),2)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(),4)
                .inputFluids(new FluidStack(Regenerative_Fluid.getFluid(), 1000))
                .chancedOutput(new ItemStack(CBItems.SYNET_CORE),5000,0)
                .EUt(96)
                .duration(500)
                .save(provider);
//拓扑核心
        CBRecipeBuilder.of(CTNHBio.id("meta_core_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance())
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:stainless_steel_frame_cb")),1)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:electrum_ring_cb")),1)
                .inputItems(CustomTags.EV_CIRCUITS,2)
                .inputItems(GTItems.QUANTUM_EYE.get(),2)
                .inputItems(ModItems.EXOTIC_DUST.get().getDefaultInstance(),20)
                .inputFluids(new FluidStack(Unstable_Compound.getFluid(), 1000))
                .inputFluids(new FluidStack(Organic_Compound.getFluid(), 1000))
                .chancedOutput(new ItemStack(CBItems.META_CORE),4000,0)
                .EUt(384)
                .duration(500)
                .save(provider);
//灵蜕核心
        CBRecipeBuilder.of(CTNHBio.id("nova_core_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance())
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:titanium_frame_cb")),1)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:black_steel_gear_cb")),1)
                .inputItems(CustomTags.IV_CIRCUITS,2)
                .inputItems(GTItems.QUANTUM_STAR.get(),2)
                .inputItems(TagPrefix.gemFlawless,Emerald,30)
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 1000))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 1000))
                .inputFluids(new FluidStack(Hormone_Secretion.getFluid(), 1000))
                .chancedOutput(new ItemStack(CBItems.NOVA_CORE),3000,0)
                .EUt(1960)
                .duration(500)
                .save(provider);
//湿件配件
        CBRecipeBuilder.of(CTNHBio.id("wetware_resistor_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(14)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.dust,Graphene)
                .inputItems(TagPrefix.wireFine,Platinum,16)
                .inputItems(TagPrefix.wireFine,Tantalum,16)
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:polypyrrole")),144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_RESISTOR.get()), UniformInt.of(30, 48))
                .EUt(6144)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_capacitor_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(14)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(CBItems.SYNET_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.foil,Polybenzimidazole,4)
                .inputItems(TagPrefix.foil,Polycaprolactam,2)
                .inputItems(TagPrefix.foil,UraniumRhodiumDinaquadide,2)
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:polypyrrole")),144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_CAPACITOR.get()), UniformInt.of(18, 28))
                .EUt(6144)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_inductor_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(14)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(CBItems.META_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.ring,NaquadahAlloy,4)
                .inputItems(TagPrefix.ring,NickelZincFerrite,4)
                .inputItems(TagPrefix.wireFine,Platinum,16)
                .inputItems(TagPrefix.wireFine,Tantalum,16)
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:polypyrrole")),144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_INDUCTOR.get()), UniformInt.of(24, 32))
                .EUt(6144)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_diode_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(14)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(CBItems.NOVA_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.dust,IndiumGalliumPhosphide,1)
                .inputItems(TagPrefix.wireFine,YttriumBariumCuprate,16)
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:polypyrrole")),144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_DIODE.get()), UniformInt.of(20, 30))
                .EUt(6144)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_transistor_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(14)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(CBItems.OMNI_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.foil,Graphene,4)
                .inputItems(TagPrefix.foil,Osmiridium,4)
                .inputItems(TagPrefix.wireFine,Tantalum,16)
                .inputFluids(FluidIngredient.of(ForgeRegistries.FLUIDS.getValue(ResourceLocation.parse("gtceu:polypyrrole")),144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_TRANSISTOR.get()), UniformInt.of(16, 32))
                .EUt(6144)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_circuit_board_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(10)
                .inputItems(GTItems.MULTILAYER_FIBER_BOARD.get().getDefaultInstance(),8)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(),4)
                .inputFluids(new FluidStack(Frenzy_Serum.getFluid(), 50))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 50))
                .inputFluids(new FluidStack(Unstable_Compound.getFluid(), 50))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_CIRCUIT_BOARD.get()), UniformInt.of(4, 12))
                .EUt(1960)
                .duration(60)
                .save(provider);
        //神经元CPU-低效
        CBRecipeBuilder.of(CTNHBio.id("neuro_processor_low_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(20)
                .inputItems(CBItems.WETWARE_PRINTED_CIRCUIT_BOARD.get().getDefaultInstance())
                .inputItems(QUBIT_CENTRAL_PROCESSING_UNIT,2)
                .inputItems(ModItems.CREATOR_MIX.get().getDefaultInstance(),4)
                .inputFluids(new FluidStack(Bile.getFluid(), 100))
                .inputFluids(new FluidStack(Heterogeneous_Compound.getFluid(), 100))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 100))
                .outputItemsRanged(new ItemStack(GTItems.NEURO_PROCESSOR.get()), UniformInt.of(1, 2))
                .EUt(6144)
                .duration(200)
                .save(provider);
        //湿件电路板Luv-ZPM
        CBRecipeBuilder.of(CTNHBio.id("wetware_processor_luv_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(10)
                .inputItems(GTItems.NEURO_PROCESSOR.get().getDefaultInstance(),1)
                .inputItems(GTItems.CRYSTAL_CENTRAL_PROCESSING_UNIT.get().getDefaultInstance(),1)
                .inputItems(GTItems.NANO_CENTRAL_PROCESSING_UNIT.get().getDefaultInstance(),1)
                .inputItems(CBItems.WETWARE_CAPACITOR.get().getDefaultInstance(),2)
                .inputItems(CBItems.WETWARE_TRANSISTOR.get().getDefaultInstance(),2)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:fine_bio_flexible_wire")),16)
                .inputFluids(new FluidStack(Ageing_Serum.getFluid(), 150))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 150))
                .outputItemsRanged(new ItemStack(GTItems.WETWARE_PROCESSOR_LuV.get()), UniformInt.of(2, 4))
                .EUt(6144)
                .duration(200)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_processor_zpm_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(10)
                .inputItems(CBItems.WETWARE_PRINTED_CIRCUIT_BOARD.get().getDefaultInstance(),1)
                .inputItems(GTItems.WETWARE_PROCESSOR_LuV.get().getDefaultInstance(),2)
                .inputItems(CBItems.WETWARE_INDUCTOR.get().getDefaultInstance(),2)
                .inputItems(CBItems.WETWARE_CAPACITOR.get().getDefaultInstance(),2)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:advanced_ram_chip")),4)
                .inputItems(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse("gtceu:fine_bio_flexible_wire")),64)
                .inputFluids(new FluidStack(Ageing_Serum.getFluid(), 100))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputItemsRanged(new ItemStack(GTItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM.get()), UniformInt.of(1, 3))
                .EUt(6144)
                .duration(400)
                .save(provider);
        //神经突触机械方块
        CBRecipeBuilder.of(CTNHBio.id("weird_pixel_dust_recipe"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .inputItems(TagPrefix.frameGt,BLOODSTEEL,1)
                .inputItems(CustomTags.LuV_CIRCUITS,2)
                .inputItems(plateDense,BLOODSTEEL,2)
                .inputItems(ModItems.CREATOR_MIX,4)
                .outputItemsRanged(CBBlocks.SYNAPTIC_CASING, UniformInt.of(1, 2))
                .EUt(24768)
                .duration(400)
                .save(provider);

    }
}
