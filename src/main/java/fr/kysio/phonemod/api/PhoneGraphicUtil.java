package fr.kysio.phonemod.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.Sys;

import java.awt.*;

public class PhoneGraphicUtil {

    /**
     * Draw a string on the phone
     * @param resolution the minecraft screen ScaleResolution
     * @param x the x position (phone relative)
     * @param y the y position (phone relative)
     * @param text the text to draw
     * @param color the color of the text
     */
    public static void drawString(PhoneManager phoneManager, ScaledResolution resolution, int x, int y, int size, String text, int color){
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontRenderer = minecraft.fontRenderer;

        float scale = getPhoneScale(phoneManager);
        int screenX = (int) (resolution.getScaledWidth() / scale) - 210;
        int screenY = (int) (resolution.getScaledHeight() / scale) - 360;

        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1);
        fontRenderer.drawString(text, screenX+(x/size), screenY+(y/size), color);
        GlStateManager.popMatrix();
    }

    public static void drawCenteredString(PhoneManager phoneManager, ScaledResolution resolution, int x, int y, int size, String text, int color){
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontRenderer = minecraft.fontRenderer;

        float scale = getPhoneScale(phoneManager);
        int screenX = (int) ((resolution.getScaledWidth() / (scale)) - 210)/size;
        int screenY = (int) ((resolution.getScaledHeight() / (scale)) - 360)/size;

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 2);
        GlStateManager.scale(scale*size, scale*size, 1);
        fontRenderer.drawString(text, (screenX+(x/size)-(fontRenderer.getStringWidth(text)/2)), screenY+(y/size), color);
        GlStateManager.popMatrix();
    }

    /**
     * Draw the phone top bar
     * @param resolution the screen resolution
     */
    public static void drawTopBar(PhoneManager phoneManager, ScaledResolution resolution){
        EntityPlayer player = Minecraft.getMinecraft().player;
        float ticks = player.world.getWorldTime();
        int hours = (int)((Math.floor(ticks / 1000.0D) + 6.0D) % 24.0D);
        int minutes = (int)Math.floor(ticks % 1000L / 1000.0D * 60.0D);
        String hour_text = hours < 10 ? "0"+hours : hours+"";
        String minute_text = minutes < 10 ? "0"+minutes : minutes+"";

        // Top bar
        PhoneGraphicUtil.drawString(phoneManager, resolution, 21, 42, 1, "unkonwn", Color.white.getRGB()); // Mobile operator
        PhoneGraphicUtil.drawString(phoneManager, resolution, 152, 42, 1, "100%", Color.white.getRGB()); // Battery
        PhoneGraphicUtil.drawCenteredString(phoneManager, resolution, 100, 42, 1, hour_text+":"+minute_text, Color.white.getRGB());
    }

    /**
     * Get the phone scale
     * @return the phone scale
     */
    public static float getPhoneScale(PhoneManager manager){
        ItemStack phone = manager.getPhone();
        if(!phone.hasTagCompound()){
            return 0f;
        }
        return phone.getTagCompound().getFloat("s_scale");
    }

    /**
     * Draw the phone background
     * @param resolution the screen resolution
     */
    public static void drawBackground(PhoneManager phoneManager, ScaledResolution resolution) {
        Minecraft minecraft = Minecraft.getMinecraft();
        minecraft.getTextureManager().bindTexture(phoneManager.getBackground());

        float scale = getPhoneScale(phoneManager);

        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1);
        GlStateManager.enableAlpha();
        Gui.drawModalRectWithCustomSizedTexture((int) (resolution.getScaledWidth() / scale) - 210, (int) (resolution.getScaledHeight() / scale) - 360, 0, 0, 200, 350, 200, 350);
        GlStateManager.disableAlpha();
        GlStateManager.popMatrix();
    }
}
