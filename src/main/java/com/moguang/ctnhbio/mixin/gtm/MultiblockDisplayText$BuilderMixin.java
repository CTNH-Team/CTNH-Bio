package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockDisplayText;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.chance.boost.ChanceBoostFunction;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.moguang.ctnhbio.api.capability.recipe.ModelRecipeCapability;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(value = MultiblockDisplayText.Builder.class, remap = false)
public abstract class MultiblockDisplayText$BuilderMixin {
    @Final
    @Shadow
    private List<Component> textList;
    @Inject(method = "addOutputLines",
            at = @At(value = "INVOKE"
                    , target = "Lcom/gregtechceu/gtceu/api/recipe/GTRecipe;getOutputContents(Lcom/gregtechceu/gtceu/api/capability/recipe/RecipeCapability;)Ljava/util/List;"
                    , ordinal = 1
                    , shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    void addModelOutputLine(GTRecipe recipe, CallbackInfoReturnable<MultiblockDisplayText.Builder> cir, int recipeTier, int chanceTier, ChanceBoostFunction function, double maxDurationSec, List itemOutputs){
        var modelOutputs = recipe.getOutputContents(ModelRecipeCapability.CAP);
        for (var model : modelOutputs){
            var stack = ModelRecipeCapability.CAP.of(model.content).getItems()[0];
            int count = stack.getCount();
            double countD = count;
            if (model.chance < model.maxChance) {
                countD = countD *
                        function.getBoostedChance(model, recipeTier, chanceTier) / model.maxChance;
                count = countD < 1 ? 1 : (int) Math.round(countD);
            }
            if (count < maxDurationSec) {
                String key = "gtceu.multiblock.output_line." + (model.chance < model.maxChance ? "2" : "0");
                textList.add(Component.translatable(key, stack.getHoverName(), count,
                        FormattingUtil.formatNumber2Places(maxDurationSec / countD)));
            } else {
                String key = "gtceu.multiblock.output_line." + (model.chance < model.maxChance ? "3" : "1");
                textList.add(Component.translatable(key, stack.getHoverName(), count,
                        FormattingUtil.formatNumber2Places(countD / maxDurationSec)));
            }
        }
    }
}
