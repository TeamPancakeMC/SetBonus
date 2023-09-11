package com.xiaohunao.setbonus.init;

import com.google.common.collect.Maps;
import com.xiaohunao.setbonus.api.IGroup;
import com.xiaohunao.setbonus.group.ArmorGroup;
import com.xiaohunao.setbonus.group.MainHandGroup;
import com.xiaohunao.setbonus.group.OffHandGroup;

import java.util.Map;

public class GroupRegistry {
    private static Map<String, IGroup> Group = Maps.newHashMap();
    public static void registerBonus(String name, IGroup group) {
        Group.put(name, group);
    }

    public static IGroup getGroup(String name) {
        return Group.get(name);
    }
    public static void register(){
        registerBonus(ArmorGroup.NAME, new ArmorGroup());
        registerBonus(MainHandGroup.NAME, new MainHandGroup());
        registerBonus(OffHandGroup.NAME, new OffHandGroup());
    }
}
