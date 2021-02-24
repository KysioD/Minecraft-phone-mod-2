package fr.kysio.phonemod.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {

    public static final KeyBinding UNLOCK_KEY = new KeyBinding("key.unlock.desc", 0, "kay.phone.category");
    public static final KeyBinding BACK_KEY = new KeyBinding("key.back.desc", 1, "kay.phone.category");

    public static void registerKeyBindings(){
        ClientRegistry.registerKeyBinding(UNLOCK_KEY);
        ClientRegistry.registerKeyBinding(BACK_KEY);
    }

}
