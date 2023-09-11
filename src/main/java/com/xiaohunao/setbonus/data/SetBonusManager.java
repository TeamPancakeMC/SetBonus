package com.xiaohunao.setbonus.data;

import com.google.common.collect.Maps;
import com.google.gson.*;
import com.xiaohunao.setbonus.SetBonus;
import com.xiaohunao.setbonus.api.IBonus;
import com.xiaohunao.setbonus.api.IGroup;
import com.xiaohunao.setbonus.init.BonusRegistry;
import com.xiaohunao.setbonus.init.GroupRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.common.crafting.conditions.ICondition;


import java.util.List;
import java.util.Map;

public class SetBonusManager extends SimpleJsonResourceReloadListener {
    public static Map<ResourceLocation, List<IGroup>> groupMap = Maps.newHashMap();
    public static Map<ResourceLocation, List<IBonus>> bonusMap = Maps.newHashMap();
    private static final Gson GSON =
            (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    public SetBonusManager() {
        super(GSON, "set_bonus");
    }


    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsonElementMap, ResourceManager manager, ProfilerFiller filler) {
        for (Map.Entry<ResourceLocation, JsonElement> entry : jsonElementMap.entrySet()) {
            ResourceLocation resourcelocation = entry.getKey();
            JsonElement value = entry.getValue();
            List<IBonus> bonuses = GsonHelper.convertToJsonArray(value, "bonus").asList().stream()
                    .map(JsonElement::getAsJsonObject)
                    .map(jsonObject -> {
                        String type = GsonHelper.getAsString(jsonObject, "type");
                        return BonusRegistry.getBonus(type).read(jsonObject);
                    })
                    .toList();
            List<IGroup> groups = GsonHelper.convertToJsonArray(value, "group").asList().stream()
                    .map(JsonElement::getAsJsonObject)
                    .map(jsonObject -> {
                        String type = GsonHelper.getAsString(jsonObject, "type");
                        return GroupRegistry.getGroup(type).read(jsonObject);
                    })
                    .toList();
            groupMap.put(resourcelocation, groups);
            bonusMap.put(resourcelocation, bonuses);
        }
    }
}
