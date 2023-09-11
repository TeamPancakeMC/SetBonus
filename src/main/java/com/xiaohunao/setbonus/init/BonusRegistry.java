package com.xiaohunao.setbonus.init;

import com.google.common.collect.Maps;
import com.xiaohunao.setbonus.api.IBonus;
import com.xiaohunao.setbonus.bonus.AttributeBonus;
import com.xiaohunao.setbonus.bonus.EffectBonus;

import java.util.Map;

public class BonusRegistry {
    private static Map<String, IBonus> Bonus = Maps.newHashMap();
    public static void registerBonus(String name, IBonus bonus) {
        Bonus.put(name, bonus);
    }

    public static IBonus getBonus(String name) {
        return Bonus.get(name);
    }
    public static void register(){
        registerBonus(EffectBonus.NAME, new EffectBonus());
        registerBonus(AttributeBonus.NAME,new AttributeBonus());
    }
}
