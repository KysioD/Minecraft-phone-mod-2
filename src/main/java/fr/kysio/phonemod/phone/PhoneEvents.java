package fr.kysio.phonemod.phone;

import fr.kysio.phonemod.PhoneMod;
import fr.kysio.phonemod.api.PhoneGraphicUtil;
import fr.kysio.phonemod.api.PhoneManager;
import fr.kysio.phonemod.api.applications.Application;
import fr.kysio.phonemod.client.KeyBindings;
import fr.kysio.phonemod.items.PhoneItems;
import fr.kysio.phonemod.network.PlayerOpenPhonePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class PhoneEvents {

    private boolean opened = false;

    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Post event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;
        ScaledResolution resolution = event.getResolution();

        if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() == PhoneItems.PHONE || player.getHeldItem(EnumHand.OFF_HAND).getItem() == PhoneItems.PHONE) {
            PhoneManager phoneManager = PhoneManager.getInstance();
            if (!opened) {
                opened = true;
                PhoneMod.network.sendToServer(new PlayerOpenPhonePacket(player));
            }

            PhoneGraphicUtil.drawBackground(resolution);
            if (!phoneManager.isLocked()) {
                Application application = phoneManager.getCurrentApplication();
                application.render(resolution);
            } else {
                float ticks = player.world.getWorldTime();
                int hours = (int)((Math.floor(ticks / 1000.0D) + 6.0D) % 24.0D);
                int minutes = (int)Math.floor(ticks % 1000L / 1000.0D * 60.0D);
                String hour_text = hours < 10 ? "0"+hours : hours+"";
                String minute_text = minutes < 10 ? "0"+minutes : minutes+"";

                //Top bar
                PhoneGraphicUtil.drawTopBar(resolution);

                // Hour
                PhoneGraphicUtil.drawCenteredString(resolution, 100, 80, 3, hour_text, Color.white.getRGB());
                PhoneGraphicUtil.drawCenteredString(resolution, 100, 102,  3, minute_text, Color.white.getRGB());

                // Unlock text
                PhoneGraphicUtil.drawCenteredString(resolution, 100, 250, 1, "press "+KeyBindings.UNLOCK_KEY.getDisplayName()+" to unlock", Color.white.getRGB());
            }
        } else {
            opened = false;
        }
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;
        if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() == PhoneItems.PHONE || player.getHeldItem(EnumHand.OFF_HAND).getItem() == PhoneItems.PHONE) {
            PhoneManager phoneManager = PhoneManager.getInstance();
            if (!phoneManager.isLocked()) {
                Application application = phoneManager.getCurrentApplication();
                application.keyPressed();

                if (application.getName() == "menu" && KeyBindings.BACK_KEY.isPressed()) phoneManager.setLocked(true);
            } else {
                if (KeyBindings.UNLOCK_KEY.isPressed()) {
                    System.out.println("UNLOCK");
                    Application menu = phoneManager.getApplication("menu");
                    if (menu != null) {
                        phoneManager.setCurrentApplication(menu);
                        phoneManager.setLocked(false);
                    }
                }
            }
        }
    }

}
