package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = MultiblockDisplayText.Builder.class, remap = false)
public abstract class MultiblockDisplayText$BuilderMixin {
    // @Final
    // @Shadow
    // private List<Component> textList;
    // @Inject(method = "addOutputLines",
    // at = @At(value = "INVOKE"
    // , target =
    // "Lcom/gregtechceu/gtceu/api/recipe/GTRecipe;getOutputContents(Lcom/gregtechceu/gtceu/api/capability/recipe/RecipeCapability;)Ljava/util/List;"
    // , ordinal = 1
    // , shift = At.Shift.AFTER),
    // locals = LocalCapture.CAPTURE_FAILHARD
    // )
    // void addModelOutputLine(GTRecipe recipe, CallbackInfoReturnable<MultiblockDisplayText.Builder> cir, int
    // recipeTier, int chanceTier, ChanceBoostFunction function, double maxDurationSec, List itemOutputs){
    // var modelOutputs = recipe.getOutputContents(ModelRecipeCapability.CAP);
    // for (var model : modelOutputs){
    // var stack = ((ModelIngredient)model.content).getModel();
    // int count = stack.getCount();
    // double countD = count;
    // if (model.chance < model.maxChance) {
    // countD = countD *
    // function.getBoostedChance(model, recipeTier, chanceTier) / model.maxChance;
    // count = countD < 1 ? 1 : (int) Math.round(countD);
    // }
    // if (count < maxDurationSec) {
    // String key = "gtceu.multiblock.output_line." + (model.chance < model.maxChance ? "2" : "0");
    // textList.add(Component.translatable(key, stack.getHoverName(), count,
    // FormattingUtil.formatNumber2Places(maxDurationSec / countD)));
    // } else {
    // String key = "gtceu.multiblock.output_line." + (model.chance < model.maxChance ? "3" : "1");
    // textList.add(Component.translatable(key, stack.getHoverName(), count,
    // FormattingUtil.formatNumber2Places(countD / maxDurationSec)));
    // }
    // }
    // }
}
