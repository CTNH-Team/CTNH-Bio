package com.moguang.ctnhbio.api;

import net.minecraft.world.entity.LivingEntity;

/**
 * 让任意生物实现此接口以支持宿主绑定
 */
public interface IHostAwareEntity {
    /** 获取宿主 */
    ILivingEntityHost<?> getHost();

    /** 设置宿主 */
    void setHost(ILivingEntityHost<?> host);

    /** 默认实现：通过PersistentData存储宿主位置 */
    default void bindToHost(ILivingEntityHost<?> host) {
        setHost(host);
//        if (this instanceof LivingEntity living) {
//            living.getPersistentData().putLong("HostID", host.getHostPos().asLong());
//        }
    }
}
