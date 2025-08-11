package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.crafting.EssenceIngredient;
import com.github.elenterius.biomancy.init.ModItems;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.data.materials.CommonMaterials;
import com.moguang.ctnhbio.registry.CBItems;
import com.moguang.ctnhbio.registry.CBMachines;
import com.moguang.ctnhbio.registry.CBMaterials;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTItems.QUBIT_CENTRAL_PROCESSING_UNIT;
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
                .nutrient(1)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 4)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 3)
                .inputItems(ModItems.EXOTIC_DUST.get().getDefaultInstance(), 2)
                .inputItems(ModItems.NUTRIENTS.get().getDefaultInstance(), 15)
                .outputItems(ModItems.CREATOR_MIX.get().getDefaultInstance())
                .EUt(12)
                .duration(80)
                .save(provider);

// 肥料
        CBRecipeBuilder.of(CTNHBio.id("fertilizer_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.NUTRIENTS.get().getDefaultInstance(), 30)
                .inputItems(ModItems.ORGANIC_MATTER.get().getDefaultInstance(), 4)
                .inputItems(TagPrefix.dust,Steel, 4)
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 600))
                .inputFluids(new FluidStack(Regenerate_Fluid.getFluid(), 400))
                .outputItems(ModItems.FERTILIZER.get().getDefaultInstance())
                .EUt(12)
                .duration(80)
                .save(provider);

// 生物尖牙
        CBRecipeBuilder.of(CTNHBio.id("mob_fang_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(TagPrefix.dust,Steel, 6)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 8)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .outputItems(ModItems.MOB_FANG.get().getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);

// 生物爪子
        CBRecipeBuilder.of(CTNHBio.id("mob_claw_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(TagPrefix.dust,Steel, 7)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 8)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .outputItems(ModItems.MOB_CLAW.get().getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);

// 骨头（原版）
        CBRecipeBuilder.of(CTNHBio.id("bone_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 5)
                .inputItems(TagPrefix.dust,Steel, 1)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .outputItems(Items.BONE.getDefaultInstance())
                .EUt(8)
                .duration(50)
                .save(provider);

// 皮革（原版）
        CBRecipeBuilder.of(CTNHBio.id("leather_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 1)
                .outputItems(Items.LEATHER.getDefaultInstance())
                .EUt(8)
                .duration(50)
                .save(provider);

// 线（原版）
        CBRecipeBuilder.of(CTNHBio.id("string_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 2)
                .inputItems(TagPrefix.dust,Steel, 1)
                .outputItems(Items.STRING.getDefaultInstance())
                .EUt(8)
                .duration(40)
                .save(provider);

// 蜘蛛网（原版）
        CBRecipeBuilder.of(CTNHBio.id("cobweb_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 8)
                .inputItems(TagPrefix.dust,Steel, 4)
                .outputItems(Items.COBWEB.getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);

// 鳞甲（原版）
        CBRecipeBuilder.of(CTNHBio.id("scute_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 6)
                .inputItems(TagPrefix.dust,Steel, 4)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .outputItems(Items.SCUTE.getDefaultInstance())
                .EUt(16)
                .duration(80)
                .save(provider);

// 鹦鹉螺壳（原版）
        CBRecipeBuilder.of(CTNHBio.id("nautilus_shell_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(TagPrefix.dust,Steel, 11)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 8)
                .inputItems(TagPrefix.dust,Calcite, 1)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ctnhbio:xxx_cb")),1)
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
                .inputFluids(new FluidStack(Wither_Slime.getFluid(), 1800))
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
        //工具类
// 贪婪之爪
        CBRecipeBuilder.of(CTNHBio.id("ravenous_claws_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(250)
                .effect(MobEffects.CONFUSION)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.MOB_CLAW.get().getDefaultInstance(), 3)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 16)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 18)
                .inputItems(TagPrefix.dust,Steel, 15)
                .outputItems(ModItems.RAVENOUS_CLAWS.get().getDefaultInstance())
                .EUt(30)
                .duration(200)
                .save(provider);

// 荆棘盾牌
        CBRecipeBuilder.of(CTNHBio.id("thorn_shield_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(250)
                .effect(MobEffects.CONFUSION)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.MOB_FANG.get().getDefaultInstance(), 8)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 10)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 32)
                .inputItems(TagPrefix.dust,Steel, 24)
                .outputItems(ModItems.THORN_SHIELD.get().getDefaultInstance())
                .EUt(30)
                .duration(200)
                .save(provider);

// 腐蚀枪刃
        CBRecipeBuilder.of(CTNHBio.id("caustic_gunblade_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(250)
                .effect(MobEffects.CONFUSION)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 16)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.GENERIC_MOB_GLAND.get().getDefaultInstance(), 2)
                .inputItems(ModItems.PRIMAL_ORIFICE.get().getDefaultInstance(), 4)
                .outputItems(ModItems.CAUSTIC_GUNBLADE.get().getDefaultInstance())
                .EUt(30)
                .duration(200)
                .save(provider);

// 注射器
        CBRecipeBuilder.of(CTNHBio.id("injector_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(5)
                .effect(MobEffects.CONFUSION)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 20)
                .inputItems(TagPrefix.dust,Steel, 10)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 3)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 5)
                .outputItems(ModItems.INJECTOR.get().getDefaultInstance())
                .EUt(16)
                .duration(100)
                .save(provider);

// 培养皿
        CBRecipeBuilder.of(CTNHBio.id("vial_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 1)
                .outputItems(ModItems.VIAL.get().getDefaultInstance())
                .EUt(8)
                .duration(50)
                .save(provider);

// 精华提取器
        CBRecipeBuilder.of(CTNHBio.id("essence_extractor_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(20)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 20)
                .inputItems(TagPrefix.dust,Steel, 10)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 25)
                .inputItems(ModItems.EXOTIC_DUST.get().getDefaultInstance(), 4)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 6)
                .outputItems(ModItems.ESSENCE_EXTRACTOR.get().getDefaultInstance())
                .EUt(16)
                .duration(150)
                .save(provider);

//建筑方块类
// 菌光体（原版）
        CBRecipeBuilder.of(CTNHBio.id("shroomlight_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.BIO_LUMENS.get().getDefaultInstance(), 10)
                .inputItems(Items.YELLOW_DYE.getDefaultInstance(), 2)
                .inputItems(ModItems.ORGANIC_MATTER.get().getDefaultInstance(), 4)
                .outputItems(Items.SHROOMLIGHT.getDefaultInstance())
                .EUt(20)
                .duration(100)
                .save(provider);

// 培养皿支架
        CBRecipeBuilder.of(CTNHBio.id("vial_holder_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 8)
                .outputItems(ModItems.VIAL_HOLDER.get().getDefaultInstance())
                .EUt(8)
                .duration(40)
                .save(provider);
// 分解器
        CBRecipeBuilder.of(CTNHBio.id("decomposer_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .effect(MobEffects.SATURATION)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 5)
                .inputItems(TagPrefix.dust,Steel, 8)
                .outputItems(ModItems.DECOMPOSER.get().getDefaultInstance())
                .EUt(30)
                .duration(200)
                .save(provider);

// 消化器
        CBRecipeBuilder.of(CTNHBio.id("digester_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .effect(MobEffects.SATURATION)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 10)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 3)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 8)
                .inputFluids(new FluidStack(Bile.getFluid(), 400))
                .outputItems(ModItems.DIGESTER.get().getDefaultInstance())
                .EUt(30)
                .duration(200)
                .save(provider);

// 生物实验室
        CBRecipeBuilder.of(CTNHBio.id("bio_lab_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .effect(MobEffects.SATURATION)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 4)
                .inputItems(ModItems.EXOTIC_DUST.get().getDefaultInstance(), 2)
                .outputItems(ModItems.BIO_LAB.get().getDefaultInstance())
                .EUt(32)
                .duration(220)
                .save(provider);

// 生物锻造台
        CBRecipeBuilder.of(CTNHBio.id("bio_forge_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .effect(MobEffects.SATURATION)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 6)
                .inputItems(ModItems.EXOTIC_DUST.get().getDefaultInstance(), 2)
                .outputItems(ModItems.BIO_FORGE.get().getDefaultInstance())
                .EUt(32)
                .duration(220)
                .save(provider);

//电力分解者-LV
        CBRecipeBuilder.of(CTNHBio.id("lv_decomposer_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.SATURATION)
                .inputItems(ModItems.DECOMPOSER.get().getDefaultInstance())
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:lv_macerator_cb")))
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance(), 2)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:tin_single_cable_cb")),2)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:lv_fluid_regulator_cb")),2)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:lv_electric_pump_cb")),2)
                .outputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ctnhbio:lv_decomposer_cb")))
                .EUt(24)
                .duration(300)
                .save(provider);

//电力生物反应腔-LV
        CBRecipeBuilder.of(CTNHBio.id("lv_bioreactor_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.SATURATION)
                .inputItems(ModItems.BIO_LAB.get().getDefaultInstance())
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:lv_chemical_reactor_cb")))
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance(), 2)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:tin_single_cable_cb")),2)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:lv_fluid_regulator_cb")),2)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:lv_electric_pump_cb")),2)
                .outputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ctnhbio:lv_bioreactor_cb")))
                .EUt(24)
                .duration(300)
                .save(provider);

//电力生物消化器-LV
        CBRecipeBuilder.of(CTNHBio.id("lv_bioreactor_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.SATURATION)
                .inputItems(ModItems.DIGESTER.get().getDefaultInstance())
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:lv_brewery_cb")))
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance(), 2)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:tin_single_cable_cb")),2)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:lv_fluid_regulator_cb")),2)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:lv_electric_pump_cb")),2)
                .outputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ctnhbio:lv_digester_cb")))
                .EUt(24)
                .duration(300)
                .save(provider);
//脉络核心
        CBRecipeBuilder.of(CTNHBio.id("synet_core_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance())
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:aluminium_frame_cb")),1)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:copper_ring_cb")),1)
                .inputItems(CustomTags.HV_CIRCUITS,2)
                .inputItems(Items.GLOW_INK_SAC.getDefaultInstance(),2)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(),4)
                .inputFluids(new FluidStack(Regenerate_Fluid.getFluid(), 1000))
                .chancedOutput(new ItemStack(CBItems.SYNET_CORE),5000,0)
                .EUt(96)
                .duration(500)
                .save(provider);
//拓扑核心
        CBRecipeBuilder.of(CTNHBio.id("meta_core_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(50)
                .effect(MobEffects.REGENERATION)
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance())
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:stainless_steel_frame_cb")),1)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:electrum_ring_cb")),1)
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
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:titanium_frame_cb")),1)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("gtceu:black_steel_gear_cb")),1)
                .inputItems(CustomTags.IV_CIRCUITS,2)
                .inputItems(GTItems.QUANTUM_STAR.get(),2)
                .inputItems(TagPrefix.gemFlawless,Emerald,30)
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 1000))
                .inputFluids(new FluidStack(Healing_Compound.getFluid(), 1000))
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 1000))
                .chancedOutput(new ItemStack(CBItems.NOVA_CORE),3000,0)
                .EUt(1960)
                .duration(500)
                .save(provider);
//湿件配件
        CBRecipeBuilder.of(CTNHBio.id("wetware_resistor_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(3)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.PRIMORDIAL_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.dust,Graphene)
                .inputItems(TagPrefix.wireFine,Platinum,8)
                .inputItems(TagPrefix.wireFine,Tantalum,8)
                .inputFluids(new FluidStack(POLYPYRROLE.getFluid(), 144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_RESISTOR.get()), UniformInt.of(24, 36))
                .EUt(6144)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_capacitor_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(3)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(CBItems.SYNET_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.foil,Polybenzimidazole,2)
                .inputItems(TagPrefix.foil,Polycaprolactam,1)
                .inputItems(TagPrefix.foil,UraniumRhodiumDinaquadide,1)
                .inputFluids(new FluidStack(POLYPYRROLE.getFluid(), 144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_CAPACITOR.get()), UniformInt.of(16, 24))
                .EUt(6144)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_inductor_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(3)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(CBItems.META_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.ring,NaquadahAlloy,1)
                .inputItems(TagPrefix.ring,NickelZincFerrite,1)
                .inputItems(TagPrefix.wireFine,Platinum,4)
                .inputItems(TagPrefix.wireFine,Tantalum,4)
                .inputFluids(new FluidStack(POLYPYRROLE.getFluid(), 144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_INDUCTOR.get()), UniformInt.of(16, 24))
                .EUt(6144)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_diode_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(3)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(CBItems.NOVA_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.dust,IndiumGalliumPhosphide,1)
                .inputItems(TagPrefix.wireFine,YttriumBariumCuprate,8)
                .inputFluids(new FluidStack(POLYPYRROLE.getFluid(), 144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_DIODE.get()), UniformInt.of(16, 24))
                .EUt(6144)
                .duration(100)
                .save(provider);
        CBRecipeBuilder.of(CTNHBio.id("wetware_transistor_recipe_cb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(3)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(CBItems.OMNI_CORE.get().getDefaultInstance())
                .inputItems(TagPrefix.foil,Graphene,1)
                .inputItems(TagPrefix.foil,Osmiridium,1)
                .inputItems(TagPrefix.wireFine,Tantalum,8)
                .inputFluids(new FluidStack(POLYPYRROLE.getFluid(), 144))
                .outputItemsRanged(new ItemStack(CBItems.WETWARE_TRANSISTOR.get()), UniformInt.of(8, 16))
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
                .inputItems(TagPrefix.wireGtSingle,BIO_FLEXIBLE,4)
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
                .inputItems(CBItems.ADVANCED_RAM_CHIP.get().getDefaultInstance(),4)
                .inputItems(TagPrefix.wireGtSingle,BIO_FLEXIBLE,16)
                .inputFluids(new FluidStack(Ageing_Serum.getFluid(), 100))
                .inputFluids(new FluidStack(Genetic_Compound.getFluid(), 100))
                .outputItemsRanged(new ItemStack(GTItems.WETWARE_PROCESSOR_ASSEMBLY_ZPM.get()), UniformInt.of(1, 3))
                .EUt(6144)
                .duration(400)
                .save(provider);
    }
}
