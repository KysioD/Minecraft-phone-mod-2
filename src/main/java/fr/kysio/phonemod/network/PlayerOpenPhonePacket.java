package fr.kysio.phonemod.network;

import fr.kysio.phonemod.sqript.events.OpenPhoneEvent;
import fr.nico.sqript.ScriptManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerOpenPhonePacket implements IMessage {

    public String player;

    public PlayerOpenPhonePacket(){

    }

    public PlayerOpenPhonePacket(EntityPlayer player){
        this.player = player.getName();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.player = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, player);
    }

    public static class Handler implements IMessageHandler<PlayerOpenPhonePacket, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(PlayerOpenPhonePacket message, MessageContext ctx) {
            String player = message.player;

            if(player.isEmpty()) return null;

            try {
                Class.forName("fr.nico.sqript.ScriptManager");

                ScriptManager.callEvent(new OpenPhoneEvent(FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(player)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }

            return null;
        }
    }
}
