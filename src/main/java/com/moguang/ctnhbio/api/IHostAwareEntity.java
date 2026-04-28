package com.moguang.ctnhbio.api;

import com.moguang.ctnhbio.api.blockentity.LivingMetaMachineBlockEntity;

/**
 * 让任意生物实现此接口以支持宿主绑定
 */
public interface IHostAwareEntity {

    /** 获取宿主 */
    LivingMetaMachineBlockEntity getHost();

    /** 设置宿主 */
    void setHost(LivingMetaMachineBlockEntity host);

    /** 默认实现：通过PersistentData存储宿主位置 */
    default void bindToHost(LivingMetaMachineBlockEntity host) {
        setHost(host);
        // if (this instanceof LivingEntity living) {
        // living.getPersistentData().putLong("HostID", host.getHostPos().asLong());
        // }
    }
}
