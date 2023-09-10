package com.xiaohunao.setbonus.bonus;

import com.xiaohunao.setbonus.api.IBonus;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectBonus implements IBonus {
    public static final String NAME = "effect";
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

        public Builder setEffect(int duration, int amplifier) {
            if (this.effect == null){
                throw new RuntimeException("Effect already set");
            }
            bonus.effect = new MobEffectInstance(this.effect, duration, amplifier);
            return this;
        }

        public EffectBonus build() {
            return bonus;
        }
    }
    @Override
    public void apply() {

    }
}
