package com.moguang.ctnhbio.mixin.hostilenetworks;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import dev.shadowsoffire.hostilenetworks.data.CachedModel;
import dev.shadowsoffire.hostilenetworks.data.DataModel;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import dev.shadowsoffire.hostilenetworks.util.Color;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.List;

import static dev.shadowsoffire.hostilenetworks.item.DataModelItem.getData;
import static dev.shadowsoffire.hostilenetworks.item.DataModelItem.getStoredModel;

@Mixin(value = DataModelItem.class)
public class DataModelItemMixin extends Item {

    public DataModelItemMixin(Properties properties) {
        super(properties);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> list, TooltipFlag pFlag) {
        CachedModel cModel = new CachedModel(pStack, 0);
        int data = getData(pStack);
        ModelTier tier = ModelTier.getByData(cModel.getModel(), data);
        int dProg = data - cModel.getTierData();
        int dMax = cModel.getNextTierData() - cModel.getTierData();
        if (tier != ModelTier.SELF_AWARE) {
            list.add(Component.translatable("hostilenetworks.info.data",
                    Component.translatable("hostilenetworks.info.dprog", dProg, dMax).withStyle(ChatFormatting.GRAY)));
            if (tier == ModelTier.FAULTY) {
                int dataPerKill = cModel.getDataPerKill();
                if (dataPerKill == 0) {
                    Component c1 = Component.literal("000 ").withStyle(ChatFormatting.GRAY, ChatFormatting.OBFUSCATED);
                    list.add(Component.translatable("hostilenetworks.info.dpk", c1).append(
                            Component.translatable("hostilenetworks.info.disabled").withStyle(ChatFormatting.RED)));
                } else {
                    list.add(Component.translatable("hostilenetworks.info.dpk",
                            Component.literal("" + cModel.getDataPerKill()).withStyle(ChatFormatting.GRAY)));
                }
            }
            List<EntityType<? extends LivingEntity>> subtypes = cModel.getModel().subtypes();
            if (!subtypes.isEmpty()) {
                list.add(Component.translatable("hostilenetworks.info.subtypes"));
                for (EntityType<?> t : subtypes) {
                    list.add(Component.translatable("hostilenetworks.info.sub_list", t.getDescription())
                            .withStyle(Style.EMPTY.withColor(Color.LIME)));
                }
            }
        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public Component getName(ItemStack pStack) {
        DynamicHolder<DataModel> model = getStoredModel(pStack);
        Component modelName;
        if (!model.isBound()) {
            modelName = Component.literal("BROKEN").withStyle(ChatFormatting.OBFUSCATED);
        } else modelName = model.get().name();

        var dataModel = model.get();
        if (dataModel != null) {
            int data = getData(pStack);
            ModelTier tier = ModelTier.getByData(dataModel, data);
            return Component.translatable(this.getDescriptionId(pStack), modelName)
                    .append("(")
                    .append(tier.getComponent())
                    .append(")");
        } else {
            return Component.translatable(this.getDescriptionId(pStack), modelName);
        }
    }
}
