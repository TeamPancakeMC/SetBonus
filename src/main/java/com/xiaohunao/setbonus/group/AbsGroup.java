package com.xiaohunao.setbonus.group;

import com.google.gson.JsonObject;
import com.xiaohunao.setbonus.api.IGroup;
import com.xiaohunao.setbonus.data.ItemVerifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class AbsGroup implements IGroup {
    private ItemVerifier verifier;

    public static class Builder {
        private ItemVerifier verifier;

        private String type;
        public Builder(String type) {
            this.type = type;
        }

        public Builder setVerifier(ItemVerifier verifier) {
            this.verifier = verifier;
            return this;
        }

        public AbsGroup build() {
            AbsGroup group;
            switch (type) {
                case "main_hand" -> group = new MainHandGroup();
                case "off_hand" -> group = new OffHandGroup();
                case "head" -> group = new HeadGroup();
                case "chest" -> group = new ChestGroup();
                case "legs" -> group = new LegsGroup();
                case "feet" -> group = new FeetGroup();
                case "selected" -> group = new SelectedGroup();
                default -> throw new RuntimeException("Unknown group type: " + type);
            }
            group.verifier = verifier;
            return group;
        }
    }

    public ItemVerifier getVerifier() {
        return verifier;
    }

    @Override
    public boolean isInGroup(Player player) {
        return false;
    }

    @Override
    public IGroup read(JsonObject jsonObject) {
        return new Builder(jsonObject.get("type").getAsString())
                .setVerifier(ItemVerifier.read(jsonObject.get("verifier").getAsJsonObject()))
                .build();
    }
}
