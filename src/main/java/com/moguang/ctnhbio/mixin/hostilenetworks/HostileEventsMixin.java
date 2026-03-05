package com.moguang.ctnhbio.mixin.hostilenetworks;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import dev.shadowsoffire.hostilenetworks.HostileEvents;
import dev.shadowsoffire.hostilenetworks.data.DataModel;
import dev.shadowsoffire.hostilenetworks.data.ModelTier;
import dev.shadowsoffire.hostilenetworks.item.DataModelItem;
import dev.shadowsoffire.hostilenetworks.item.DeepLearnerItem;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = HostileEvents.class, remap = false)
public class HostileEventsMixin {

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void updateModels(ItemStack learner, EntityType<?> type, int bonus) {
        ItemStackHandler handler = DeepLearnerItem.getItemHandler(learner);

        for (int i = 0; i < 4; ++i) {
            ItemStack model = handler.getStackInSlot(i);
            if (!model.isEmpty()) {

                DynamicHolder<DataModel> dModel = DataModelItem.getStoredModel(model);
                int data = DataModelItem.getData(model);
                ModelTier tier = ModelTier.getByData(dModel, data);
                if (tier != ModelTier.FAULTY) continue;
                if (dModel.isBound() && ((DataModel) dModel.get()).type() == type ||
                        ((DataModel) dModel.get()).subtypes().contains(type)) {
                    DataModelItem.setData(model, data + ((DataModel) dModel.get()).getDataPerKill(tier) + bonus);
                }
            }
        }

        DeepLearnerItem.saveItems(learner, handler);
    }
}
