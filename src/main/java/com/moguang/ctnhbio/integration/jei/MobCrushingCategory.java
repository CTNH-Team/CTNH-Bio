package com.moguang.ctnhbio.integration.jei;

import com.moguang.ctnhbio.CTNHBio;
import com.moguang.ctnhbio.api.recipe.CBRecipeType;
import com.moguang.ctnhbio.common.recipe.MobCrushingRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.compat.jei.EmptyBackground;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedCrushingWheels;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import fr.lucreeper74.createmetallurgy.compat.jei.CMJeiConstants;
import fr.lucreeper74.createmetallurgy.compat.jei.category.entity.EntityIngredientRenderer;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.createmod.catnip.layout.LayoutHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@ParametersAreNonnullByDefault
public class MobCrushingCategory implements IRecipeCategory<MobCrushingRecipe> {

    private static final IDrawable BASIC_SLOT = asDrawable(AllGuiTextures.JEI_SLOT);
    private static final IDrawable CHANCE_SLOT = asDrawable(AllGuiTextures.JEI_CHANCE_SLOT);
    private final AnimatedCrushingWheels crushingWheels = new AnimatedCrushingWheels();

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable slotDrawable;
    private final Component title;

    public MobCrushingCategory(IGuiHelper guiHelper) {
        this.background = new EmptyBackground(177, 100);

        // 图标（粉碎轮物品）
        this.icon = guiHelper.createDrawableIngredient(
                VanillaTypes.ITEM_STACK,
                new ItemStack(Items.IRON_INGOT) // 替换为你的粉碎轮物品
        );

        // 输入/输出槽样式
        this.slotDrawable = guiHelper.getSlotDrawable();

        // 标题
        this.title = Component.translatable("jei.ctnhbio.mob_crushing");
    }

    @Override
    public RecipeType<MobCrushingRecipe> getRecipeType() {
        return CBRecipeType.MOB_CRUSHING;
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @SuppressWarnings({"Deprecated", "removal"})
    @Override
    public @Nullable IDrawable getBackground() {
        return background;
    }


    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MobCrushingRecipe recipe, IFocusGroup focuses) {
        // 输入槽：生物 Spawn Egg 或自定义图标
        builder.addSlot(RecipeIngredientRole.INPUT, 2, 2)
                .addItemStack(getEntityIcon(recipe.getEntityType()))
                .setStandardSlotBackground();

        builder.addSlot(RecipeIngredientRole.INPUT, 75, -2)
                .setCustomRenderer(CMJeiConstants.ENTITY_TYPE, new EntityIngredientRenderer(35))
                .addIngredient(CMJeiConstants.ENTITY_TYPE, recipe.getDisplay());

        // 输出槽：粉碎产物（支持多输出）
        layoutOutput(recipe).forEach(layoutEntry -> builder
                .addSlot(RecipeIngredientRole.OUTPUT, 92 + layoutEntry.posX(), 86 + layoutEntry.posY())
                .setBackground(CHANCE_SLOT, -1, -1)
                .addItemStack(layoutEntry.output().getStack())
                .addRichTooltipCallback((slotView, tooltip) -> {
                    tooltip.add(Component.translatable("jei.ctnhbio.tooltip.chance")
                            .append(Component.literal(String.format(" %.0f%%", recipe.getChance() * 100))));

                    tooltip.add(Component.translatable("jei.ctnhbio.tooltip.amount_range",
                            recipe.getMinAmount(),
                            recipe.getMaxAmount()));
                })
        );
    }

    @Override
    public void draw(MobCrushingRecipe recipe, IRecipeSlotsView slots, GuiGraphics graphics, double mouseX, double mouseY) {
        PoseStack pose = graphics.pose();

        var shadow = AllGuiTextures.JEI_SHADOW;

        PoseStack poseStack = graphics.pose();
        poseStack.pushPose();
        poseStack.translate(46, 20, 0); // 定位到目标坐标
        poseStack.scale(0.5f, 0.5f, 1.0f); // 应用缩放

        // 原始纹理绘制（坐标归零，因为已通过translate定位）
        graphics.blit(shadow.location, 0, 0, shadow.getStartX(), shadow.getStartY(), shadow.getWidth(), shadow.getHeight());

        poseStack.popPose();


        // 绘制 Create 风格的箭头动画
        AllGuiTextures.JEI_DOWN_ARROW.render(graphics, 72, 7);

        // 绘制粉碎轮动画
        crushingWheels.draw(graphics, 62, 59);

    }

    // 获取生物图标（Spawn Egg 或备用方案）
    private ItemStack getEntityIcon(EntityType<?> entityType) {
        return ForgeRegistries.ITEMS.getValues().stream()
                .filter(item -> item instanceof SpawnEggItem && ((SpawnEggItem) item).getType(null) == entityType)
                .findFirst()
                .map(ItemStack::new)
                .orElseGet(() -> {
                    ItemStack stack = new ItemStack(Items.PAPER);
                    stack.setHoverName(Component.translatable(entityType.getDescriptionId()));
                    return stack;
                });
    }

    // 复用 Create 的输出布局逻辑
    private List<LayoutEntry> layoutOutput(MobCrushingRecipe recipe) {
        List<LayoutEntry> positions = new ArrayList<>();
        LayoutHelper layout = LayoutHelper.centeredHorizontal(1, 1, 18, 18, 1);
        positions.add(new LayoutEntry(
                new ProcessingOutput(recipe.getResult().copy(), recipe.getChance()),
                layout.getX(), layout.getY()
        ));
        return positions;
    }

    private record LayoutEntry(ProcessingOutput output, int posX, int posY) {}

    protected static IDrawable asDrawable(AllGuiTextures texture) {
        return new IDrawable() {
            @Override
            public int getWidth() {
                return texture.getWidth();
            }

            @Override
            public int getHeight() {
                return texture.getHeight();
            }

            @Override
            public void draw(GuiGraphics graphics, int xOffset, int yOffset) {
                texture.render(graphics, xOffset, yOffset);
            }
        };
    }
}
