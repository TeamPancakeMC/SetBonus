package com.xiaohunao.setbonus.bonus;

import com.google.gson.JsonObject;
import com.xiaohunao.setbonus.api.IBonus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectBonus implements IBonus {
    public static final String TYPE = "effect";
    public MobEffectInstance effect;

    public static class Builder {
        private final EffectBonus bonus;
        private final MobEffect effect;

        public Builder(String effect) {
            bonus = new EffectBonus();
            MobEffect value = ForgeRegistries.MOB_EFFECTS.getValue(ResourceLocation.tryParse(effect));
            if (value == null) {
                throw new RuntimeException("Effect " + effect + " not found");
            }
            this.effect = value;
        }

        public Builder setEffect(int level) {
            if (this.effect == null){
                throw new RuntimeException("Effect already set");
            }
            bonus.effect = new MobEffectInstance(this.effect, -1, level);
            return this;
        }

        public EffectBonus build() {
            return bonus;
        }
    }
    @Override
    public void apply(Player player) {
        player.addEffect(effect);
    }

    @Override
    public void clear(Player player) {
        player.removeEffect(effect.getEffect());
    }

    @Override
    public IBonus read(JsonObject asJsonObject) {
        EffectBonus.Builder builder = new EffectBonus.Builder(asJsonObject.get("effect").getAsString())
                .setEffect(asJsonObject.get("level").getAsInt());
        return builder.build();
    }
}
