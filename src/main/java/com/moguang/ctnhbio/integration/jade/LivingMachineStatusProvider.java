package com.moguang.ctnhbio.integration.jade;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.integration.jade.provider.CapabilityBlockProvider;
import com.moguang.ctnhbio.api.ILivingMachine;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.theme.IThemeHelper;
import snownee.jade.api.ui.BoxStyle;
import snownee.jade.api.ui.IElementHelper;
import snownee.jade.impl.ui.HealthElement;

import java.util.Collection;

import static snownee.jade.addon.vanilla.StatusEffectsProvider.getEffectName;

public class LivingMachineStatusProvider extends CapabilityBlockProvider<ILivingMachine> {


    public LivingMachineStatusProvider() {
        super(GTCEu.id("living_machine_status_provider"));
    }

    @Nullable
    @Override
    protected ILivingMachine getCapability(Level level, BlockPos pos, @Nullable Direction side) {
        //return GTCapabilityHelper.getControllable(level, pos, side);
        if(BasicLivingMachine.getMachine(level, pos) instanceof ILivingMachine machine)
            return machine;
        else
            return null;
    }

    @Override
    protected void write(CompoundTag data, ILivingMachine machine) {
        var maxHealth = machine.getMachineEntity().getMaxHealth();
        var health = machine.getMachineEntity().getHealth();
        data.putFloat("MaxHealth", maxHealth);
        data.putFloat("Health", health);

        var nutrientAmount = machine.getNutrientAmount();
        var nutrientCapacity = machine.getNutrientCapacity();
        data.putDouble("NutrientAmount", nutrientAmount);
        data.putDouble("NutrientCapacity", nutrientCapacity);

        LivingEntity living = machine.getMachineEntity();
        Collection<MobEffectInstance> effects = living.getActiveEffects();
        if (!effects.isEmpty()) {
            ListTag list = new ListTag();

            for(MobEffectInstance effect : effects) {
                CompoundTag compound = new CompoundTag();
                compound.putString("Name", Component.Serializer.toJson(getEffectName(effect)));
                if (effect.isInfiniteDuration()) {
                    compound.putBoolean("Infinite", true);
                } else {
                    compound.putInt("Duration", effect.getDuration());
                }

                compound.putBoolean("Bad", effect.getEffect().getCategory() == MobEffectCategory.HARMFUL);
                list.add(compound);
            }

            data.put("StatusEffects", list);
        }
        //data.putBoolean("WorkingEnabled", machine.isWorkingEnabled());
    }

    @Override
    protected void addTooltip(CompoundTag capData, ITooltip tooltip, Player player, BlockAccessor block,
                              BlockEntity blockEntity, IPluginConfig config) {
        if (capData.contains("MaxHealth") && capData.contains("Health")) {
            float maxHealth = capData.getFloat("MaxHealth");
            float health = capData.getFloat("Health");
            tooltip.add(new HealthElement(maxHealth, health));
        }
        if (capData.contains("NutrientAmount") && capData.contains("NutrientCapacity")) {
            double nutrientAmount = capData.getDouble("NutrientAmount");
            double nutrientCapacity = capData.getDouble("NutrientCapacity");
            tooltip.add(new NutrientElement(nutrientCapacity, nutrientAmount));
        }
        if (capData.contains("StatusEffects")) {
            IElementHelper helper = IElementHelper.get();
            ITooltip box = helper.tooltip();
            ListTag list = capData.getList("StatusEffects", 10);
            Component[] lines = new Component[list.size()];

            for(int i = 0; i < lines.length; ++i) {
                CompoundTag compound = list.getCompound(i);
                MutableComponent name = Component.Serializer.fromJsonLenient(compound.getString("Name"));
                if (name != null) {
                    String duration;
                    if (compound.getBoolean("Infinite")) {
                        duration = I18n.get("effect.duration.infinite", new Object[0]);
                    } else {
                        duration = StringUtil.formatTickDuration(compound.getInt("Duration"));
                    }

                    MutableComponent s = Component.translatable("jade.potion", new Object[]{name, duration});
                    IThemeHelper t = IThemeHelper.get();
                    box.add(compound.getBoolean("Bad") ? t.danger(s) : t.success(s));
                }
            }

            tooltip.add(helper.box(box, BoxStyle.DEFAULT));
        }
        if(MetaMachine.getMachine(blockEntity.getLevel(), block.getPosition()) instanceof ILivingMachine machine)
        {


        }
//        if (capData.contains("WorkingEnabled") && !capData.getBoolean("WorkingEnabled")) {
//            tooltip.add(Component.translatable("gtceu.top.working_disabled").withStyle(ChatFormatting.YELLOW));
//        }
    }
}