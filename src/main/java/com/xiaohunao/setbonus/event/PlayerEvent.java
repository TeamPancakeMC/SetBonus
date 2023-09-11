package com.xiaohunao.setbonus.event;


import com.google.gson.JsonObject;
import com.xiaohunao.setbonus.api.IGroup;
import com.xiaohunao.setbonus.data.ItemVerifier;
import com.xiaohunao.setbonus.data.SetBonusManager;
import com.xiaohunao.setbonus.init.GroupRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;


@Mod.EventBusSubscriber
public class PlayerEvent {

    @SubscribeEvent
    public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.level().isClientSide) return;

        if (entity instanceof Player player){
            for (Map.Entry<ResourceLocation, List<IGroup>> entry : SetBonusManager.groupMap.entrySet()) {
                ResourceLocation key = entry.getKey();
                List<IGroup> value = entry.getValue();

                SetBonusManager.bonusMap.get(key)
                        .forEach(bonus -> bonus.clear(player));
                boolean isInGroup = value.stream().allMatch(group -> group.isInGroup(player));
                if (isInGroup) {
                    SetBonusManager.bonusMap.get(key)
                            .forEach(bonus -> bonus.apply(player));
                }
            }
        }
    }

}
