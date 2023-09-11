package com.xiaohunao.setbonus;

import com.xiaohunao.setbonus.data.SetBonusManager;
import com.xiaohunao.setbonus.init.BonusRegistry;
import com.xiaohunao.setbonus.init.GroupRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(SetBonus.MOD_ID)
public class SetBonus {
    public static final String MOD_ID = "set_bonus";
    public SetBonus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BonusRegistry.register();
        GroupRegistry.register();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::onDataPackLoad);
    }

    private void onDataPackLoad(AddReloadListenerEvent event) {
        event.addListener(new SetBonusManager());
    }
}
