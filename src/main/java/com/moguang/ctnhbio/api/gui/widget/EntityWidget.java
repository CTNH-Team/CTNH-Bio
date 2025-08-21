package com.moguang.ctnhbio.api.gui.widget;

import com.lowdragmc.lowdraglib.gui.ingredient.IRecipeIngredientSlot;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.jei.IngredientIO;
import com.lowdragmc.lowdraglib.jei.JEIPlugin;
import com.lowdragmc.lowdraglib.utils.Position;
import com.lowdragmc.lowdraglib.utils.Size;
import com.moguang.ctnhbio.integration.xei.handlers.entity.CycleEntityEntryHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EntityWidget extends Widget implements IRecipeIngredientSlot {
    //basics
    @Setter @Nullable
    CycleEntityEntryHandler cycle;
    @Setter
    int count = 1;
    @Setter
    float chance = 1;
    @Getter @Setter
    IngredientIO ingredientIO = IngredientIO.RENDER_ONLY;

    public EntityWidget(){
        super(new Position(0, 0), new Size(18, 18));
    }

    //Render
    private void renderEntityModel(@NotNull Entity entity,GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) {
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;


        //center
        int centerX = getPosition().x + 9;
        int centerY = getPosition().y + 16;

        //Reference to Create Metallurgy :: EntityIngredientRenderer
        PoseStack matrixStack = graphics.pose();
        matrixStack.pushPose();
        if (entity instanceof LivingEntity livingEntity) {
            int entityScale = Integer.max(getSizeHeight(),getSizeWidth());
            float maxSize = entity.getBbHeight() + entity.getBbWidth();
            entityScale = (int)((float)entityScale / maxSize);
            PoseStack modelView = RenderSystem.getModelViewStack();
            modelView.pushPose();
            modelView.mulPoseMatrix(matrixStack.last().pose());
            livingEntity.setYHeadRot(0.0F);
            InventoryScreen.renderEntityInInventoryFollowsMouse(graphics,centerX,centerY,entityScale,centerX-mouseX,centerY-mouseY,livingEntity);
            modelView.popPose();
            RenderSystem.applyModelViewMatrix();
        }
        else{

            float scale = calculateEntityScale(entity);
            matrixStack.translate(centerX, centerY, 50);
            matrixStack.scale(scale, -scale, scale); // 反转Y轴

            //rot
            matrixStack.mulPose(Axis.YP.rotationDegrees( (level.getGameTime() + partialTicks ) * 2));

            EntityRenderDispatcher dispatcher = mc.getEntityRenderDispatcher();
            MultiBufferSource.BufferSource buffer = graphics.bufferSource();
            dispatcher.render(entity, 0, 0, 0, 0, partialTicks, matrixStack, buffer, LightTexture.FULL_BRIGHT);
            buffer.endBatch();
            dispatcher.setRenderShadow(true);
        }

        matrixStack.popPose();

    }
    // 根据实体大小动态调整缩放
    private float calculateEntityScale(Entity entity) {
        float baseScale = 12.0f;
        AABB bb = entity.getBoundingBox();
        double size = Math.max(bb.getXsize(), Math.max(bb.getYsize(), bb.getZsize()));

        if (size > 2.0) {
            return baseScale / (float) (size * 0.8);
        }
        return baseScale;
    }

    @Override
    public void drawInBackground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        if(cycle==null || cycle.isEmpty()) return;
        var mc = Minecraft.getInstance();

        //render entity
        Entity entity = cycle.currentEntity(mc);
        if (entity == null) return;
        renderEntityModel(entity, graphics, partialTicks, mouseX, mouseY);
        entity.remove(Entity.RemovalReason.DISCARDED);

        // 绘制数量
        if (count > 1) {
            graphics.drawString(Minecraft.getInstance().font,
                    String.valueOf(count),
                    getPosition().x + 12, getPosition().y + 9, 0xFFFFFF, false);
        }

        // 绘制概率条
        if (chance < 1.0f) {
            int width = (int) (16 * chance);
            graphics.fill(getPosition().x + 1, getPosition().y + 17,
                    getPosition().x + 1 + width, getPosition().y + 18,
                    0xFF00FF00);
        }

    }

    @Override
    public void drawInForeground(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        if(isMouseOverElement(mouseX, mouseY) && getHoverElement(mouseX, mouseY) == this){
            if (gui != null && cycle!= null) {
                gui.getModularUIGui().setHoverTooltip(cycle.tooltips, ItemStack.EMPTY, null, null);
            }
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1f);
        }
    }

    @Override
    public List<Component> getFullTooltipTexts() {
        return cycle!= null? cycle.tooltips : List.of();
    }

    @Override
    public float getXEIChance() {
        return chance;
    }

    @Override
    //only for jei
    public List<Object> getXEIIngredients() {
        return cycle !=null ? cycle.getEntries().stream()
                                .map(this::mapToIngredient)
                                .collect(Collectors.toList())
                            : Collections.emptyList();
    }

    @Override
    public @Nullable Object getXEIIngredientOverMouse(double mouseX, double mouseY) {
        return self().isMouseOverElement(mouseX, mouseY) ? getXEICurrentIngredient() : null;
    }

    @Override
    public @Nullable Object getXEICurrentIngredient() {
        return cycle !=null ? mapToIngredient(cycle.currentType()) : null;
    }

    public static ItemStack getSpawnEgg(EntityType<?> type){
        return ForgeRegistries.ITEMS.getValues().stream()
                .filter(item -> item instanceof SpawnEggItem && ((SpawnEggItem) item).getType(null) == type)
                .findFirst()
                .map(ItemStack::new)
                .orElseGet(() -> {
                    ItemStack stack = new ItemStack(Items.EGG);
                    stack.setHoverName(type.getDescription());
                    return stack;
                });
    }
    private Object mapToIngredient(EntityType<?> type) {
        ItemStack egg = getSpawnEgg(type);
        return JEIPlugin.getItemIngredient(egg, getPosition().x, getPosition().y, getSize().width, getSize().height);
    }
}
