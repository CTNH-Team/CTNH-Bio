package com.moguang.ctnhbio.common.serum;

import com.github.elenterius.biomancy.api.serum.Serum;
import com.github.elenterius.biomancy.init.ModEntityTypes;
import com.github.elenterius.biomancy.init.ModSerums;
import com.github.elenterius.biomancy.serum.BasicSerum;
import com.moguang.ctnhbio.event.TransformManager;
import com.moguang.ctnhbio.registry.CBSerums;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class PrimordialSerum extends BasicSerum {
    public PrimordialSerum(int color) {
        super(color);
    }

    @Override
    public boolean canAffectPlayerSelf(CompoundTag tag, Player targetSelf) {
        return false;
    }

    @Override
    public boolean canAffectEntity(CompoundTag tag, @Nullable LivingEntity source, LivingEntity target) {
        return target.getType() == ModEntityTypes.FLESH_BLOB.get();
    }

    @Override
    public void affectEntity(ServerLevel level, CompoundTag tag, @Nullable LivingEntity source, LivingEntity target) {
        if(target.getType() == ModEntityTypes.FLESH_BLOB.get()){
            TransformManager.addEntity(target);
        }
    }

    @Override
    public void affectPlayerSelf(CompoundTag tag, ServerPlayer targetSelf) {

    }

    @Override
    public String getNameTranslationKey() {
        return Serum.makeTranslationKey(Objects.requireNonNull(CBSerums.REGISTRY.get().getKey(this)));
    }

    @Override
    public String toString() {
        return "Serum{name=%s, color=%s}".formatted(CBSerums.REGISTRY.get().getKey(this), Integer.toHexString(getColor()));
    }
}
