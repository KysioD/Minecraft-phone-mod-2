package fr.kysio.phonemod.sqript.events;

import fr.nico.sqript.events.ScriptEvent;
import fr.nico.sqript.meta.Event;
import fr.nico.sqript.structures.ScriptAccessor;
import fr.nico.sqript.types.TypePlayer;
import fr.nico.sqript.types.primitive.TypeString;
import net.minecraft.entity.player.EntityPlayer;
import sun.font.Script;

public class ApplicationsEvent {

    @Event(name = "phone application open",
            description = "Called when a player open an application on his phone",
            examples = "on phone application open:",
            patterns = "app open[ed]",
            accessors = {"app name:string", "app player:player"})
    public static class Open extends ScriptEvent{
        public Open(String applicationName, EntityPlayer player) {
            super(new ScriptAccessor(new TypeString(applicationName), "app name"),
                    new ScriptAccessor(new TypePlayer(player), "app player"));
        }
    }

    @Event(name = "phone application close",
            description = "Called when a player close an application on his phone",
            examples = "on phone application close:",
            patterns = "app close[d]",
            accessors = {"app name:string", "app player:player"})
    public static class Close extends ScriptEvent{
        public Close(String applicationName, EntityPlayer player) {
            super(new ScriptAccessor(new TypeString(applicationName), "app name"),
                    new ScriptAccessor(new TypePlayer(player), "app player"));
        }
    }
}
