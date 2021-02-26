package fr.kysio.phonemod.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    public static final KeyBinding UNLOCK_KEY = new KeyBinding("key.unlock.desc", Keyboard.KEY_RETURN, "kay.phone.category");
    public static final KeyBinding BACK_KEY = new KeyBinding("key.back.desc", Keyboard.KEY_BACK, "kay.phone.category");
    public static final KeyBinding UP_KEY = new KeyBinding("key.up.desc", Keyboard.KEY_UP, "key.phone.category");
    public static final KeyBinding DOWN_KEY = new KeyBinding("key.down.desc", Keyboard.KEY_DOWN, "key.phone.category");
    public static final KeyBinding LEFT_KEY = new KeyBinding("key.left.desc", Keyboard.KEY_LEFT, "key.phone.category");
    public static final KeyBinding RIGHT_KEY = new KeyBinding("key.right.desc", Keyboard.KEY_RIGHT, "key.phone.category");

    public static void registerKeyBindings(){
        ClientRegistry.registerKeyBinding(UNLOCK_KEY);
        ClientRegistry.registerKeyBinding(BACK_KEY);
        ClientRegistry.registerKeyBinding(UP_KEY);
        ClientRegistry.registerKeyBinding(DOWN_KEY);
        ClientRegistry.registerKeyBinding(LEFT_KEY);
        ClientRegistry.registerKeyBinding(RIGHT_KEY);
    }

}
