package com.xiaohunao.setbonus.bonus;

import com.google.gson.JsonObject;
import com.xiaohunao.setbonus.SetBonus;
import com.xiaohunao.setbonus.api.IBonus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.UUID;

public class AttributeBonus implements IBonus {
    public static final String TYPE = "attribute";
    public Attribute attribute;
    public AttributeModifier modifier;

    public static class Builder {
        private final AttributeBonus bonus;

        public Builder(String attribute) {
            bonus = new AttributeBonus();
            Attribute value = ForgeRegistries.ATTRIBUTES.getValue(ResourceLocation.tryParse(attribute));
            if (value == null) {
                throw new RuntimeException("Attribute " + attribute + " not found");
            }
            bonus.attribute = value;
        }
        public Builder setModifier(float amount, int operation) {
            UUID uuid = UUID.nameUUIDFromBytes((SetBonus.MOD_ID + bonus.attribute.getDescriptionId() + amount + operation).getBytes());
            bonus.modifier = new AttributeModifier(uuid, SetBonus.MOD_ID + bonus.attribute.getDescriptionId(), amount, AttributeModifier.Operation.fromValue(operation));
            return this;
        }
        public AttributeBonus build() {
            return bonus;
        }
    }

    @Override
    public void apply(Player player) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance != null) {
            if (instance.getModifier(modifier.getId()) != null){
                instance.removeModifier(modifier);
            }
            instance.addTransientModifier(modifier);
        }
    }

    @Override
    public void clear(Player player) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance != null) {
            instance.removeModifier(modifier);
        }
    }

    @Override
    public IBonus read(JsonObject asJsonObject) {
        AttributeBonus.Builder builder = new AttributeBonus.Builder(GsonHelper.getAsString(asJsonObject, "attribute"))
                .setModifier(GsonHelper.getAsFloat(asJsonObject, "amount"), GsonHelper.getAsInt(asJsonObject, "operation"));
        return builder.build();
    }
}
