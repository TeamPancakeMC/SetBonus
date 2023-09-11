package com.xiaohunao.setbonus.group;

import net.minecraft.world.entity.player.Player;

public class SelectedGroup extends AbsGroup{
    public static final String TYPE = "selected";
    @Override
    public boolean isInGroup(Player player) {
        return getVerifier().verify(player.getInventory().getSelected());
    }
}
