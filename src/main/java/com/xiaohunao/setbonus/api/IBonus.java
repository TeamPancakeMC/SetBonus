package com.xiaohunao.setbonus.api;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.conditions.ICondition;

public interface IBonus {
    void apply();

    IBonus read(JsonObject asJsonObject);
}
