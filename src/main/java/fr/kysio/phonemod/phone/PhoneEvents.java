package fr.kysio.phonemod.phone;

import fr.kysio.phonemod.api.PhoneManager;
import fr.kysio.phonemod.items.PhoneItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PhoneEvents {

    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Pre event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;
        ScaledResolution resolution = event.getResolution();


        if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() == PhoneItems.PHONE || player.getHeldItem(EnumHand.OFF_HAND).getItem() == PhoneItems.PHONE) {
            PhoneManager phoneManager = PhoneManager.getInstance();

            if (!phoneManager.isLocked()) {
                if (phoneManager.getCurrentApplication() == null) return;
            } else {

                phoneManager.drawBackground(resolution);
            }
        }
    }

}
