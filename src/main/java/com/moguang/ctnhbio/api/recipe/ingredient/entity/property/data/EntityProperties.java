package com.moguang.ctnhbio.api.recipe.ingredient.entity.property.data;

import com.moguang.ctnhbio.api.recipe.ingredient.entity.property.IBaseEntityProperty;
import net.minecraft.network.chat.Component;

import java.util.HashMap;
import java.util.Map;

import static com.moguang.ctnhbio.api.recipe.ingredient.entity.property.SimpleEntityPropertyFactory.*;

public abstract class EntityProperties{
    
    public static final Map<String, IBaseEntityProperty<?>> ALL_PROPERTIES = new HashMap<>();

    //TODO: 要用的时候再加,懒得写
    //Entity::saveWithoutId
    public static final IBaseEntityProperty<Float> FALL_DISTANCE = new FloatEntityProperty("FallDistance");
    public static final IBaseEntityProperty<Short> REMAINING_FIRE_TICKS = new ShortEntityProperty("Fire");
    public static final IBaseEntityProperty<Short> AIR_SUPPLY = new ShortEntityProperty("Air");
    public static final IBaseEntityProperty<Boolean> ON_GROUND = new BooleanEntityProperty("OnGround");
    public static final IBaseEntityProperty<Boolean> INVULNERABLE = new BooleanEntityProperty("Invulnerable");
    public static final IBaseEntityProperty<Integer> PORTAL_COOLDOWN = new IntEntityProperty("PortalCooldown");
    public static final IBaseEntityProperty<Component> CUSTOM_NAME = new ComponentEntityProperty("CustomName");
    public static final IBaseEntityProperty<Boolean> CUSTOM_NAME_VISIBLE = new BooleanEntityProperty("CustomNameVisible");
    public static final IBaseEntityProperty<Boolean> SILENT = new BooleanEntityProperty("Silent");
    public static final IBaseEntityProperty<Boolean> NO_GRAVITY = new BooleanEntityProperty("NoGravity");
    public static final IBaseEntityProperty<Boolean> GLOWING = new BooleanEntityProperty("Glowing");
    public static final IBaseEntityProperty<Integer> TICKS_FROZEN = new IntEntityProperty("TicksFrozen");
    public static final IBaseEntityProperty<Boolean> HAS_VISUAL_FIRE = new BooleanEntityProperty("HasVisualFire");
    public static final IBaseEntityProperty<Boolean> CAN_UPDATE = new BooleanEntityProperty("CanUpdate");
    //LivingEntity::addAdditionalSaveData
    public static final IBaseEntityProperty<Float> HEALTH = new FloatEntityProperty("Health");
    public static final IBaseEntityProperty<Short> HURT_TIME = new ShortEntityProperty("HurtTime");
    public static final IBaseEntityProperty<Integer> HURT_BY_TIMESTAMP = new IntEntityProperty("HurtByTimestamp");
    public static final IBaseEntityProperty<Short> DEATH_TIME = new ShortEntityProperty("DeathTime");
    public static final IBaseEntityProperty<Float> ABSORPTION_AMOUNT = new FloatEntityProperty("AbsorptionAmount");
    public static final IBaseEntityProperty<Boolean> FALL_FLYING = new BooleanEntityProperty("FallFlying");




    public static void init(){

    }

    public static void put(IBaseEntityProperty<?> property){
        ALL_PROPERTIES.put(property.getSection(),property);
    }

    public static IBaseEntityProperty<?> get(String section){
        return ALL_PROPERTIES.get(section);
    }

    /*Utils*/
    /// @param <T> nbt数据类型
    /// @param clazz nbt数据类型的class
    /// @param key 该属性的名称
    public static <T> IBaseEntityProperty<T> simple(Class<T> clazz,String key) {
        var ret = create(clazz, key);
        put(ret);
        return ret;
    }
}
