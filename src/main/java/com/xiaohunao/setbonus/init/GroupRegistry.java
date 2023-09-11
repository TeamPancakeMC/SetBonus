package com.xiaohunao.setbonus.init;

import com.google.common.collect.Maps;
import com.xiaohunao.setbonus.api.IGroup;
import com.xiaohunao.setbonus.group.*;

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
        registerBonus(MainHandGroup.TYPE, new MainHandGroup());
        registerBonus(OffHandGroup.TYPE, new OffHandGroup());
        registerBonus(SelectedGroup.TYPE, new SelectedGroup());
        registerBonus(HeadGroup.TYPE, new HeadGroup());
        registerBonus(ChestGroup.TYPE, new ChestGroup());
        registerBonus(LegsGroup.TYPE, new LegsGroup());
        registerBonus(FeetGroup.TYPE, new FeetGroup());
    }
}
