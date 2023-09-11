package com.xiaohunao.setbonus.api;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IGroup {
    boolean isInGroup(Player player);
    IGroup read(JsonObject jsonObject);
}
