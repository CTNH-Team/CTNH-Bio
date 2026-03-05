package com.moguang.ctnhbio.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;
import tech.vixhentx.mcmod.ctnhlib.langprovider.Lang;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.CN;
import tech.vixhentx.mcmod.ctnhlib.langprovider.annotation.EN;

import java.util.List;

import static com.moguang.ctnhbio.data.recipe.CogniRecipeBuilder.COGNI_AESSEMBLY_STEP;

public class AssemblyStepItem extends Item {

    public AssemblyStepItem(Properties properties) {
        super(properties);
    }

    @CN("意识装配中间产物（当前步骤：%s）")
    @EN("Cogni Assembly Intermediate Product(Current Step: %s)")
    static Lang cogni_assemble_step;

    @CN("该物品必须通过联体桥输入或输出")
    @EN("This item must be input or output through a Parabiotic Bridge.")
    static Lang io_constrain;

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
                                TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        CompoundTag nbt = stack.getTag();
        if (nbt != null && nbt.contains(COGNI_AESSEMBLY_STEP)) {
            tooltipComponents.add(
                    cogni_assemble_step.translate(nbt.getInt(COGNI_AESSEMBLY_STEP)).withStyle(ChatFormatting.YELLOW));
            tooltipComponents.add(
                    io_constrain.translate().withStyle(ChatFormatting.DARK_RED));
        }
    }
}
