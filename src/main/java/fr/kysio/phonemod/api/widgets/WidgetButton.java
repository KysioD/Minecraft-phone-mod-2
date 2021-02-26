package fr.kysio.phonemod.api.widgets;

import fr.kysio.phonemod.api.PhoneGraphicUtil;
import fr.kysio.phonemod.api.PhoneManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public abstract class WidgetButton extends PhoneWidget{

    public int backgroundColor;
    public int foregroundColor;
    public int selectedColor;
    public String text;

    public WidgetButton(int x, int y, int width, int height, float scale, int backgroundColor, int foregroundColor, int selectedColor, String text) {
        super(x, y, width, height, scale);
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        this.selectedColor = selectedColor;
        this.text = text;
    }

    @Override
    public void onRender(ScaledResolution resolution) {
        float phoneScale = PhoneGraphicUtil.getPhoneScale();
        int screenX = (int) (((resolution.getScaledWidth() / (phoneScale)) - 210)/getScale());
        int screenY = (int) (((resolution.getScaledHeight() / (phoneScale)) - 360)/getScale());

        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 2);
        GlStateManager.scale(phoneScale*getScale(), phoneScale*getScale(), 1);
        if(isSelected()) Gui.drawRect((screenX+(int)(getX()/getScale()))-2, (screenY+(int)(getY()/getScale()))-2, (screenX+(int)((getX()+getWidth())/getScale()))+2, (screenY+(int)((getY()+getHeight())/getScale()))+2, selectedColor);
        Gui.drawRect(screenX+(int)(getX()/getScale()), screenY+(int)(getY()/getScale()), screenX+(int)((getX()+getWidth())/getScale()), screenY+(int)((getY()+getHeight())/getScale()), backgroundColor);
        GlStateManager.popMatrix();

        PhoneGraphicUtil.drawCenteredString(resolution, getX()+(getWidth()/2),getY()+(getHeight()/2) - 2, 1, text, foregroundColor);
    }

    @Override
    public abstract void onUse();

    @Override
    public void onSelected() {
    }
}