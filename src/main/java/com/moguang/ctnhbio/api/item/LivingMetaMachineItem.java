package com.moguang.ctnhbio.api.item;

import com.gregtechceu.gtceu.api.block.IMachineBlock;
import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class LivingMetaMachineItem extends MetaMachineItem implements GeoAnimatable {
    private final Supplier<GeoItemRenderer<LivingMetaMachineItem>> rendererFactory;
    public LivingMetaMachineItem(IMachineBlock block, Properties properties, Supplier<GeoItemRenderer<LivingMetaMachineItem>> rendererFactory) {
        super(block, properties);
        this.rendererFactory = rendererFactory;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return rendererFactory.get();
            }
        });
    }



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }
    protected final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }
}
