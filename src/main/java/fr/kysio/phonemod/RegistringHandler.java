package fr.kysio.phonemod;

import fr.kysio.phonemod.items.PhoneItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RegistringHandler {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(PhoneItems.PHONE, PhoneItems.SHIELD);
    }

}
