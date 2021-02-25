package fr.kysio.phonemod.network;

import fr.kysio.phonemod.sqript.events.ApplicationsEvent;
import fr.kysio.phonemod.sqript.events.OpenPhoneEvent;
import fr.nico.sqript.ScriptManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerOpenAppPacket implements IMessage {

    public String name;
    public String player;


    public PlayerOpenAppPacket(){

    }

    public PlayerOpenAppPacket(String name, EntityPlayer player){
        this.name = name;
        this.player = player.getName();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.name = ByteBufUtils.readUTF8String(buf);
        this.player = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, name);
        ByteBufUtils.writeUTF8String(buf, player);
    }

    public static class Handler implements IMessageHandler<PlayerOpenAppPacket, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PlayerOpenAppPacket message, MessageContext ctx) {
            String name = message.name;
            String player = message.player;

            if(player.isEmpty()) return null;
            if(name.isEmpty()) return null;

            try {
                Class.forName("fr.nico.sqript.ScriptManager");

                ScriptManager.callEvent(new ApplicationsEvent.Open(name, FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(player)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }

            return null;
        }
    }
}
