package com.xiaohunao.setbonus.group;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class MainHandGroup extends AbsGroup{
    public static final String TYPE = "main_hand";
    @Override
    public boolean isInGroup(Player player) {
        return getVerifier().verify(player.getMainHandItem());
    }
}
