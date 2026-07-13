package com.moguang.ctnhbio.mixin.ali;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import com.yanny.ali.api.*;
import com.yanny.ali.compatibility.common.GenericUtils;
import com.yanny.ali.compatibility.emi.EmiBaseLoot;
import com.yanny.ali.compatibility.emi.EmiGameplayLoot;
import com.yanny.ali.platform.Services;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Mixin(value = EmiGameplayLoot.class, remap = false)
public abstract class EmiGamePlayLootMixin extends EmiBaseLoot {

    @Shadow
    @Final
    private ResourceLocation location;

    @Unique
    private EntityType<?> ctnhbio$entityType;

    public EmiGamePlayLootMixin(EmiRecipeCategory category, ResourceLocation id, IDataNode lootTable, int widgetX,
                                int widgetY, List<ItemStack> inputs, List<ItemStack> outputs) {
        super(category, id, lootTable, widgetX, widgetY, inputs, outputs);
    }

    @ModifyArg(
               method = "<init>",
               at = @At(value = "INVOKE",
                        target = "Lcom/yanny/ali/compatibility/emi/EmiBaseLoot;<init>(Ldev/emi/emi/api/recipe/EmiRecipeCategory;Lnet/minecraft/resources/ResourceLocation;Lcom/yanny/ali/api/IDataNode;IILjava/util/List;Ljava/util/List;)V"),
               index = 4)
    private static int modifyInit(EmiRecipeCategory category, ResourceLocation id, IDataNode lootTable, int widgetX,
                                  int widgetY, List<ItemStack> inputs, List<ItemStack> outputs) {
        return ctnhbio$isDespoil(id) ? 48 : widgetY;
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL"))
    private void injectInit(EmiRecipeCategory category, ResourceLocation id, IDataNode lootTable, List outputs,
                            CallbackInfo ci) {
        if (ctnhbio$isDespoil(id)) {
            ctnhbio$entityType = ctnhbio$tryGuessEntityTypeFromDespoilId(id);
            SpawnEggItem spawnEgg = Services.getPlatform().getSpawnEggItem(ctnhbio$entityType);
            if (spawnEgg != null) {
                catalysts.add(EmiStack.of(spawnEgg));
            }

        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public int getDisplayHeight() {
        return (ctnhbio$isDespoil(location) ? 48 : 10) + getItemsHeight();
    }

    // @Inject(method = "getRootWidget", at = @At("HEAD"), cancellable = true)
    // void hideEmpty(IWidgetUtils utils, IDataNode entry, RelativeRect rect, int maxWidth,
    // CallbackInfoReturnable<IWidget> cir){
    // if(ctnhbio$isDespoil(location) && ctnhbio$entityType == null){
    // cir.setReturnValue(null);
    // }
    // }

    @Inject(
            method = "getAdditionalWidgets",
            at = @At("HEAD"),
            cancellable = true)
    protected void getAdditionalWidgets(WidgetHolder widgetHolder, CallbackInfoReturnable<List<Widget>> cir) {
        if (!ctnhbio$isDespoil(location)) return;

        List<Widget> widgets = new LinkedList<>();
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null && ctnhbio$entityType != null) {
            int length = Minecraft.getInstance().font.width(ctnhbio$entityType.getDescription());

            widgets.add(new Widget() {

                private static final int WIDGET_SIZE = 36;
                final Bounds bounds = new Bounds((widgetHolder.getWidth() - WIDGET_SIZE) / 2, 10, WIDGET_SIZE,
                        WIDGET_SIZE);
                final Rect rect = new Rect(bounds.x(), bounds.y(), bounds.width(), bounds.height());
                final Entity entity = ctnhbio$entityType.create(level);

                @Override
                public Bounds getBounds() {
                    return bounds;
                }

                @Override
                public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
                    Level level = Minecraft.getInstance().level;

                    if (level != null) {
                        GenericUtils.renderEntity(entity, rect, widgetHolder.getWidth(), guiGraphics, mouseX, mouseY);
                    }
                }
            });
            widgets.add(new TextWidget(ctnhbio$entityType.getDescription().getVisualOrderText(),
                    (widgetHolder.getWidth() - length) / 2, 0, 0, false));
            catalysts.forEach((catalyst) -> widgets.add(new SlotWidget(catalyst, 0, 0)));
        }
        cir.setReturnValue(widgets);
    }

    @Unique
    private static boolean ctnhbio$isDespoil(ResourceLocation id) {
        return id.getPath().contains("despoil");
    }

    @Unique
    private static EntityType<?> ctnhbio$tryGuessEntityTypeFromDespoilId(ResourceLocation id) {
        if (id == null) return null;
        String namespace = id.getNamespace();
        String path = id.getPath();
        String[] parts = path.split("/");
        if (parts.length < 3) return null;
        String entityPath = String.join("/", Arrays.copyOfRange(parts, 2, parts.length));
        ResourceLocation entityId = ResourceLocation.tryBuild(namespace, entityPath);
        if (ForgeRegistries.ENTITY_TYPES.containsKey(entityId))
            return ForgeRegistries.ENTITY_TYPES.getValue(entityId);
        else
            return null;
    }
}
