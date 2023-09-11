package com.xiaohunao.setbonus.group;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ChestGroup extends AbsGroup{
    public static final String TYPE = "chest";
    @Override
    public boolean isInGroup(Player player) {
        return getVerifier().verify(player.getInventory().armor.get(2));
    }
}
