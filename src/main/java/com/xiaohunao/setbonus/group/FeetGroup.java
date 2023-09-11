package com.xiaohunao.setbonus.group;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class FeetGroup extends AbsGroup{
    public static final String TYPE = "feet";
    @Override
    public boolean isInGroup(Player player) {
        return getVerifier().verify(player.getInventory().armor.get(0));
    }
}
