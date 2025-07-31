package com.moguang.ctnhbio.data.recipe;

import com.github.elenterius.biomancy.crafting.EssenceIngredient;
import com.github.elenterius.biomancy.init.ModItems;
import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.registry.CBMachines;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.moguang.ctnhbio.data.materials.OrganicMaterials.*;

@SuppressWarnings("all")
public class BioelectrlcForgeRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        //生物锻炉
        //组件类
// 创造者混合物



        CBRecipeBuilder.of(CTNHBio.id("creator_mix"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
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
        CBRecipeBuilder.of(CTNHBio.id("fertilizer"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.NUTRIENTS.get().getDefaultInstance(), 30)
                .inputItems(ModItems.ORGANIC_MATTER.get().getDefaultInstance(), 4)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 4)
                .inputFluids(new FluidStack(Endocrine_Hormone.getFluid(), 600))
                .inputFluids(new FluidStack(Regenerate_Fluid.getFluid(), 400))
                .outputItems(ModItems.FERTILIZER.get().getDefaultInstance())
                .EUt(12)
                .duration(80)
                .save(provider);

// 生物尖牙
        CBRecipeBuilder.of(CTNHBio.id("mob_fang"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 6)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.STONE_POWDER.get().getDefaultInstance(), 1)
                .outputItems(ModItems.MOB_FANG.get().getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);

// 生物爪子
        CBRecipeBuilder.of(CTNHBio.id("mob_claw"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 7)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.STONE_POWDER.get().getDefaultInstance(), 1)
                .outputItems(ModItems.MOB_CLAW.get().getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);

// 骨头（原版）
        CBRecipeBuilder.of(CTNHBio.id("bone"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 1)
                .inputItems(ModItems.STONE_POWDER.get().getDefaultInstance(), 1)
                .outputItems(Items.BONE.getDefaultInstance())
                .EUt(8)
                .duration(50)
                .save(provider);

// 皮革（原版）
        CBRecipeBuilder.of(CTNHBio.id("leather"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 1)
                .outputItems(Items.LEATHER.getDefaultInstance())
                .EUt(8)
                .duration(50)
                .save(provider);

// 线（原版）
        CBRecipeBuilder.of(CTNHBio.id("string"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 2)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 1)
                .outputItems(Items.STRING.getDefaultInstance())
                .EUt(8)
                .duration(40)
                .save(provider);

// 蜘蛛网（原版）
        CBRecipeBuilder.of(CTNHBio.id("cobweb"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 4)
                .outputItems(Items.COBWEB.getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);

// 鳞甲（原版）
        CBRecipeBuilder.of(CTNHBio.id("scute"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 6)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 4)
                .inputItems(ModItems.STONE_POWDER.get().getDefaultInstance(), 1)
                .outputItems(Items.SCUTE.getDefaultInstance())
                .EUt(16)
                .duration(80)
                .save(provider);

// 鹦鹉螺壳（原版）
        CBRecipeBuilder.of(CTNHBio.id("nautilus_shell"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 11)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.STONE_POWDER.get().getDefaultInstance(), 1)
                .inputItems(ForgeRegistries.ITEMS.getValue(new ResourceLocation("ctnhbio:xxx")),1)
                .outputItems(Items.NAUTILUS_SHELL.getDefaultInstance())
                .EUt(24)
                .duration(100)
                .save(provider);

// 骷髅头颅（原版）
        CBRecipeBuilder.of(CTNHBio.id("skeleton_skull"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(4)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 50)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 9)
                .outputItems(Items.SKELETON_SKULL.getDefaultInstance())
                .EUt(32)
                .duration(150)
                .save(provider);

// 凋灵骷髅头颅（原版）
        CBRecipeBuilder.of(CTNHBio.id("wither_skeleton_skull"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(4)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 50)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 9)
                .inputFluids(new FluidStack(Wither_Slime.getFluid(), 1800))
                .outputItems(Items.WITHER_SKELETON_SKULL.getDefaultInstance())
                .EUt(32)
                .duration(150)
                .save(provider);

// 玩家头颅（原版）
        CBRecipeBuilder.of(CTNHBio.id("player_head"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
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
        CBRecipeBuilder.of(CTNHBio.id("piglin_head"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
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
        CBRecipeBuilder.of(CTNHBio.id("ravenous_claws"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(250)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.MOB_CLAW.get().getDefaultInstance(), 3)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 16)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 18)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 15)
                .outputItems(ModItems.RAVENOUS_CLAWS.get().getDefaultInstance())
                .EUt(30)
                .duration(200)
                .save(provider);

// 荆棘盾牌
        CBRecipeBuilder.of(CTNHBio.id("thorn_shield"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(250)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.MOB_FANG.get().getDefaultInstance(), 8)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 10)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 32)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 24)
                .outputItems(ModItems.THORN_SHIELD.get().getDefaultInstance())
                .EUt(30)
                .duration(200)
                .save(provider);

// 腐蚀枪刃
        CBRecipeBuilder.of(CTNHBio.id("caustic_gunblade"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(250)
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
        CBRecipeBuilder.of(CTNHBio.id("injector"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(5)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 20)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 10)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 3)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 5)
                .outputItems(ModItems.INJECTOR.get().getDefaultInstance())
                .EUt(16)
                .duration(100)
                .save(provider);

// 培养皿
        CBRecipeBuilder.of(CTNHBio.id("vial"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 1)
                .outputItems(ModItems.VIAL.get().getDefaultInstance())
                .EUt(8)
                .duration(50)
                .save(provider);

// 精华提取器
        CBRecipeBuilder.of(CTNHBio.id("essence_extractor"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(20)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 20)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 10)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 25)
                .inputItems(ModItems.EXOTIC_DUST.get().getDefaultInstance(), 4)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 6)
                .outputItems(ModItems.ESSENCE_EXTRACTOR.get().getDefaultInstance())
                .EUt(16)
                .duration(150)
                .save(provider);
        //杂项类
// 血肉压力板
        CBRecipeBuilder.of(CTNHBio.id("fleshkin_pressure_plate"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 3)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 4)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 10)
                .outputItems(ModItems.FLESHKIN_PRESSURE_PLATE.get().getDefaultInstance())
                .EUt(16)
                .duration(80)
                .save(provider);

// 模块化喉部
        CBRecipeBuilder.of(CTNHBio.id("modular_larynx"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.GEM_FRAGMENTS.get().getDefaultInstance(), 4)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 6)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 12)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 16)
                .outputItems(ModItems.MODULAR_LARYNX.get().getDefaultInstance())
                .EUt(24)
                .duration(120)
                .save(provider);

// 存储囊
        CBRecipeBuilder.of(CTNHBio.id("storage_sac"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 4)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 6)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 12)
                .inputItems(ModItems.GEM_FRAGMENTS.get().getDefaultInstance(), 4)
                .outputItems(ModItems.STORAGE_SAC.get().getDefaultInstance())
                .EUt(16)
                .duration(100)
                .save(provider);

// 血肉箱
        CBRecipeBuilder.of(CTNHBio.id("fleshkin_chest"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 10)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 12)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 8)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 32)
                .outputItems(ModItems.FLESHKIN_CHEST.get().getDefaultInstance())
                .EUt(24)
                .duration(150)
                .save(provider);
// 蛹
        CBRecipeBuilder.of(CTNHBio.id("chrysalis"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.TOUGH_FIBERS.get().getDefaultInstance(), 6)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 12)
                .inputItems(Items.EGG.getDefaultInstance(), 4)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .outputItems(ModItems.CHRYSALIS.get().getDefaultInstance())
                .EUt(20)
                .duration(120)
                .save(provider);
//建筑方块类
// 肌腱链条
        CBRecipeBuilder.of(CTNHBio.id("tendon_chain"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 8)
                .outputItems(ModItems.TENDON_CHAIN.get().getDefaultInstance())
                .EUt(8)
                .duration(40)
                .save(provider);
// 黄色生物灯笼
        CBRecipeBuilder.of(CTNHBio.id("yellow_bio_lantern"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.BIO_LUMENS.get().getDefaultInstance(), 10)
                .inputItems(Items.YELLOW_DYE.getDefaultInstance(), 1)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 2)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 4)
                .outputItems(ModItems.YELLOW_BIO_LANTERN.get().getDefaultInstance())
                .EUt(16)
                .duration(80)
                .save(provider);

// 蓝色生物灯笼
        CBRecipeBuilder.of(CTNHBio.id("blue_bio_lantern"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.BIO_LUMENS.get().getDefaultInstance(), 10)
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 2)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 4)
                .outputItems(ModItems.BLUE_BIO_LANTERN.get().getDefaultInstance())
                .EUt(16)
                .duration(80)
                .save(provider);

// 菌光体（原版）
        CBRecipeBuilder.of(CTNHBio.id("shroomlight"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.BIO_LUMENS.get().getDefaultInstance(), 10)
                .inputItems(Items.YELLOW_DYE.getDefaultInstance(), 2)
                .inputItems(ModItems.ORGANIC_MATTER.get().getDefaultInstance(), 4)
                .outputItems(Items.SHROOMLIGHT.getDefaultInstance())
                .EUt(20)
                .duration(100)
                .save(provider);

// 培养皿支架
        CBRecipeBuilder.of(CTNHBio.id("vial_holder"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 8)
                .outputItems(ModItems.VIAL_HOLDER.get().getDefaultInstance())
                .EUt(8)
                .duration(40)
                .save(provider);

// 吉祥物旗帜图案
        CBRecipeBuilder.of(CTNHBio.id("mascot_banner_patterns"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.ORGANIC_MATTER.get().getDefaultInstance(), 8)
                .inputItems(Items.SPIDER_EYE.getDefaultInstance(), 1)
                .inputFluids(new FluidStack(Bile.getFluid(), 400))
                .outputItems(ModItems.MASCOT_BANNER_PATTERNS.get().getDefaultInstance())
                .EUt(12)
                .duration(60)
                .save(provider);
// 分解器
        CBRecipeBuilder.of(CTNHBio.id("decomposer"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.MINERAL_FRAGMENT.get().getDefaultInstance(), 8)
                .outputItems(ModItems.DECOMPOSER.get().getDefaultInstance())
                .EUt(30)
                .duration(200)
                .save(provider);

// 消化器
        CBRecipeBuilder.of(CTNHBio.id("digester"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
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
        CBRecipeBuilder.of(CTNHBio.id("bio_lab"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
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
        CBRecipeBuilder.of(CTNHBio.id("bio_forge"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 8)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 6)
                .inputItems(ModItems.EXOTIC_DUST.get().getDefaultInstance(), 2)
                .outputItems(ModItems.BIO_FORGE.get().getDefaultInstance())
                .EUt(32)
                .duration(220)
                .save(provider);

// 舌头
        CBRecipeBuilder.of(CTNHBio.id("tongue"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 3)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 10)
                .outputItems(ModItems.TONGUE.get().getDefaultInstance())
                .EUt(24)
                .duration(150)
                .save(provider);

// 巨口漏斗
        CBRecipeBuilder.of(CTNHBio.id("maw_hopper"), CBRecipeTypes.BIOELECTRIC_FORGE_RECIPES)
                .nutrient(1)
                .inputItems(ModItems.LIVING_FLESH.get().getDefaultInstance())
                .inputItems(ModItems.FLESH_BITS.get().getDefaultInstance(), 5)
                .inputItems(ModItems.BONE_FRAGMENTS.get().getDefaultInstance(), 3)
                .inputItems(ModItems.ELASTIC_FIBERS.get().getDefaultInstance(), 10)
                .outputItems(ModItems.MAW_HOPPER.get().getDefaultInstance())
                .EUt(24)
                .duration(150)
                .save(provider);


    }
}
