package fr.kysio.phonemod.phone;

import fr.kysio.phonemod.api.PhoneGraphicUtil;
import fr.kysio.phonemod.api.PhoneManager;
import fr.kysio.phonemod.api.applications.Application;
import fr.kysio.phonemod.client.KeyBindings;
import fr.kysio.phonemod.items.PhoneItems;
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

    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Pre event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;
        ScaledResolution resolution = event.getResolution();


        if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() == PhoneItems.PHONE || player.getHeldItem(EnumHand.OFF_HAND).getItem() == PhoneItems.PHONE) {
            PhoneManager phoneManager = PhoneManager.getInstance();

            PhoneGraphicUtil.drawBackground(resolution);
            if (!phoneManager.isLocked()) {
                Application application = phoneManager.getCurrentApplication();
                application.render(resolution);
            }else{
                PhoneGraphicUtil.drawString(resolution, 10, 10, "test", Color.white.getRGB());
            }
        }
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event){
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;
        if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() == PhoneItems.PHONE || player.getHeldItem(EnumHand.OFF_HAND).getItem() == PhoneItems.PHONE) {
            PhoneManager phoneManager = PhoneManager.getInstance();
            if(!phoneManager.isLocked()){
                Application application = phoneManager.getCurrentApplication();
                application.keyPressed();

                if(application.getName() == "menu" && KeyBindings.BACK_KEY.isPressed()) phoneManager.setLocked(true);
            }else{
                if(KeyBindings.UNLOCK_KEY.isPressed()){
                    System.out.println("UNLOCK");
                    Application menu = phoneManager.getApplication("menu");
                    if(menu != null){
                        phoneManager.setCurrentApplication(menu);
                        phoneManager.setLocked(false);
                    }
                }
            }
        }
    }

}
