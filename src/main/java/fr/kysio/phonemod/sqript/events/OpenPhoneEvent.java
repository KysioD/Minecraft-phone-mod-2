package fr.kysio.phonemod.sqript.events;

import fr.nico.sqript.ScriptManager;
import fr.nico.sqript.events.ScriptEvent;
import fr.nico.sqript.meta.Event;
import fr.nico.sqript.structures.ScriptAccessor;
import fr.nico.sqript.types.TypePlayer;
import net.minecraft.entity.player.EntityPlayer;

@Event(name = "open phone",
        description = "Called when a player open a phone",
        examples = "on phone open:",
        patterns = "phone open[ed]",
        accessors = "[phone] player:player")
public class OpenPhoneEvent extends ScriptEvent {

    public OpenPhoneEvent(EntityPlayer player) {
        super(new ScriptAccessor(new TypePlayer(player), "[phone] player"));
    }
}
