package com.xiaohunao.setbonus.api;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.player.Player;

public interface IBonus {
    void apply(Player player);
    void clear(Player player);

    IBonus read(JsonObject asJsonObject);
}
