package fr.kysio.phonemod.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class PhoneGraphicUtil {

    /**
     * Draw a string on the phone
     * @param resolution the minecraft screen ScaleResolution
     * @param x the x position (phone relative)
     * @param y the y position (phone relative)
     * @param text the text to draw
     * @param color the color of the text
     */
    public static void drawString(ScaledResolution resolution, int x, int y, String text, int color){
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontRenderer = minecraft.fontRenderer;

        float scale = getPhoneScale();
        int screenX = (int) (resolution.getScaledWidth() / scale) - 210;
        int screenY = (int) (resolution.getScaledHeight() / scale) - 360;

        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1);
        fontRenderer.drawString(text, screenX+x, screenY+y, color);
        GlStateManager.popMatrix();
    }

    /**
     * Get the phone scale
     * @return the phone scale
     */
    public static float getPhoneScale(){
        PhoneManager phoneManager = PhoneManager.getInstance();
        return (phoneManager.getSettings("scale") != null) ? (float) (phoneManager.getSettings("scale").getValue()) : 0.5f;
    }

    /**
     * Draw the phone background
     * @param resolution the screen resolution
     */
    public static void drawBackground(ScaledResolution resolution) {
        Minecraft minecraft = Minecraft.getMinecraft();
        PhoneManager phoneManager = PhoneManager.getInstance();
        minecraft.getTextureManager().bindTexture(phoneManager.getBackground());

        float scale = getPhoneScale();

        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 1);
        GlStateManager.enableAlpha();
        Gui.drawModalRectWithCustomSizedTexture((int) (resolution.getScaledWidth() / scale) - 210, (int) (resolution.getScaledHeight() / scale) - 360, 0, 0, 200, 350, 200, 350);
        GlStateManager.disableAlpha();
        GlStateManager.popMatrix();
    }
}
