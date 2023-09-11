package com.xiaohunao.setbonus.group;

import net.minecraft.world.entity.player.Player;

public class OffHandGroup extends AbsGroup{
    public static final String TYPE = "off_hand";
    @Override
    public boolean isInGroup(Player player) {
        return getVerifier().verify(player.getOffhandItem());
    }
}
