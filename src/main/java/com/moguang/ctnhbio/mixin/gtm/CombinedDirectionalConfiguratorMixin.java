package com.moguang.ctnhbio.mixin.gtm;

import com.gregtechceu.gtceu.api.gui.widget.directional.CombinedDirectionalConfigurator;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.lowdragmc.lowdraglib.gui.widget.SceneWidget;
import com.lowdragmc.lowdraglib.utils.TrackedDummyWorld;
import com.moguang.ctnhbio.api.block.LivingMetaMachineBlock;
import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;
import com.moguang.ctnhbio.api.entity.LivingMetaMachineEntity;
import com.moguang.ctnhbio.api.machine.BasicLivingMachine;
import com.moguang.ctnhbio.registry.CBEntities;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 修改 CombinedDirectionalConfigurator 的场景渲染逻辑
 *
 * @author YourName
 */
@Mixin(value = CombinedDirectionalConfigurator.class, remap = false )
public abstract class CombinedDirectionalConfiguratorMixin {
//
//    @Final
//    @Shadow
//    private MetaMachine machine;
//
//    @Shadow
//    protected @Nullable Direction selectedSide;
//
//    @Inject(method = "createSceneWidget", at = @At(value = "TAIL"))
//    @OnlyIn(Dist.CLIENT)
//    private void onCreateSceneWidgetHead(CallbackInfoReturnable<SceneWidget> cir) {
//
//        if(machine instanceof BasicLivingMachine)
//        {
//            var sceneWidget = cir.getReturnValue();
//            TrackedDummyWorld dummyWorld = sceneWidget.getDummyWorld();
//            var entityType = ((LivingMetaMachineBlockEntity<?>)machine.holder).getEntityType();
//            var pos = ((LivingMetaMachineBlockEntity<?>)machine.holder).getEntityOffset();
//            var demoEntity = entityType.create(dummyWorld);
//            demoEntity.setPos(machine.getPos(), pos);
//            //demoEntity.noPhysics = true;
//            demoEntity.setBoundingBox(new AABB(0, 0, 0, 0, 0, 0));
//
//            dummyWorld.addFreshEntity(demoEntity);
//
//
//
//        }
//    }

//    @Invoker("onSideSelected")
//    abstract void invokeOnSideSelected(BlockPos pos, Direction side);
//
//
//
//    @Inject(method = "mouseClicked", at = @At("HEAD"))
//    @OnlyIn(Dist.CLIENT)
//    private void ensureSideSelection(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
//        // 强制刷新面选择状态
//        if (this.selectedSide != null) {
//            invokeOnSideSelected(machine.getPos(), this.selectedSide);
//        }
//    }

}
