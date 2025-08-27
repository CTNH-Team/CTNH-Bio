package com.moguang.ctnhbio.api.gui;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.WidgetUtils;
import com.gregtechceu.gtceu.api.gui.editor.IEditableUI;
import com.gregtechceu.gtceu.api.gui.widget.DualProgressWidget;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.category.GTRecipeCategory;
import com.gregtechceu.gtceu.api.recipe.ui.GTRecipeTypeUI;
import com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeEMICategory;
import com.gregtechceu.gtceu.integration.jei.recipe.GTRecipeJEICategory;
import com.gregtechceu.gtceu.integration.rei.recipe.GTRecipeREICategory;
import com.lowdragmc.lowdraglib.gui.editor.configurator.IConfigurableWidget;
import com.lowdragmc.lowdraglib.gui.editor.data.Resources;
import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.lowdragmc.lowdraglib.gui.widget.ButtonWidget;
import com.lowdragmc.lowdraglib.gui.widget.ProgressWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.jei.JEIPlugin;
import com.lowdragmc.lowdraglib.utils.Position;
import com.moguang.ctnhbio.registry.CBRecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CBRecipeTypeUI extends GTRecipeTypeUI {
    public GTRecipeType gtRecipeType;
    public CBRecipeTypeUI(@NotNull GTRecipeType recipeType) {
        super(recipeType);
        gtRecipeType = recipeType;
    }

    @Override
    protected WidgetGroup addInventorySlotGroup(boolean isOutputs, boolean isSteam, boolean isHighPressure) {
        int maxCount = 0;
        int totalR = 0;
        TreeMap<RecipeCapability<?>, Integer> map = new TreeMap<>(RecipeCapability.COMPARATOR);
        if (isOutputs) {
            for (var value : gtRecipeType.maxOutputs.entrySet()) {
                if (value.getKey().doRenderSlot) {
                    int val = value.getValue();
                    if (val > maxCount) {
                        maxCount = Math.min(val, 3);
                    }
                    totalR += (val + 2) / 3;
                    map.put(value.getKey(), val);
                }
            }
        } else {
            for (var value : gtRecipeType.maxInputs.entrySet()) {
                if (value.getKey().doRenderSlot) {
                    int val = value.getValue();
                    if (val > maxCount) {
                        maxCount = Math.min(val, 3);
                    }
                    totalR += (val + 2) / 3;
                    map.put(value.getKey(), val);
                }
            }
        }
        WidgetGroup group = new WidgetGroup(0, 0, maxCount * 18 + 8, totalR * 18 + 8);
        int index = 0;
        for (var entry : map.entrySet()) {
            RecipeCapability<?> cap = entry.getKey();
            if (cap.getWidgetClass() == null) {
                continue;
            }
            int capCount = entry.getValue();
            for (int slotIndex = 0; slotIndex < capCount; slotIndex++) {
                var slot = cap.createWidget().setBackground(CBGuiTextures.SLOT_BIO);
                slot.setSelfPosition(new Position((index % 3) * 18 + 4, (index / 3) * 18 + 4));
                slot.setBackground(
                        getOverlaysForSlot(isOutputs, cap, slotIndex == capCount - 1, isSteam, isHighPressure));
                slot.setId(cap.slotName(isOutputs ? IO.OUT : IO.IN, slotIndex));
                group.addWidget(slot);
                index++;
            }
            // move to new row
            index += (3 - (index % 3)) % 3;
        }
        return group;
    }

    @Override
    protected IGuiTexture getOverlaysForSlot(boolean isOutput, RecipeCapability<?> capability, boolean isLast, boolean isSteam, boolean isHighPressure) {
        IGuiTexture base = capability == FluidRecipeCapability.CAP ? GuiTextures.FLUID_SLOT : (isSteam ? GuiTextures.SLOT_STEAM.get(isHighPressure) : CBGuiTextures.SLOT_BIO);
        byte overlayKey = (byte)((isOutput ? 2 : 0) + (capability == FluidRecipeCapability.CAP ? 1 : 0) + (isLast ? 4 : 0));
        return (IGuiTexture)(this.getSlotOverlays().containsKey(overlayKey) ? new GuiTextureGroup(new IGuiTexture[]{(IGuiTexture)base, (IGuiTexture)this.getSlotOverlays().get(overlayKey)}) : base);
    }

    @Override
    public IEditableUI<WidgetGroup, RecipeHolder> createEditableUITemplate(boolean isSteam, boolean isHighPressure) {
        return new IEditableUI.Normal<>(() -> {
            var isCustomUI = !isSteam && hasCustomUI();
            if (isCustomUI) {
                CompoundTag nbt = getCustomUI();
                WidgetGroup group = new WidgetGroup();
                IConfigurableWidget.deserializeNBT(group, nbt.getCompound("root"),
                        Resources.fromNBT(nbt.getCompound("resources")), false);
                group.setSelfPosition(new Position(0, 0));
                return group;
            }

            var inputs = addInventorySlotGroup(false, isSteam, isHighPressure);
            var outputs = addInventorySlotGroup(true, isSteam, isHighPressure);
            var maxWidth = Math.max(inputs.getSize().width, outputs.getSize().width);
            var group = new WidgetGroup(0, 0, 2 * maxWidth + 40,
                    Math.max(inputs.getSize().height, outputs.getSize().height));
            var size = group.getSize();

            inputs.addSelfPosition((maxWidth - inputs.getSize().width) / 2,
                    (size.height - inputs.getSize().height) / 2);
            outputs.addSelfPosition(maxWidth + 40 + (maxWidth - outputs.getSize().width) / 2,
                    (size.height - outputs.getSize().height) / 2);
            group.addWidget(inputs);
            group.addWidget(outputs);

            var progressWidget = new ProgressWidget(ProgressWidget.JEIProgress, maxWidth + 10, size.height / 2 - 10, 20,
                    20, getProgressBarTexture());
            progressWidget.setId("progress");
            group.addWidget(progressWidget);

            progressWidget.setProgressTexture(getProgressBarTexture());

            return group;
        }, (template, recipeHolder) -> {
            var isJEI = recipeHolder.progressSupplier() == ProgressWidget.JEIProgress;

            // bind progress
            List<Widget> progress = new ArrayList<>();
            // First set the progress suppliers separately.
            WidgetUtils.widgetByIdForEach(template, "^progress$", ProgressWidget.class, progressWidget -> {
                progressWidget.setProgressSupplier(recipeHolder.progressSupplier());
                progress.add(progressWidget);
            });
            // Then set the dual-progress widgets, to override their builtin ones' suppliers, in case someone forgot to
            // remove the id from the internal ones.
            WidgetUtils.widgetByIdForEach(template, "^progress$", DualProgressWidget.class, dualProgressWidget -> {
                dualProgressWidget.setProgressSupplier(recipeHolder.progressSupplier());
                progress.add(dualProgressWidget);
            });
            // add recipe button
            if (!isJEI && (GTCEu.Mods.isREILoaded() || GTCEu.Mods.isJEILoaded() || GTCEu.Mods.isEMILoaded())) {
                for (Widget widget : progress) {
                    template.addWidget(new ButtonWidget(widget.getPosition().x, widget.getPosition().y,
                            widget.getSize().width, widget.getSize().height, IGuiTexture.EMPTY, cd -> {
                        if (cd.isRemote) {
                            if (GTCEu.Mods.isJEILoaded()) {
                                JEIPlugin.jeiRuntime.getRecipesGui().showTypes(
                                        Stream.concat(
                                                gtRecipeType.getCategories().stream()
                                                        .filter(GTRecipeCategory::isXEIVisible)
                                                        .map(GTRecipeJEICategory::machineType),
                                                Stream.of(new RecipeType<>(CBRecipeTypes.BASIC_LIVING_RECIPES.getCategory().registryKey, GTRecipe.class))
                                        ).collect(Collectors.toList())
                                );
                            }
                        }
                    }).setHoverTooltips("gtceu.recipe_type.show_recipes"));
                }
            }

            // Bind I/O
            for (var capabilityEntry : recipeHolder.storages().rowMap().entrySet()) {
                IO io = capabilityEntry.getKey();
                for (var storagesEntry : capabilityEntry.getValue().entrySet()) {
                    RecipeCapability<?> cap = storagesEntry.getKey();
                    Object storage = storagesEntry.getValue();
                    // bind overlays
                    Class<? extends Widget> widgetClass = cap.getWidgetClass();
                    if (widgetClass != null) {
                        WidgetUtils.widgetByIdForEach(template, "^%s_[0-9]+$".formatted(cap.slotName(io)), widgetClass,
                                widget -> {
                                    var index = WidgetUtils.widgetIdIndex(widget);
                                    cap.applyWidgetInfo(widget, index, isJEI, io, recipeHolder, gtRecipeType, null, null,
                                            storage, 0, 0);
                                });
                    }
                }
            }
        });
    }
}
